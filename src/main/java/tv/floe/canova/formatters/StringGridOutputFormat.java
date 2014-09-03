package tv.floe.canova.formatters;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjk on 8/29/14.
 */
public class StringGridOutputFormat extends BaseOutputFormat<ArrayList<List<String>>> {

    private static final String COMMA = new String(",");
    private static final String NEWLINE = new String("\n");

    /**
     * writes a list of list of strings to a StringWriter delimited by delim
     * @param lists
     * @param delim
     * @return
     * @throws IOException
     */
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

    /**
     * writes a list of list of strings to a File delimited by a comma
     * @param lists
     * @param file
     * @throws IOException
     */
    @Override
    public void write(ArrayList<List<String>> lists, File file) throws IOException {
        this.write(lists, file, COMMA);
    }

    /**
     * writes a list of list of strings to a File delimited by delim
     * @param lists
     * @param file
     * @param delim
     * @throws IOException
     */
    public void write(ArrayList<List<String>> lists, File file, String delim) throws IOException {
        FileWriter filewriter = new FileWriter(file);

        StringWriter stringwriter = listsToString(lists, delim);

        filewriter.write(stringwriter.toString());
        filewriter.close();
    }

    /**
     * writes a list of list of strings to a string delimited by a comma
     * @param lists
     * @return
     * @throws IOException
     */
    @Override
    public String write(ArrayList<List<String>> lists) throws IOException {
        return this.write(lists, COMMA);
    }

    /**
     * writes a list of list of strings to a string delimited by delim
     * @param lists
     * @param delim
     * @return
     * @throws IOException
     */
    public String write(ArrayList<List<String>> lists, String delim) throws IOException {

        StringWriter stringwriter = listsToString(lists, delim);

        return stringwriter.toString();
    }

    /**
     * writes a list of list of strings to an output stream delimited by a comma
     * @param lists
     * @param os
     * @throws IOException
     */
    @Override
    public void write(ArrayList<List<String>> lists, OutputStream os) throws IOException {
        this.write(lists, os, COMMA);
    }

    /**
     * writes a list of list of strings to an output stream delimited by delim
     * @param lists
     * @param os
     * @param delim
     * @throws IOException
     */
    public void write(ArrayList<List<String>> lists, OutputStream os, String delim) throws IOException {
        StringWriter stringwriter = listsToString(lists, delim);

        StringWriter sw = listsToString(lists, delim);
        BufferedOutputStream bw = new BufferedOutputStream(os);

    }
}
