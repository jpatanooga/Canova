package tv.floe.canova.formatters;

import java.io.*;

/**
 * Created by mjk on 8/29/14.
 */
public abstract class BaseOutputFormat<T> implements IOutputFormat<T> {
    /**
     * @param t
     * @param file
     * @throws IOException
     */
    public abstract void write(T t, File file) throws IOException;

    /**
     * @param t
     * @throws IOException
     */
    public abstract String write(T t) throws IOException;

    /**
     * @param t
     * @param os
     * @throws IOException
     */
    public abstract void write(T t, OutputStream os) throws IOException;

    public void write_obj(T countVectorizer, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(countVectorizer);
        oos.close();
    }
    public String write_obj(T countVectorizer) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        write(countVectorizer, os);
        os.close();
        String imgString = org.apache.commons.codec.binary.Base64.encodeBase64String(os.toByteArray());
        return imgString;
    }
    public void write_obj(T countVectorizer, File file) throws IOException {
        FileOutputStream ofs = new FileOutputStream(file);
        write(countVectorizer, ofs);
        ofs.close();
    }
}
