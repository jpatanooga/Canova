package tv.floe.canova.formatters;

import org.deeplearning4j.topicmodeling.CountVectorizer;

import java.io.*;

/**
 * Created by mjk on 8/31/14.
 */

public class CountVectorizerOutputFormat extends BaseOutputFormat<CountVectorizer> {
    /**
     * Writes a CountVectorizer into a file
     * @param countVectorizer
     * @param file
     * @throws IOException
     */
    @Override
    public void write(CountVectorizer countVectorizer, File file) throws IOException {
        writeObj(countVectorizer, file);
    }

    /**
     * Writes a CountVectorizer into a String
     * @param countVectorizer
     * @return
     * @throws IOException
     */
    @Override
    public String write(CountVectorizer countVectorizer) throws IOException {
        return writeObj(countVectorizer);
    }

    /**
     * Writes a CountVectorizer into an Output Stream
     * @param countVectorizer
     * @param os
     * @throws IOException
     */
    @Override
    public void write(CountVectorizer countVectorizer, OutputStream os) throws IOException {
        writeObj(countVectorizer, os);
    }
}
