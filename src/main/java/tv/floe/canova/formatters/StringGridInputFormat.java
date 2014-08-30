package tv.floe.canova.formatters;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridInputFormat extends BaseInputFormat<List<List<String>>> {

    /**
     * Reads from an input stream line by line delimited by delim and returns a list of list of strings
     * @param is
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public List<List<String>> read(InputStream is, String delim) throws IOException {
        List<List<String>> ourList = new ArrayList<List<String>>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String l;
        while ( (l = reader.readLine()) != null ) {
            List<String> data = new ArrayList<String>();

            String[] tokens = this.stringSplit(l, delim);
            float thisRow[] = new float[tokens.length];
            for (String ri: tokens) {
                data.add(ri);
            }
            ourList.add(data);
        }

        return ourList;

    }

    /**
     * Reads from an input stream line by line delimited by a comma and returns a list of list of strings
     * @param is
     * @return
     * @throws IOException
     */
    public List<List<String>> read(InputStream is) throws IOException {
        return read(is, new String(","));
    }

    /**
     * Reads from a string line by line delimited by delim and returns a list of list of strings
     * @param is
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public List<List<String>> read(String is, String delim) throws IOException {
        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));
        List<List<String>> ret = read(stream, delim);
        stream.close();
        return ret;
    }

    /**
     * Reads from a string line by line delimited by a comma and returns a list of list of strings
     * @param is
     * @return
     * @throws IOException
     */
    public List<List<String>> read(String is) throws IOException {

        InputStream stream = new ByteArrayInputStream(is.getBytes(StandardCharsets.UTF_8));

        List<List<String>> ret = read(stream);
        stream.close();
        return ret;
    }

    /**
     * Reads from a file line by line delimited by delim and returns a list of list of strings
     * @param file
     * @param delim
     * @return
     * @throws IOException
     */
    @Override
    public List<List<String>> read(File file, String delim) throws IOException {
        InputStream stream = new FileInputStream(file);
        List<List<String>> ret = read(stream, delim);
        stream.close();
        return ret;
    }

    /**
     * Reads from a file line by line delimited by a comma and returns a list of list of strings
     * @param file
     * @return
     * @throws IOException
     */
    public List<List<String>> read(File file) throws IOException {

        InputStream stream = new FileInputStream(file);
        List<List<String>> ret = read(stream);
        stream.close();
        return ret;
    }
}
