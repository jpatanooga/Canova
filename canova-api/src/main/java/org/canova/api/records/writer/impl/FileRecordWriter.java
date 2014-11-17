package org.canova.api.records.writer.impl;

import org.canova.api.records.writer.RecordWriter;

import java.io.*;

/**
 * Write to files
 * @author Adam Gibson
 */
public abstract class FileRecordWriter implements RecordWriter {

    protected File writeTo;
    protected DataOutputStream out;
    public final static String NEW_LINE = "\n";

    public FileRecordWriter(File path) throws FileNotFoundException {
        this.writeTo = path;
        out = new DataOutputStream(new FileOutputStream(writeTo));
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
