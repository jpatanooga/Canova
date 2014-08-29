package tv.floe.canova.formatters;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridInputFormat extends BaseInputFormat<ArrayList<List<String>>> {

    @Override
    public ArrayList<List<String>> read(InputStream is, String delim) throws IOException {
        ArrayList<List<String>> ourList = new ArrayList<List<String>>();


        int columns = 0;
        int rows = 0;
        List<String> l = IOUtils.readLines(is);
        for (String str : l) {
            List<String> data = new ArrayList<String>();

            columns = 0;
            String[] tokens = this.stringSplit(str, delim);
            float thisRow[] = new float[tokens.length];
            for (String ri: tokens) {
                data.add(ri);
                columns += 1;
            }
            rows += 1;
            ourList.add(data);
        }

        return ourList;

    }

    public ArrayList<List<String>> read(InputStream is) throws IOException {
        return read(is, new String(","));
    }

    @Override
    public ArrayList<List<String>> read(String is, String delim) throws IOException {
        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        return read(stream, delim);
    }

    public ArrayList<List<String>> read(String is) throws IOException {

        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        return read(stream, new String(","));
    }

    @Override
    public ArrayList<List<String>> read(File file, String delim) throws IOException {
        InputStream stream = new FileInputStream(file);
        return read(stream, delim);
    }

    public ArrayList<List<String>> read(File file) throws IOException {

        InputStream stream = new FileInputStream(file);
        return read(stream, new String(","));
    }
}
