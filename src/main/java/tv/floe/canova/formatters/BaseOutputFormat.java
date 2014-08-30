package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mjk on 8/29/14.
 */
public abstract class BaseOutputFormat<T> implements IOutputFormat<T> {
    /**
     * 
     * @param t
     * @param file
     * @throws IOException
     */
    public abstract void write(T t, File file) throws IOException;

    /**
     *
     * @param t
     * @throws IOException
     */
    public abstract String write(T t) throws IOException;

    /**
     *
     * @param t
     * @param os
     * @throws IOException
     */
    public abstract void write(T t, OutputStream os) throws IOException;
}
