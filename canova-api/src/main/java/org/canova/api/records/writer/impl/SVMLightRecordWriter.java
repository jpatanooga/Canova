package org.canova.api.records.writer.impl;

import org.canova.api.writable.Writable;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 *
 * SVM Light Record Writer
 *
 * @author Adam Gibson
 *
 */
public class SVMLightRecordWriter extends FileRecordWriter {

    public SVMLightRecordWriter(File path) throws FileNotFoundException {
        super(path);
    }
    public SVMLightRecordWriter(File path,boolean append) throws FileNotFoundException {
        super(path,append);
    }
    @Override
    public void write(Collection<Writable> record) throws IOException {
        if(!record.isEmpty()) {
            List<Writable> recordList = record instanceof List ? (List<Writable>) record : new ArrayList<>(record);
            StringBuffer result = new StringBuffer();
            result.append(recordList.get(recordList.size() - 1).toString());

            for(int i = 0; i < recordList.size() - 1; i++)
                result.append(" " + (i + 1) + ":"
                        + Double.valueOf(recordList.get(i).toString()));

            out.write(result.toString().getBytes());
            out.write(NEW_LINE.getBytes());

        }

    }
}
