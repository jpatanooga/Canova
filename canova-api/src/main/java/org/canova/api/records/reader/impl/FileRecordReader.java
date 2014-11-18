package org.canova.api.records.reader.impl;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.canova.api.io.data.Text;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.split.InputSplit;
import org.canova.api.writable.Writable;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.*;

/**
 * File reader/writer
 *
 * @author Adam Gibson
 */
public class FileRecordReader implements RecordReader {

    private URI[] locations;
    private int currIndex = 0;
    private Iterator<File> iter;


    @Override
    public void initialize(InputSplit split) throws IOException, InterruptedException {
        this.locations = split.locations();
        if(locations != null && locations.length > 0) {
            iter = FileUtils.iterateFiles(new File(locations[0]),null,true);
        }

    }

    @Override
    public Collection<Writable> next() {
        List<Writable> ret = new ArrayList<>();
        try {
            ret.add(new Text(FileUtils.readFileToString(iter.next())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(iter.hasNext()) {
            return ret;
        }
        else {
            currIndex++;
            try {
                close();
                iter = IOUtils.lineIterator(new InputStreamReader(locations[currIndex].toURL().openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(iter.hasNext()) {
                try {
                    ret.add(new Text(FileUtils.readFileToString(iter.next())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ret;
            }

        }

        throw new NoSuchElementException("No more elements found!");
    }

    @Override
    public boolean hasNext() {
        return iter != null && iter.hasNext();
    }

    @Override
    public void close() throws IOException {

    }
}
