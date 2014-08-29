package tv.floe.canova.formatters;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridInputFormat extends BaseInputFormat<ArrayList<List<String>>> {

    public ArrayList<List<String>> read(InputStream is, String delim) throws IOException {
        ArrayList<List<String>> ourList = new ArrayList<List<String>>();


        int columns = 0;
        int rows = 0;
        List<String> l = IOUtils.readLines(is);
        for (String str : l) {
            List<String> data = new ArrayList<String>();

            columns = 0;
            String[] tokens = this.stringSplit(str, new String(" "));
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

    @Override
    public ArrayList<List<String>> read(InputStream is) throws IOException {
        return read(is, new String(","));
    }

    @Override
    public ArrayList<List<String>> read(String is) throws IOException {
        ArrayList<List<String>> ourList = new ArrayList<List<String>>();


        int columns = 0;
        int rows = 0;
        List<String> l = IOUtils.readLines(is);

        return ourList;
    }

    @Override
    public ArrayList<List<String>> read(File file) throws IOException {
        return null;
    }
}
