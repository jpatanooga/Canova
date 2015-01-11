package org.canova.api.formats.input.impl;

import org.canova.api.formats.input.InputFormat;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.records.reader.impl.SVMLightRecordReader;
import org.canova.api.split.InputSplit;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by agibsonccc on 1/11/15.
 */
public class SVMLightInputFormat implements InputFormat {
    @Override
    public RecordReader createReader(InputSplit split) throws IOException, InterruptedException {
        SVMLightRecordReader reader = new SVMLightRecordReader();
        reader.initialize(split);
        return reader;
    }

    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
