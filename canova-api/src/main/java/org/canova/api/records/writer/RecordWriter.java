package org.canova.api.records.writer;


import org.canova.api.writable.Writable;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collection;

/**
 *  Record writer
 *  @author Adam Gibson
 */
public interface RecordWriter extends Closeable {


    /**
     * Write a record
     * @param record the record to write
     */
    void write(Collection<Writable> record) throws IOException;



    void close();

}
