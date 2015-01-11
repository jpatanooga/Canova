package org.canova.api.records.reader.impl;

import org.canova.api.io.data.DoubleWritable;
import org.canova.api.io.data.Text;
import org.canova.api.writable.Writable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/**
 * Adapted from the weka svmlight reader
 *
 *
 * @author Adam Gibson
 */
public class SVMLightRecordReader extends LineRecordReader {


    @Override
    public Collection<Writable> next() {
        Text t =  (Text) super.next().iterator().next();
        String val = new String(t.getBytes());
        Collection<Writable> ret = new ArrayList<>();
        StringTokenizer tok;
        int	index,max;
        String	col;
        double	value;

        // actual data
        try {
            // determine max index
            max = 0;
            tok = new StringTokenizer(val, " \t");
            tok.nextToken();  // skip class
            while (tok.hasMoreTokens()) {
                col = tok.nextToken();
                // finished?
                if (col.startsWith("#"))
                    break;
                // qid is not supported
                if (col.startsWith("qid:"))
                    continue;
                // actual value
                index = Integer.parseInt(col.substring(0, col.indexOf(":")));
                if (index > max)
                    max = index;
            }

            // read values into array
            tok    = new StringTokenizer(val, " \t");

            // 1. class
            double classVal = Double.parseDouble(tok.nextToken());

            // 2. attributes
            while (tok.hasMoreTokens()) {
                col  = tok.nextToken();
                // finished?
                if (col.startsWith("#"))
                    break;
                // qid is not supported
                if (col.startsWith("qid:"))
                    continue;
                // actual value
                index = Integer.parseInt(col.substring(0, col.indexOf(":")));
                value = Double.parseDouble(col.substring(col.indexOf(":") + 1));
                ret.add(new DoubleWritable(value));
            }

            ret.add(new DoubleWritable(classVal));
        }
        catch (Exception e) {
            System.err.println("Error parsing line '" + val + "': " + e);
        }

        return ret;
    }



}
