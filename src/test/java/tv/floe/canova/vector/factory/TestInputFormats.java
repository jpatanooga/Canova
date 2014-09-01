package tv.floe.canova.vector.factory;

import junit.framework.TestCase;
import org.deeplearning4j.linalg.jblas.NDArray;
import org.deeplearning4j.topicmodeling.CountVectorizer;
import org.deeplearning4j.util.Index;
import org.deeplearning4j.word2vec.sentenceiterator.SentenceIterator;
import org.deeplearning4j.word2vec.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.word2vec.tokenizer.Tokenizer;
import org.deeplearning4j.word2vec.tokenizer.TokenizerFactory;
import org.junit.Test;
import tv.floe.canova.formatters.CountVectorizerInputFormat;
import tv.floe.canova.formatters.CountVectorizerOutputFormat;
import tv.floe.canova.formatters.NDArrayInputFormat;
import tv.floe.canova.formatters.NDArrayOutputFormat;

import java.io.IOException;

/**
 * Created by mjk on 8/31/14.
 */
public class TestInputFormats extends TestCase {

    @Test
    public void testCountVectorizer() {
         SentenceIterator iter = new SentenceIterator() {
             @Override
             public String nextSentence() {
                 return null;
             }

             @Override
             public boolean hasNext() {
                 return false;
             }

             @Override
             public void reset() {

             }

             @Override
             public void finish() {

             }

             @Override
             public SentencePreProcessor getPreProcessor() {
                 return null;
             }

             @Override
             public void setPreProcessor(SentencePreProcessor sentencePreProcessor) {

             }
         };
         TokenizerFactory tokenizerFactory = new TokenizerFactory() {
             @Override
             public Tokenizer create(String s) {
                 return null;
             }
         };
         Index wordsToCount = new Index();

        if (false) {
            CountVectorizer cv = new CountVectorizer(iter, tokenizerFactory, wordsToCount);
            CountVectorizer cv1;

            CountVectorizerInputFormat cvi = new CountVectorizerInputFormat();
            CountVectorizerOutputFormat cvo = new CountVectorizerOutputFormat();

            String o = new String();
            try {
                o = cvo.write(cv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (true) {
            NDArray cv = new NDArray(new float[2], new int[]{1,2});
            NDArray cv1;

            NDArrayInputFormat cvi = new NDArrayInputFormat();
            NDArrayOutputFormat cvo = new NDArrayOutputFormat();

            String o = new String();
            try {
                o = cvo.write(cv);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
