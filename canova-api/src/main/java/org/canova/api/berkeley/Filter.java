package org.canova.api.berkeley;

/**
 * Filters are boolean functions which accept or reject items.
 * @author Dan Klein
 */
public interface Filter<T> {
  boolean accept(T t);
}
