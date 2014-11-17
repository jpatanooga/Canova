package org.canova.api.vector;

import org.canova.api.writable.Writable;

import java.util.Collection;

/**
 * Vectorizer of a particular type.
 * Meant for converting individual records to vectors
 *
 * @author Adam Gibson
 */
public interface Vectorizer<VECTOR_TYPE> {


    /**
     * Transform a record in to a vector
     * @param record the record to write
     * @return
     */
    VECTOR_TYPE transform(Collection<Writable> record);


}
