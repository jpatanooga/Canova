package org.canova.api.formats.output.impl;

import org.canova.api.conf.Configuration;
import org.canova.api.exceptions.CanovaException;
import org.canova.api.formats.output.OutputFormat;
import org.canova.api.records.writer.RecordWriter;
import org.canova.api.records.writer.impl.LineRecordWriter;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Line output format
 * @author Adam Gibson
 */
public class LineOutputFormat implements OutputFormat {
    @Override
    public RecordWriter createWriter(Configuration conf) throws CanovaException {
        String outputPath = conf.get(OutputFormat.OUTPUT_PATH,".");
        try {
            return new LineRecordWriter(new File(outputPath));
        } catch (FileNotFoundException e) {
            throw new CanovaException(e);
        }
    }
}
