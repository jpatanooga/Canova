package org.canova.api.formats.output;

import org.canova.api.conf.Configuration;
import org.canova.api.exceptions.CanovaException;
import org.canova.api.records.writer.RecordWriter;

/**
 * Create a record writer
 * @author Adam Gibson
 */
public interface OutputFormat {

    public static final String OUTPUT_PATH = "org.canova.outputpath";

    /**
     * Create a record writer
     * @return the created writer
     */
    RecordWriter createWriter(Configuration conf) throws CanovaException;

}
