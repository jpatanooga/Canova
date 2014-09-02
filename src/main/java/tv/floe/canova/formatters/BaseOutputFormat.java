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

    /**
     * Write/serialize a generic object to an output stream
     * @param countVectorizer
     * @param os
     * @throws IOException
     */
    public void writeObj(T countVectorizer, OutputStream os) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(countVectorizer);
        oos.close();
    }

    /**
     * Write/Serialize a generic object to a string and return it
     * @param countVectorizer
     * @return
     * @throws IOException
     */
    public String writeObj(T countVectorizer) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        write(countVectorizer, os);
        os.close();
        String imgString = org.apache.commons.codec.binary.Base64.encodeBase64String(os.toByteArray());
        return imgString;
    }

    /**
     * Write/serialize and object to the file object given
     * @param countVectorizer
     * @param file
     * @throws IOException
     */
    public void writeObj(T countVectorizer, File file) throws IOException {
        FileOutputStream ofs = new FileOutputStream(file);
        write(countVectorizer, ofs);
        ofs.close();
    }
    protected static final String DELIM_NEWLINE  = new String("\n");
    protected static final String DELIM_COLON  = new String(":");
    protected static final String DELIM_BAR  = new String("|");
    protected static final String SPACE  = new String(" ");

}
