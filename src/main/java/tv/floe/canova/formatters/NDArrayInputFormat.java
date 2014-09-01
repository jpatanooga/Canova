package tv.floe.canova.formatters;

import org.deeplearning4j.linalg.jblas.NDArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/31/14.
 */
public class NDArrayInputFormat extends BaseInputFormat<NDArray> {
    @Override
    public NDArray read(InputStream is, String delim) throws IOException {
        return read_obj(is, delim);
    }
    public NDArray read(InputStream is) throws IOException {
        return read(is, COMMA);
    }

    @Override
    public NDArray read(String is, String delim) throws IOException {
        return read_obj(is, delim);
    }
    public NDArray read(String is) throws IOException {
        return read(is, COMMA);
    }
    @Override
    public NDArray read(File file, String delim) throws IOException {
        return read_obj(file, delim);
    }
    public NDArray read(File file) throws IOException {
        return read(file, COMMA);
    }
}
