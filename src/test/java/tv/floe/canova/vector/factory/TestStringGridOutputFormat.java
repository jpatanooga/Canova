package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import tv.floe.canova.formatters.StringGridOutputFormat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

            byte[] frb = Files.readAllBytes(Paths.get(f.getCanonicalPath()));
            byte[] frbt = Files.readAllBytes(Paths.get(ft.getCanonicalPath()));

            assertEquals(Arrays.toString(frb), Arrays.toString(frbt));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


}