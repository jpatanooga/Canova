package org.canova.api.records;

import org.canova.api.writable.Writable;

import java.util.Collection;
import java.util.Iterator;

/**
 * An individual record.
 * In Machine learning this would be an "example" that was an unvectorized.
 * @author Adam Gibson
 */
public interface Record {


    /**
     * Read from an input
     * @param recordInput the input to read fields from
     */
    void deserialize(RecordInput recordInput);

    /**
     *
     * Write to an output
     * @param recordOutput the output to write to
     */
    void serialize(RecordOutput recordOutput);

    /**
     * The number of elements
     * @return the number of elements
     */
    int size();


    /**
     * An iterator over the elements
     * @return an iterator over the elements
     */
    Iterator<Writable> iterator();


    /**
     * The collection of elements
     * @return the collection of elements
     */
    Collection<Writable> values();

    /**
     * Add a value to the record
     * @param value the value to add
     */
    void add(Writable value);

    /**
     * Remove a value from the record
     * @param value the value to remove
     */
    void remove(Writable value);



}
