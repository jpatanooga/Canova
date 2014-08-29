package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/28/14.
 */
public interface IInputFormat<T> {
    public T read(InputStream is) throws IOException;

    public T read(String is) throws IOException;

    public T read(File file) throws IOException;

}
