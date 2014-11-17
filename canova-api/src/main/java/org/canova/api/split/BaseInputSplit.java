package org.canova.api.split;

import java.net.URI;

/**
 * Base input split
 *
 * @author Adam Gibson
 */
public abstract class BaseInputSplit implements InputSplit {

    protected URI[] locations;
    protected long length = 0;

    @Override
    public URI[] locations() {
        return locations;
    }

    @Override
    public long length() {
        return 0;
    }

}
