package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mjk on 8/28/14.
 */
public interface IOutputFormat<T> {

    /**
     *
     * @param t
     * @param file
     * @throws IOException
     */
    public void write(T t, File file) throws IOException;

    /**
     *
     * @param t
     * @param str
     * @throws IOException
     */
    public String write(T t, String str) throws IOException;

    /**
     *
     * @param t
     * @param os
     * @throws IOException
     */
    public void write(T t, OutputStream os) throws IOException;
}
