package tv.floe.canova.formatters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by mjk on 9/2/14.
 */
public abstract class LibSVMOutputFormat<T> extends BaseInputFormat<T> {
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
        Set ns=  m.keySet();
        Iterator itr=ns.iterator();
        while (itr.hasNext()) {
            int key = (Integer) itr.next();
            double val = 0.0;

            if (key == 0) {
                double label = m.get(key);
                int label1 = (int)label;
                ret += String.valueOf(label1);
            } else {
                ret += String.valueOf(key);
                val = (Double) m.get(key);
                ret += splitDelim;
                ret += String.valueOf(val);
            }
            ret += SPACE;
        }

        return ret;
    }
}
