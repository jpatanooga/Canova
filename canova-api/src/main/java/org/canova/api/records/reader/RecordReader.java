package org.canova.api.records.reader;

import org.canova.api.split.InputSplit;
import org.canova.api.writable.Writable;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by agibsonccc on 11/15/14.
 */
public interface RecordReader {

    /**
     * Called once at initialization.
     * @param split the split that defines the range of records to read
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    void initialize(InputSplit split) throws IOException, InterruptedException;


    /**
     * Get the next record
     * @return
     */
    Collection<Writable> next();


    /**
     * Whether there are anymore records
     * @return
     */
    boolean hasNext();




}
