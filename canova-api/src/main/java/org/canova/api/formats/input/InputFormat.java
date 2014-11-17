package org.canova.api.formats.input;

import org.canova.api.records.reader.RecordReader;
import org.canova.api.split.InputSplit;
import org.canova.api.writable.Writable;

import java.io.IOException;

/**
 * Create an input format
 *
 * @author Adam Gibson
 */
public interface InputFormat extends Writable {

    /**
     * Creates a reader from an input split
     * @param split the split to read
     * @return the reader from the given input split
     */
    RecordReader createReader(InputSplit split) throws IOException, InterruptedException;

}
