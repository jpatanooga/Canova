package org.canova.api.formats.output.impl;

import org.canova.api.conf.Configuration;
import org.canova.api.formats.output.OutputFormat;
import org.canova.api.records.writer.RecordWriter;
import org.canova.api.records.writer.impl.LineRecordWriter;

/**
 * Line output format
 * @author Adam Gibson
 */
public class LineOutputFormat implements OutputFormat {
    @Override
    public RecordWriter createWriter(Configuration conf) {
        return new LineRecordWriter();
    }
}
