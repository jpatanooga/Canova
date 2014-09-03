package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/28/14.
 */
public interface IInputFormat<T> {
    public T read(InputStream is, String delim) throws IOException;

    public T read(String is, String delim) throws IOException;

    public T read(File file, String delim) throws IOException;

}
