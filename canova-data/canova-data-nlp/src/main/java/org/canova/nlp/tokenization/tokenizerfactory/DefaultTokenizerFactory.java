package org.canova.nlp.tokenization.tokenizerfactory;



import org.canova.nlp.tokenization.tokenizer.DefaultStreamTokenizer;
import org.canova.nlp.tokenization.tokenizer.DefaultTokenizer;
import org.canova.nlp.tokenization.tokenizer.TokenPreProcess;
import org.canova.nlp.tokenization.tokenizer.Tokenizer;

import java.io.InputStream;

/**
 * Default tokenizer based on string tokenizer or stream tokenizer
 * @author Adam Gibson
 */
public class DefaultTokenizerFactory implements TokenizerFactory {

    private TokenPreProcess tokenPreProcess;

    @Override
    public Tokenizer create(String toTokenize) {
        DefaultTokenizer t =  new DefaultTokenizer(toTokenize);
        t.setTokenPreProcessor(tokenPreProcess);
        return t;
    }

    @Override
    public Tokenizer create(InputStream toTokenize) {
        Tokenizer t =  new DefaultStreamTokenizer(toTokenize);
        t.setTokenPreProcessor(tokenPreProcess);
        return t;
    }

    @Override
    public void setTokenPreProcessor(TokenPreProcess preProcessor) {
        this.tokenPreProcess = preProcessor;
    }


}
