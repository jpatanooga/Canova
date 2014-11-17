package org.canova.api.formats.output.impl;

import org.canova.api.conf.Configuration;
import org.canova.api.formats.output.OutputFormat;
import org.canova.api.records.writer.RecordWriter;
import org.canova.api.records.writer.impl.CSVRecordWriter;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Creates an @link{CSVRecordWriter}
 *
 * @author Adam Gibson
 */
public class CSVOutputFormat implements OutputFormat {
    @Override
    public RecordWriter createWriter(Configuration conf) {
        String outputPath = conf.get(OutputFormat.OUTPUT_PATH,".");
        try {
            return new CSVRecordWriter(new File(outputPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
