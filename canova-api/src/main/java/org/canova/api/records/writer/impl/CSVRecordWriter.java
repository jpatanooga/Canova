package org.canova.api.records.writer.impl;

import org.canova.api.writable.Writable;

import java.io.*;
import java.util.Collection;

/**
 * Csv record writer
 *
 * @author Adam Gibson
 */
public class CSVRecordWriter extends FileRecordWriter {


    public CSVRecordWriter(File path) throws FileNotFoundException {
        super(path);
    }

    @Override
    public void write(Collection<Writable> record) throws IOException {
        if(!record.isEmpty()) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            for(Writable w : record) {
                w.write(dos);
                dos.write(NEW_LINE.getBytes());
            }

            dos.flush();
            dos.close();
        }

    }
}
