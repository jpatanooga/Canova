package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.junit.Test;
import tv.floe.canova.formatters.StringGridInputFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class TestStringGridInputFormat extends TestCase {
    private ArrayList<List<String>> abcdef() {
        List<String> l1 = Arrays.asList("a", "b", "c");
        List<String> l2 = Arrays.asList("d", "e", "f");

        ArrayList<List<String>> pos = new ArrayList<List<String>>();
        pos.add(l1);
        pos.add(l2);
        return pos;
    }
    @Test
    public void testConvertVectorFile() {
        StringGridInputFormat e = new StringGridInputFormat();
        File f = new File("src/test/java/tv/floe/canova/vector/factory/test.txt");
        System.out.printf("%s", f.getAbsolutePath());
        ArrayList<List<String>> a = new ArrayList<List<String>>();
        try {
            a = e.read(f);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ArrayList<List<String>> pos = abcdef();

        assertEquals(pos,a);

    }
    @Test
    public void testConvertVectorString() {
        StringGridInputFormat e = new StringGridInputFormat();
        String s = new String("a,b,c\nd,e,f");
        ArrayList<List<String>> a = new ArrayList<List<String>>();

        try {
            a = e.read(s);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ArrayList<List<String>> pos = abcdef();

        assertEquals(pos,a);
    }
}
