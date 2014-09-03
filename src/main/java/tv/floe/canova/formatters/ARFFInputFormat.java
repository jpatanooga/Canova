package tv.floe.canova.formatters;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;

/**
 * Created by mjk on 9/2/14.
 */
public abstract class ARFFInputFormat<T> extends BaseInputFormat<T>{
    static public final char[] RELATION = {'R','E','L','A','T','I','O','N'};
    static public final char[] DATA     = {'D','A','T','A'};
    static public final char[] CLASS    = {'c','l','a','s','s'};

    static private final String blank = new String();
    /**
     * InputStream is is assumed to be at the beginning of the actual data section
     * @param is
     * @param recordDelim
     * @param splitDelim
     * @param ret
     * @return a map that should be read as an ordered set
     * @throws IOException
     */
    public static Map<Integer,Double> parseRecord(InputStream is,
                                                String recordDelim,
                                                String splitDelim,
                                                Map<Integer,Double> ret) throws IOException {

        String l;
        l = readLine(recordDelim,is);
        if (l == null) {
            return ret;
        }

        String[] features = l.split(COMMA);

        Integer label = 0;
        try {
            String label_s = features[features.length - 1];
            label = label_s.hashCode();
        }  catch (NumberFormatException e) {
            return ret;
        }

        double label_d = (double) label;
        ret.put(0, label_d);

        for (int x = 1; x < features.length; x++) {

            double val = Double.parseDouble(features[x-1]);
            ret.put(x,val);
        }
        return ret;
    }

    public static Map<Integer,Double> parseHeader(InputStream is,
                                                 String recordDelim,
                                                 String splitDelim,
                                                 Map<Integer,Double> ret) throws IOException {
        String l;
        int dataLineNumber = 0;
        while ( (l = readLine(DELIM_NEWLINE, is)) != null ) {

            StringReader sis = new StringReader(l);
            int ch;
            while ( (ch = sis.read()) != -1) {
                if (ch == '%') {
                    break; // a comment, throw away rest of line
                } else
                if (ch == '@') {
                    String[] attributeLine = l.split(" ");
                    int i = 0;
                    break;
                } else {
                    // This is a 'data' element
                    int i;
                    i = 0;
                    break;
                }
            }
        }
        return ret;
    }

    private static int consumeChar(StringReader sis, char delim, int l) throws IOException {
        char[] a = readConsumeUntil(sis, delim, l, true);
        return 0;
    }
    private static char[] readUntil(StringReader sis, char delim, int l) throws IOException {
        return         readConsumeUntil(sis, delim, l, false);
    }
    private static char[] readConsumeUntil(StringReader sis, char delim, int l, boolean justConsume) throws IOException {
        int i = 0;
        sis.mark(l);
        int ch_a;
        ch_a = sis.read();
        while (ch_a == delim) {
            if (ch_a == -1)
                break;
            ch_a = sis.read();
            i++;
        }
        if (justConsume) {
            sis.reset();
            sis.skip(i);
            return null;
        }
        char[] attributeType = new char[i];
        sis.reset();
        sis.read(attributeType, 0, i);
        return attributeType;
    }

}