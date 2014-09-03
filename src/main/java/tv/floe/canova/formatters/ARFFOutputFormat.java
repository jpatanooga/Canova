package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by mjk on 9/3/14.
 */
public abstract class ARFFOutputFormat<T> extends BaseInputFormat<T>  {
    @Override
    public abstract T read(InputStream is, String delim) throws IOException;

    @Override
    public abstract T read(String is, String delim) throws IOException;


    @Override
    public abstract T read(File file, String delim) throws IOException;

    public static String  parseWrite(Map<Integer, Double> m, String recordDelim, String splitDelim) {
        String ret = new String();
        if (m.isEmpty()) {
            return ret;
        }
        Set ns = m.keySet();
        Iterator itr = ns.iterator();
        String labelHash = new String();

        while (itr.hasNext()) {
            int key = (Integer) itr.next();
            double val = 0.0;

            if (key == 0) {
                double label = m.get(key);
                int label1 = (int) label;
                labelHash = String.valueOf(label1);
                continue;
            } else {
                val = (Double) m.get(key);
                ret += String.valueOf(val);
            }
            ret += splitDelim;

        }
        ret += labelHash;
        return ret;
    }
}
