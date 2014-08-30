package tv.floe.canova.formatters;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
/**
 * Created by mjk on 8/28/14.
 */

public abstract class BaseInputFormat<T> implements IInputFormat<T> {

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */
    @Override
    public abstract T read(InputStream is, String delim) throws IOException;

    /**
     *
     * @param is
     * @return
     * @throws IOException
     */
    @Override
    public abstract T read(String is, String delim) throws IOException;

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public abstract T read(File file, String delim) throws IOException;

    /**
     *
     * @param ins
     * @return
     */
    protected String[] stringSplitComma(String ins) {
        return stringSplit(ins, new String(","));
    }

    /**
     *
     * @param ins
     * @param delim
     * @return
     */
    protected String[] stringSplit(String ins, String delim) {
        String[] tokens = ins.split(delim);
        return tokens;
    }
}
