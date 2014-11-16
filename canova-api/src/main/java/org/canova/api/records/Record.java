package org.canova.api.records;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by agibsonccc on 11/15/14.
 */
public interface Record {


    int size();


    Iterator<Serializable> iterator();

    Collection<Serializable> values();


    void add(Serializable value);

    void remove(Serializable value);



}
