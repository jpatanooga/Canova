package org.canova.api.records.writer;

import org.canova.api.records.Record;

/**
 *  Record writer
 *  @author Adam Gibson
 */
public interface RecordWriter {


    /**
     * Write a record
     * @param record the record to write
     */
    void write(Record record);

}
