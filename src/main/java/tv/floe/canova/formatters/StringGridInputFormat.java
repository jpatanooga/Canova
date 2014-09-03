package tv.floe.canova.formatters;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridInputFormat extends BaseInputFormat<List<List<String>>> {

    @Override
    public List<List<String>> read(InputStream is, String delim) throws IOException {
        List<List<String>> ourList = new ArrayList<List<String>>();



        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<String> l = IOUtils.readLines(reader);

        for (String str : l) {
            List<String> data = new ArrayList<String>();

            String[] tokens = this.stringSplit(str, delim);
            float thisRow[] = new float[tokens.length];
            for (String ri: tokens) {
                data.add(ri);
            }
            ourList.add(data);
        }

        return ourList;

    }

    public List<List<String>> read(InputStream is) throws IOException {
        return read(is, new String(","));
    }

    @Override
    public List<List<String>> read(String is, String delim) throws IOException {
        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        return read(stream, delim);
    }

    public List<List<String>> read(String is) throws IOException {

        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        return read(stream, new String(","));
    }

    @Override
    public List<List<String>> read(File file, String delim) throws IOException {
        InputStream stream = new FileInputStream(file);
        return read(stream, delim);
    }

    public List<List<String>> read(File file) throws IOException {

        InputStream stream = new FileInputStream(file);
        return read(stream, new String(","));
    }
}
