package org.canova.api.records.reader;

import org.canova.api.records.Record;

/**
 * Created by agibsonccc on 11/15/14.
 */
public interface RecordReader {


    /**
     * Get the next record
     * @return
     */
    Record next();


    /**
     * Whether there are anymore records
     * @return
     */
    boolean hasNext();




}
