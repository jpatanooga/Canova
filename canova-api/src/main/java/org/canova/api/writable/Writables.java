package org.canova.api.writable;

import org.canova.api.io.data.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Util class for writable conversions
 *
 * @author Adam Gibson
 */
public class Writables {


    public static List<List<Writable>> writables(Collection<String> collection) {
        List<List<Writable>> ret = new ArrayList<>();
        for(String s : collection) {
            ret.add(Arrays.<Writable>asList(new Text(s)));
        }

        return ret;
    }



}
