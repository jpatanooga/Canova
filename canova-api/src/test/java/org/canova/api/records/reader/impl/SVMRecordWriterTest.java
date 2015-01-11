package org.canova.api.records.reader.impl;

import static org.junit.Assume.*;

import org.apache.commons.io.IOUtils;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.records.writer.RecordWriter;
import org.canova.api.records.writer.impl.SVMLightRecordWriter;
import org.canova.api.split.FileSplit;
import org.canova.api.split.InputSplit;
import org.canova.api.writable.Writable;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by agibsonccc on 1/11/15.
 */
public class SVMRecordWriterTest {

    @Test
    public void testWriter() throws Exception {
        InputStream is  = new ClassPathResource("iris.dat").getInputStream();
        assumeNotNull(is);
        File tmp = new File("iris.txt");
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmp));
        IOUtils.copy(is, bos);
        bos.flush();
        bos.close();
        InputSplit split = new FileSplit(tmp);
        tmp.deleteOnExit();
        RecordReader reader = new CSVRecordReader();
        List<Collection<Writable>> records = new ArrayList<>();
        reader.initialize(split);
        int count = 0;
        while(reader.hasNext()) {
            Collection<Writable> record = reader.next();
            records.add(record);
            assertEquals(5, record.size());
            records.add(record);
            count++;
        }

        assertEquals(150,count);
        File out = new File("iris_out.txt");
        out.deleteOnExit();
        RecordWriter writer = new SVMLightRecordWriter(out);
        for(Collection<Writable> record : records)
            writer.write(record);

        RecordReader svmReader = new SVMLightRecordReader();
        InputSplit svmSplit = new FileSplit(out);
        svmReader.initialize(svmSplit);
        while(svmReader.hasNext()) {
            Collection<Writable> record = svmReader.next();
            records.add(record);
            assertEquals(5, record.size());
            records.add(record);
            count++;
        }




    }


}
