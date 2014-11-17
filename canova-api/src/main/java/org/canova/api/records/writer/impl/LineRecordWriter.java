package org.canova.api.records.writer.impl;

import org.canova.api.io.data.Text;
import org.canova.api.writable.Writable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

/**
 * Line record writer
 * @author Adam Gibson
 */
public class LineRecordWriter extends FileRecordWriter {


    public LineRecordWriter(File path) throws FileNotFoundException {
        super(path);
    }

    @Override
    public void write(Collection<Writable> record) throws IOException {
         if(!record.isEmpty()) {
             Text t = (Text) record.iterator().next();
             t.write(out);
             out.write(NEW_LINE.getBytes());
         }


    }
}
