package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.junit.Test;
import tv.floe.canova.formatters.StringGridInputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class TestStringGridInputFormat extends TestCase {
    @Test
    public void testConvertVector() {
        StringGridInputFormat e = new StringGridInputFormat();
        String s = new String("a,b,c\nd,e,f");
        ArrayList<List<String>> a = new ArrayList<List<String>>();

        try {
            a = e.read(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.print(a.toString());
    }
}
