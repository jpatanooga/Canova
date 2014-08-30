package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mjk on 8/28/14.
 */
public interface IOutputFormat<T> {

    /**
     * Writes object to File pointer
     *
     * @param t
     * @param file
     * @throws IOException
     */
    public void write(T t, File file) throws IOException;

    /**
     * Write object to String and returns it.
     *
     * @param t
     * @throws IOException
     */
    public String write(T t) throws IOException;

    /**
     * Write object to OutputStream
     *
     * @param t
     * @param os
     * @throws IOException
     */
    public void write(T t, OutputStream os) throws IOException;
}
