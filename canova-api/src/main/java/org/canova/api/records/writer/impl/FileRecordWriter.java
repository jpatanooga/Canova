package org.canova.api.records.writer.impl;

import org.canova.api.io.data.Text;
import org.canova.api.records.writer.RecordWriter;
import org.canova.api.writable.Writable;

import java.io.*;
import java.util.Collection;

/**
 * Write to files
 * @author Adam Gibson
 */
public  class FileRecordWriter implements RecordWriter {

    protected File writeTo;
    protected DataOutputStream out;
    public final static String NEW_LINE = "\n";

    public FileRecordWriter(File path) throws FileNotFoundException {
        this.writeTo = path;
        out = new DataOutputStream(new FileOutputStream(writeTo));
    }


    @Override
    public void write(Collection<Writable> record) throws IOException {
        if(!record.isEmpty()) {
            Text t = (Text) record.iterator().next();
            t.write(out);
        }
    }

    @Override
    public void close() {
        if(out != null) {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
