package org.canova.nlp.vectorizer;

import org.canova.api.conf.Configuration;
import org.canova.nlp.tokenization.tokenizer.Tokenizer;
import org.canova.nlp.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.canova.nlp.tokenization.tokenizerfactory.TokenizerFactory;

import java.util.HashSet;
import java.util.Set;


/**
 * Tf idf vectorizer
 * @author Adam Gibson
 */
public abstract class TfidfVectorizer<VECTOR_TYPE> extends TextVectorizer<VECTOR_TYPE> {




    @Override
    public void doWithTokens(Tokenizer tokenizer) {
        Set<String> seen = new HashSet<>();
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            cache.incrementCount(token);
            if(!seen.contains(token)) {
                cache.incrementDocCount(token);
            }
        }
    }

    @Override
    public TokenizerFactory createTokenizerFactory(Configuration conf) {
        return new DefaultTokenizerFactory();
    }

    



}
