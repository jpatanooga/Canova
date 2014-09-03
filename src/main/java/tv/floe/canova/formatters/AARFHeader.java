package tv.floe.canova.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mjk on 9/2/14.
 */
public class AARFHeader {
    List<String> attribute;
    Map<Integer,String> classMap;
    Map<Integer,List<Double>> dataMap;

    public AARFHeader() {
        attribute = new ArrayList<>();
        classMap = new HashMap<>();
        dataMap = new HashMap<>();
    }

}
