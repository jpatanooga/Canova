package tv.floe.canova.formatters;

import org.deeplearning4j.util.MovingWindowMatrix;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mjk on 8/31/14.
 */
public class MovingWindowMatrixOutputFormat extends BaseOutputFormat<MovingWindowMatrix> {
    @Override
    public void write(MovingWindowMatrix movingWindowMatrix, File file) throws IOException {
        writeObj(movingWindowMatrix, file);
    }

    @Override
    public String write(MovingWindowMatrix movingWindowMatrix) throws IOException {
        return writeObj(movingWindowMatrix);
    }

    @Override
    public void write(MovingWindowMatrix movingWindowMatrix, OutputStream os) throws IOException {
        writeObj(movingWindowMatrix, os);
    }
}
