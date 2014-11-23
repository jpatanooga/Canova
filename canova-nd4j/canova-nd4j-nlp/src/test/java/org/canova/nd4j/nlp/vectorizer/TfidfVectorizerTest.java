package org.canova.nd4j.nlp.vectorizer;

import static org.junit.Assert.*;

import org.canova.api.conf.Configuration;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.records.reader.impl.CollectionRecordReader;
import org.canova.api.writable.Writables;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by agibsonccc on 11/23/14.
 */
public class TfidfVectorizerTest {

    private static Logger log = LoggerFactory.getLogger(TfidfVectorizerTest.class);

    @Test
    public void testTfidfVectorizer() {
        TfidfVectorizer vectorizer = new TfidfVectorizer();
        vectorizer.initialize(new Configuration());
        RecordReader reader = new CollectionRecordReader(Writables.writables(Arrays.asList("Testing one.", "Testing 2.")));
        INDArray n = vectorizer.fitTransform(reader);
        //number of vocab words is 3
        assertEquals(3,n.columns());
        //number of records is 2
        assertEquals(2,n.rows());
    }

}
