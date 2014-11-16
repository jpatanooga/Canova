package org.canova.api.io.serializers;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A factory for {@link Serialization}s.
 * </p>
 */
public class SerializationFactory extends Configured {

    private static final Log LOG =
            LogFactory.getLog(SerializationFactory.class.getName());

    private List<Serialization<?>> serializations = new ArrayList<Serialization<?>>();

    /**
     * <p>
     * Serializations are found by reading the <code>io.serializations</code>
     * property from <code>conf</code>, which is a comma-delimited list of
     * classnames.
     * </p>
     */
    public SerializationFactory(Configuration conf) {
        super(conf);
        for (String serializerName : conf.getStrings("io.serializations",
                new String[]{"org.apache.hadoop.io.serializer.WritableSerialization"})) {
            add(conf, serializerName);
        }
    }

    @SuppressWarnings("unchecked")
    private void add(Configuration conf, String serializationName) {
        try {

            Class<? extends Serialization> serializionClass =
                    (Class<? extends Serialization>) conf.getClassByName(serializationName);
            serializations.add((Serialization)
                    ReflectionUtils.newInstance(serializionClass, getConf()));
        } catch (ClassNotFoundException e) {
            LOG.warn("Serilization class not found: " +
                    StringUtils.stringifyException(e));
        }
    }

    public <T> Serializer<T> getSerializer(Class<T> c) {
        return getSerialization(c).getSerializer(c);
    }

    public <T> Deserializer<T> getDeserializer(Class<T> c) {
        return getSerialization(c).getDeserializer(c);
    }

    @SuppressWarnings("unchecked")
    public <T> Serialization<T> getSerialization(Class<T> c) {
        for (Serialization serialization : serializations) {
            if (serialization.accept(c)) {
                return (Serialization<T>) serialization;
            }
        }
        return null;
    }
}