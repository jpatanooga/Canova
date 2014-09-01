package tv.floe.canova.formatters;

import org.deeplearning4j.topicmodeling.DocumentMatrix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/31/14.
 */
public class DocumentMatrixInputFormat extends BaseInputFormat<DocumentMatrix> {
    @Override
    public DocumentMatrix read(InputStream is, String delim) throws IOException {
        DocumentMatrix cv = null;
        cv = readObj(is, delim);
        return cv;
    }
    public DocumentMatrix read(InputStream is) throws IOException {
        return this.read(is, COMMA);
    }

    @Override
    public DocumentMatrix read(String is, String delim) throws IOException {
        DocumentMatrix ret = readObj(is, delim);
        return ret;
    }
    public DocumentMatrix read(String is) throws IOException {
        return this.read(is, COMMA);
    }


    @Override
    public DocumentMatrix read(File file, String delim) throws IOException {
        DocumentMatrix ret = readObj(file, delim);
        return ret;
    }
    public DocumentMatrix read(File file) throws IOException {
        return this.read(file, COMMA);
    }

}
