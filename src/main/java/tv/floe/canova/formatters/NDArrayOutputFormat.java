package tv.floe.canova.formatters;

import org.deeplearning4j.linalg.jblas.NDArray;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by mjk on 8/31/14.
 */
public class NDArrayOutputFormat extends BaseOutputFormat<NDArray> {
    @Override
    public void write(NDArray ndArray, File file) throws IOException {
         writeObj(ndArray, file);
    }

    @Override
    public String write(NDArray ndArray) throws IOException {
        return writeObj(ndArray);
    }

    @Override
    public void write(NDArray ndArray, OutputStream os) throws IOException {
         writeObj(ndArray, os);
    }
}
