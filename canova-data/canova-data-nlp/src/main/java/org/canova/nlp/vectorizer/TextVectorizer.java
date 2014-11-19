package org.canova.nlp.vectorizer;

import org.canova.api.conf.Configuration;
import org.canova.api.records.reader.RecordReader;
import org.canova.api.vector.Vectorizer;
import org.canova.api.writable.Writable;
import org.canova.nlp.tokenization.tokenizer.Tokenizer;
import org.canova.nlp.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Baseline text vectorizer that includes some common elements
 * to text analysis such as the tokenizer factory
 *
 * @author Adam Gibson
 */
public abstract class TextVectorizer<VECTOR_TYPE> implements Vectorizer<VECTOR_TYPE> {

    protected TokenizerFactory tokenizerFactory;

    @Override
    public void initialize(Configuration conf) {
         tokenizerFactory = createTokenizerFactory(conf);
    }

    @Override
    public void fit(RecordReader reader) {
        while(reader.hasNext()) {
            Collection<Writable> record = reader.next();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(bos);
            for(Writable w : record) {
                try {
                    w.write(dos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String s = new String(bos.toByteArray());
            Tokenizer tokenizer = tokenizerFactory.create(s);
            doWithTokens(tokenizer);


        }
    }



    /**
     * Increment counts, add to collection,...
     * @param tokenizer
     */
    public abstract void doWithTokens(Tokenizer tokenizer);

    public abstract TokenizerFactory createTokenizerFactory(Configuration conf);

}
