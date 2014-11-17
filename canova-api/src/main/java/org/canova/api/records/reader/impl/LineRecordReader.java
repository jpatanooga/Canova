package org.canova.api.records.reader.impl;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.canova.api.io.data.Text;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.split.InputSplit;
import org.canova.api.writable.Writable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Reads files line by line
 *
 * @author Adam Gibson
 */
public class LineRecordReader implements RecordReader {


    private URI[] locations;
    private int currIndex = 0;
    private LineIterator iter;


    @Override
    public void initialize(InputSplit split) throws IOException, InterruptedException {
        this.locations = split.locations();
        if(locations != null && locations.length > 0) {
            iter = IOUtils.lineIterator(new InputStreamReader(locations[0].toURL().openStream()));
        }

    }

    @Override
    public Collection<Writable> next() {
        List<Writable> ret = new ArrayList<>();
        if(iter.hasNext()) {
            ret.add(new Text(iter.nextLine()));
            return ret;
        }
        else {
            currIndex++;
            iter.close();
            try {
                iter = IOUtils.lineIterator(new InputStreamReader(locations[currIndex].toURL().openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(iter.hasNext()) {
                ret.add(new Text(iter.nextLine()));
                return ret;
            }

        }

        throw new NoSuchElementException("No more elements found!");
    }

    @Override
    public boolean hasNext() {
        return iter != null && iter.hasNext();
    }
}
