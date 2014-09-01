package tv.floe.canova.formatters;


import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by mjk on 8/28/14.
 */

public abstract class BaseInputFormat<T> implements IInputFormat<T>, Serializable {
    protected static final String COMMA = new String(",");


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

    protected T read_obj(String is, String delim) throws IOException {
        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        T ret = read(stream, delim);
        stream.close();
        return ret;
    }

    public T read_obj(InputStream is, String delim) throws IOException {
        ObjectInputStream objin = new ObjectInputStream(is);
        T cv = null;
        try {
            cv = (T) objin.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cv;
    }

    public T read_obj(File file, String delim) throws IOException {
        FileInputStream fs = new FileInputStream(file);
        T cv = read(fs, delim);
        fs.close();
        return cv;
    }

}
