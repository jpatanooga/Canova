package tv.floe.canova.formatters;

import org.deeplearning4j.util.MovingWindowMatrix;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mjk on 8/31/14.
 */
public class MovingWindowMatrixInputFormat extends BaseInputFormat<MovingWindowMatrix> {
    @Override
    public MovingWindowMatrix read(InputStream is, String delim) throws IOException {
        return readObj(is, delim);
    }

    public MovingWindowMatrix read(InputStream is) throws IOException {
        return read(is, COMMA);
    }

    @Override
    public MovingWindowMatrix read(String is, String delim) throws IOException {
        return readObj(is, delim);
    }

    public MovingWindowMatrix read(String is) throws IOException {
        return read(is, COMMA);
    }

    @Override
    public MovingWindowMatrix read(File file, String delim) throws IOException {
        return read(file, delim);
    }

    public MovingWindowMatrix read(File file) throws IOException {
        return read(file);
    }
}
