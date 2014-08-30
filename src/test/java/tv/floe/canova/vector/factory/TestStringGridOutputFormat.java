package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import tv.floe.canova.formatters.StringGridOutputFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class TestStringGridOutputFormat extends TestCase {
    private ArrayList<List<String>> abcdef() {
        List<String> l1 = Arrays.asList("a", "b", "c");
        List<String> l2 = Arrays.asList("d", "e", "f");

        ArrayList<List<String>> pos = new ArrayList<List<String>>();
        pos.add(l1);
        pos.add(l2);
        return pos;
    }

    public void testConvertVectorFile() {
        StringGridOutputFormat e = new StringGridOutputFormat();
        File f = new File("src/test/java/tv/floe/canova/vector/factory/TestStringGridInputFormat.txt");
        File ft = new File("src/test/java/tv/floe/canova/vector/factory/TestStringGridInputFormat-tst.txt");
        ArrayList<List<String>> a = abcdef();
        try {
            e.write(a, ft);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            Process p = Runtime.getRuntime().exec(new String("/usr/bin/md5sum ") + f.getCanonicalPath());
            Process p1 = Runtime.getRuntime().exec(new String("/usr/bin/md5sum ") + ft.getCanonicalPath());
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdInput1 = new BufferedReader(new
                    InputStreamReader(p1.getInputStream()));
            assertEquals(stdInput.readLine().split(" ")[0], stdInput1.readLine().split(" ")[0]);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}