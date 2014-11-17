package org.canova.api.split;

import org.apache.commons.io.FileUtils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collection;

/**
 * File input split. Splits up a root directory in to files.
 * @author Adam Gibson
 */
public class FileSplit extends BaseInputSplit {

    public FileSplit(File rootDir) {
        if(rootDir == null && rootDir.exists())
            throw new IllegalArgumentException("File must not be null");

        if(rootDir.isDirectory()) {
            Collection<File> subFiles = FileUtils.listFiles(rootDir, null, true);
            locations = new URI[subFiles.size()];
            int count = 0;
            for(File f : subFiles) {
                locations[count++] = f.toURI();
                length += f.length();
            }
        }
        else {
            locations = new URI[1];
            locations[0] = rootDir.toURI();
            length += rootDir.length();

        }

    }


    @Override
    public long length() {
        return length;
    }


    @Override
    public void write(DataOutput out) throws IOException {

    }

    @Override
    public void readFields(DataInput in) throws IOException {

    }
}
