package tv.floe.canova.formatters;

import java.io.*;

/**
 * Created by mjk on 9/1/14.
 */
public class LibSVMInputFormat extends BaseInputFormat<String> {
    private static final String SPACE = new String(" ");

    @Override
    public String read(InputStream is, String delim) throws IOException {
        String ret = new String();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String l;
        while ( (l = reader.readLine()) != null ) {
            String[] parts = l.split(SPACE);
            for (String sbsr: parts) {
                String[] feature = sbsr.split(":");
                int index = Integer.parseInt(feature[0]);
                double val = Double.parseDouble(feature[1]);
            }
        }
        return null;
    }
    public String read(InputStream is) throws IOException {
        return read(is, SPACE);
    }

    @Override
    public String read(String is, String delim) throws IOException {
        return null;
    }

    @Override
    public String read(File file, String delim) throws IOException {
        return null;
    }
}
