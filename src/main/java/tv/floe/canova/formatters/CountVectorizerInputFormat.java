package tv.floe.canova.formatters;

import org.deeplearning4j.topicmodeling.CountVectorizer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/30/14.
 */
public class CountVectorizerInputFormat extends BaseInputFormat<CountVectorizer>
{
    private static final String COMMA = new String(",");

    /**
     * Reads an input stream into, and returns, a CountVectorizer
     * @param is
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public CountVectorizer read(InputStream is, String delim) throws IOException {
        CountVectorizer cv = readObj(is, delim);
        return cv;
    }

    /**
     * Reads an input stream into, and returns, a CountVectorizer
     * @param is
     * @return
     * @throws IOException
     */
    public CountVectorizer read(InputStream is) throws IOException {
        return read(is, COMMA);
    }

    /**
     * Reads a string into, and returns, a CountVectorizer
     * @param is
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public CountVectorizer read(String is, String delim) throws IOException {
        CountVectorizer ret = readObj(is, delim);
        return ret;
    }

    /**
     * Reads a string into, and returns, a CountVectorizer
     * @param is
     * @return
     * @throws IOException
     */
    public CountVectorizer read(String is) throws IOException {
        return read(is, COMMA);
    }

    /**
     * Reads a file into, and returns, a CountVectorizer
     * @param file
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public CountVectorizer read(File file, String delim) throws IOException {
        CountVectorizer cv = readObj(file, delim);
        return cv;
    }

    /**
     * Reads a file into, and returns, a CountVectorizer
     * @param file
     * @return
     * @throws IOException
     */
    public CountVectorizer read(File file) throws IOException {
        return read(file, COMMA);
    }
}
