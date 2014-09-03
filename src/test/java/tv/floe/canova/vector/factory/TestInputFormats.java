package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.junit.Test;
import tv.floe.canova.formatters.ARFFInputFormat;
import tv.floe.canova.formatters.ARFFOutputFormat;
import tv.floe.canova.formatters.LibSVMInputFormat;
import tv.floe.canova.formatters.LibSVMOutputFormat;

import java.io.*;
import java.util.HashMap;
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
            m = (ConcurrentSkipListMap<Integer, Double>) LibSVMInputFormat.parseRecord(is,
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
            m1= (ConcurrentSkipListMap<Integer, Double>) LibSVMInputFormat.parseRecord(is,
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
        try {
            m1= (ConcurrentSkipListMap<Integer, Double>) LibSVMInputFormat.parseRecord(is,
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

    @Test
    public void testAARF() {
        String a = "" +
                "% 1. Title: Iris Plants Database\n" +
                "% \n" +
                "% 2. Sources:\n" +
                "%      (a) Creator: R.A. Fisher\n" +
                "%      (b) Donor: Michael Marshall (MARSHALL%PLU@io.arc.nasa.gov)\n" +
                "%      (c) Date: July, 1988\n" +
                "% \n" +
                "@RELATION iris\n" +
                "\n" +
                "@ATTRIBUTE sepallength  NUMERIC\n" +
                "@ATTRIBUTE sepalwidth   NUMERIC\n" +
                "@ATTRIBUTE petallength  NUMERIC\n" +
                "@ATTRIBUTE petalwidth   NUMERIC\n" +
                "@ATTRIBUTE class        {Iris-setosa,Iris-versicolor,Iris-virginica}";
        String b = "" +
                "5.1,3.5,1.4,0.2,Iris-setosa\n" +
                "4.9,3.0,1.4,0.2,Iris-setosa\n" +
                "4.7,3.2,1.3,0.2,Iris-setosa\n" +
                "4.6,3.1,1.5,0.2,Iris-setosa\n" +
                "5.0,3.6,1.4,0.2,Iris-setosa\n" +
                "5.4,3.9,1.7,0.4,Iris-setosa\n" +
                "4.6,3.4,1.4,0.3,Iris-setosa\n" +
                "5.0,3.4,1.5,0.2,Iris-setosa\n" +
                "4.4,2.9,1.4,0.2,Iris-setosa\n" +
                "4.9,3.1,1.5,0.1,Iris-setosa";
        HashMap<Integer, Double> m = new HashMap<>();
        InputStream is = new ByteArrayInputStream( a.getBytes(  ) );
        InputStream is1= new ByteArrayInputStream( b.getBytes(  ) );

        try {
            m = (HashMap<Integer, Double>) ARFFInputFormat.parseHeader(is,
                    ARFFInputFormat.DELIM_NEWLINE,
                    ARFFInputFormat.DELIM_NEWLINE,
                    m);
            m = (HashMap<Integer, Double>) ARFFInputFormat.parseRecord(is1,
                    ARFFInputFormat.DELIM_NEWLINE,
                    ARFFInputFormat.DELIM_NEWLINE,
                    m);
            String m_out = ARFFOutputFormat.parseWrite(m,ARFFOutputFormat.DELIM_NEWLINE,
                    ARFFOutputFormat.DELIM_COMMA);
            String m_exp = new String("5.1,3.5,1.4,0.2,-828411339");
            assertEquals(m_out, m_exp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
