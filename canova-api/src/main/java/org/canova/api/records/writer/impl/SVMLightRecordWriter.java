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

    @Override
    public void write(Collection<Writable> record) throws IOException {
        if(!record.isEmpty()) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            List<Writable> recordList = record instanceof List ? (List<Writable>) record : new ArrayList<>(record);
            StringBuffer result = new StringBuffer();
            result.append(recordList.get(recordList.size() - 1).toString());

            for(int i = 0; i < recordList.size() - 1; i++) {

                result.append(" " + (i + 1) + ":"
                        + Double.valueOf(recordList.get(i).toString()));



            }


            dos.write(result.toString().getBytes());
            dos.write(NEW_LINE.getBytes());

            dos.flush();
            dos.close();
        }

    }
}
