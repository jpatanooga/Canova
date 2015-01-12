package org.canova.api.records.reader.impl;

import org.canova.api.io.data.DoubleWritable;
import org.canova.api.writable.Writable;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Matlab record reader
 * @author Adam Gibson
 */
public class MatlabRecordReader extends FileRecordReader {

    private Collection<Writable> currRecord;
    private List<Collection<Writable>> records = new ArrayList<>();
    private Iterator<Collection<Writable>> currIter;

    @Override
    public boolean hasNext() {
        return super.hasNext();
    }

    @Override
    public Collection<Writable> next() {
        //use the current iterator
        if(currIter != null && currIter.hasNext())
            return currIter.next();
        records.clear();
        //next file
        Collection<Writable> next = super.next();
        String val = next.iterator().next().toString();
        StringReader reader = new StringReader(val);
        int	c;
        char chr;
        StringBuffer fileContent;
        boolean	isComment;


        currRecord       = new ArrayList<>();
        fileContent       = new StringBuffer();
        isComment = false;
        records.add(currRecord);
        try {
            // determine number of attributes
            while ((c = reader.read()) != -1) {
                chr = (char) c;

                // comment found?
                if (chr == '%')
                    isComment = true;

                // end of line reached
                if ((chr == '\n') || (chr == '\r')) {
                    isComment = false;
                    if (fileContent.length() > 0)
                        currRecord.add(new DoubleWritable(new Double(fileContent.toString())));

                    if (currRecord.size() > 0) {
                        currRecord = new ArrayList<>();
                        records.add(currRecord);
                    }
                    fileContent = new StringBuffer();
                    continue;
                }

                // skip till end of comment line
                if (isComment)
                    continue;

                // separator found?
                if ((chr == '\t') || (chr == ' ')) {
                    if (fileContent.length() > 0) {
                        currRecord.add(new DoubleWritable(new Double(fileContent.toString())));
                        fileContent = new StringBuffer();
                    }
                }
                else {
                    fileContent.append(chr);
                }
            }

            // last number?
            if (fileContent.length() > 0)
                currRecord.add(new DoubleWritable(new Double(fileContent.toString())));


             currIter = records.iterator();

        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("Unable to determine structure as Matlab ASCII file: " + ex);
        }
        throw new IllegalStateException("Strange state detected");
    }
}
