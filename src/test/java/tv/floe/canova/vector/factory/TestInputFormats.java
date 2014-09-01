package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.deeplearning4j.linalg.jblas.NDArray;
import org.junit.Test;
import tv.floe.canova.formatters.NDArrayInputFormat;
import tv.floe.canova.formatters.NDArrayOutputFormat;

import java.io.File;
import java.io.IOException;

/**
 * Created by mjk on 8/31/14.
 */
public class TestInputFormats extends TestCase {

    @Test
    public void testStringFormats() {

        NDArray cv = new NDArray(new float[2], new int[]{1,2});
        NDArray cv_d = new NDArray(new float[3], new int[]{1,2,3});

        NDArray cv1;

        NDArrayInputFormat cvi = new NDArrayInputFormat();
        NDArrayOutputFormat cvo = new NDArrayOutputFormat();

        String o = new String();
        try {
            o = cvo.write(cv);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cv1 = cvi.read(o);
            assertEquals(cv, cv1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testFileFormats() {
        NDArray cv = new NDArray(new float[2], new int[]{1,2});
        NDArray cv_d = new NDArray(new float[3], new int[]{1,2,3});

        NDArray cv1;

        NDArrayInputFormat cvi = new NDArrayInputFormat();
        NDArrayOutputFormat cvo = new NDArrayOutputFormat();

        File f = new File("./asdf");
        try {
            cvo.write(cv,f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            NDArray cv2;
            cv2 = cvi.read(f);
            assertEquals(cv, cv2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.delete();
    }
}
