package org.canova.nd4j.nlp.vectorizer;

import org.canova.api.berkeley.Counter;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.vector.Vectorizer;
import org.canova.api.writable.Writable;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Nd4j tfidf vectorizer
 *
 * @author Adam Gibson
 */
public class TfidfVectorizer extends org.canova.nlp.vectorizer.TfidfVectorizer<INDArray> {
    @Override
    public INDArray createVector(Object[] args) {
        INDArray ret = Nd4j.create(cache.vocabWords().size());
        Counter<String> docFrequencies = (Counter<String>)args[0];
        for(int i = 0; i < cache.vocabWords().size(); i++) {
            double freq = docFrequencies.getCount(cache.wordAt(i));
            double tfidf = cache.tfidf(cache.wordAt(i),freq);
            ret.putScalar(i,tfidf);
        }
        return ret;
    }

    @Override
    public INDArray fitTransform(RecordReader reader) {
        return fitTransform(reader,null);
    }

    @Override
    public INDArray fitTransform(RecordReader reader, RecordCallBack callBack) {
        final List<Collection<Writable>> records = new ArrayList<>();

        fit(reader,new RecordCallBack() {
            @Override
            public void onRecord(Collection<Writable> record) {
                records.add(record);
            }
        });

        INDArray ret = Nd4j.create(records.size(),cache.vocabWords().size());
        int i = 0;
        for(Collection<Writable> record : records) {
            ret.putRow(i++, transform(record));
            if(callBack != null)
                callBack.onRecord(record);
        }

        return ret;
    }

    @Override
    public INDArray transform(Collection<Writable> record) {
        Counter<String> wordFrequencies = wordFrequenciesForRecord(record);
        return createVector(new Object[]{wordFrequencies});

    }
}
