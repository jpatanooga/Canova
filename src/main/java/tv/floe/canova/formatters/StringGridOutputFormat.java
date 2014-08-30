package tv.floe.canova.formatters;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridOutputFormat extends BaseOutputFormat<ArrayList<List<String>>> {

    private static String COMMA = new String(",");
    private static String NEWLINE = new String("\n");

    private StringWriter listsToString(ArrayList<List<String>> lists, String delim) throws IOException {
        StringWriter stringwriter = new StringWriter();
        for (List<String> l : lists) {
            boolean firstPass = true;
            for (String s : l) {
                if (firstPass == true) {
                    firstPass = false;
                } else {
                    IOUtils.write(delim, stringwriter);
                }
                IOUtils.write(s, stringwriter);
            }
            IOUtils.write(NEWLINE, stringwriter);
        }
        return stringwriter;
    }

    @Override
    public void write(ArrayList<List<String>> lists, File file) throws IOException {
        this.write(lists, file, COMMA);
    }

    public void write(ArrayList<List<String>> lists, File file, String delim) throws IOException {
        FileWriter filewriter = new FileWriter(file);

        StringWriter stringwriter = listsToString(lists, delim);

        filewriter.write(stringwriter.toString());
        filewriter.close();
    }

    @Override
    public String write(ArrayList<List<String>> lists, String str) throws IOException {
        return this.write(lists, str, COMMA);
    }

    public String write(ArrayList<List<String>> lists, String str, String delim) throws IOException {

        StringWriter stringwriter = listsToString(lists, delim);

        return stringwriter.toString();
    }

    @Override
    public void write(ArrayList<List<String>> lists, OutputStream os) throws IOException {
        this.write(lists, os, COMMA);
    }

    public void write(ArrayList<List<String>> lists, OutputStream os, String delim) throws IOException {
        StringWriter stringwriter = listsToString(lists, delim);

        StringWriter sw = listsToString(lists, delim);
        BufferedOutputStream bw = new BufferedOutputStream(os);

    }
}
