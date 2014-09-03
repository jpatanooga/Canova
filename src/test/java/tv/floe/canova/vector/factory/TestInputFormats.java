package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.deeplearning4j.linalg.jblas.NDArray;
import org.junit.Test;
import tv.floe.canova.formatters.LibSVMInputFormat;
import tv.floe.canova.formatters.LibSVMOutputFormat;
import tv.floe.canova.formatters.NDArrayInputFormat;
import tv.floe.canova.formatters.NDArrayOutputFormat;

import java.io.*;
import java.util.concurrent.ConcurrentSkipListMap;

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
    public void testLibSVM() {
        String a = "-1 3:1.0 11:1.3 14:1.0 19:1.0 39:1.0 42:1.0 \n-1 55:1.0 64:1.0 67:1.0 73:1.0 75:1.0 76:1.0 80:1.0 83:1.0 ";
        String[] a_s = a.split("\n");
        InputStream is = new ByteArrayInputStream( a.getBytes(  ) );
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String m_out = null;
        ConcurrentSkipListMap<Integer, Double> m = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<Integer, Double> m1 = new ConcurrentSkipListMap<>();

        try {
            m = (ConcurrentSkipListMap<Integer, Double>) LibSVMInputFormat.parseRead(is,
                    LibSVMInputFormat.DELIM_NEWLINE,
                    LibSVMInputFormat.DELIM_COLON,
                    m);

             m_out = LibSVMOutputFormat.parseWrite(m,
                    LibSVMOutputFormat.DELIM_NEWLINE,
                    LibSVMOutputFormat.DELIM_COLON);
            assertEquals(a_s[0], m_out);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            m1= (ConcurrentSkipListMap<Integer, Double>) LibSVMInputFormat.parseRead(is,
                    LibSVMInputFormat.DELIM_NEWLINE,
                    LibSVMInputFormat.DELIM_COLON,
                    m1);

            m_out = LibSVMOutputFormat.parseWrite(m1,
                    LibSVMOutputFormat.DELIM_NEWLINE,
                    LibSVMOutputFormat.DELIM_COLON);

            assertEquals(a_s[1], m_out);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
