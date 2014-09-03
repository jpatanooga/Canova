package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.junit.Test;
import tv.floe.canova.formatters.LibSVMInputFormat;
import tv.floe.canova.formatters.LibSVMOutputFormat;

import java.io.*;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by mjk on 8/31/14.
 */
public class TestInputFormats extends TestCase {


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
