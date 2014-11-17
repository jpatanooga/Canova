package org.canova.api.records.writer;


import org.canova.api.writable.Writable;

import java.util.Collection;

/**
 *  Record writer
 *  @author Adam Gibson
 */
public interface RecordWriter {


    /**
     * Write a record
     * @param record the record to write
     */
    void write(Collection<Writable> record);

}
