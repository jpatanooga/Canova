package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.deeplearning4j.linalg.jblas.NDArray;
import org.deeplearning4j.util.MovingWindowMatrix;
import org.junit.Test;
import tv.floe.canova.formatters.MovingWindowMatrixInputFormat;
import tv.floe.canova.formatters.MovingWindowMatrixOutputFormat;
import tv.floe.canova.formatters.NDArrayInputFormat;
import tv.floe.canova.formatters.NDArrayOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void testMatrixWindowFormat() {
        NDArray cv = new NDArray(new float[2], new int[]{1,2});
        NDArray cv_d = new NDArray(new float[3], new int[]{1,2,3});

        NDArray cv1;

        List<NDArray> l1 = new ArrayList<>();
        List<NDArray> l2 = new ArrayList<>();

        l1.add(cv);
        l2.add(cv_d);

        MovingWindowMatrix mo = new MovingWindowMatrix(cv, 1, 1);
        MovingWindowMatrix mo1 = new MovingWindowMatrix(cv, 1, 1);

        MovingWindowMatrix mo2 = new MovingWindowMatrix(cv_d, 1, 1);

        MovingWindowMatrixInputFormat cvi = new MovingWindowMatrixInputFormat();
        MovingWindowMatrixOutputFormat cvo = new MovingWindowMatrixOutputFormat();

        String o = new String();
        try {
            o = cvo.write(mo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mo1 = cvi.read(o);
            assertEquals(mo1, mo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
