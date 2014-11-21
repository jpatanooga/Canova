package org.canova.nlp.metadata;

import org.canova.api.berkeley.Counter;
import org.canova.api.conf.Configuration;
import org.canova.nlp.vectorizer.TextVectorizer;

import java.util.List;

/**
 * Created by agibsonccc on 11/21/14.
 */
public class DefaultVocabCache implements VocabCache {

    private Counter<String> wordFrequencies = new Counter<>();
    private Counter<String> docFrequencies = new Counter<>();
    private int minWordFrequency;
    private List<String> vocabWords;

    @Override
    public void initialize(Configuration conf) {
        minWordFrequency = conf.getInt(TextVectorizer.MIN_WORD_FREQUENCY,5);
    }

    @Override
    public double wordFrequency(String word) {
        return wordFrequencies.getCount(word);
    }

    @Override
    public int minWordFrequency() {
        return minWordFrequency;
    }

    @Override
    public List<String> vocabWords() {
        return vocabWords;
    }

    @Override
    public void incrementDocCount(String word) {
        incrementDocCount(word,1.0);
    }

    @Override
    public void incrementDocCount(String word, double by) {
        docFrequencies.incrementCount(word,by);

    }

    @Override
    public void incrementCount(String word) {
        incrementCount(word,1.0);
    }

    @Override
    public void incrementCount(String word, double by) {
        wordFrequencies.incrementCount(word,by);
        if(wordFrequencies.getCount(word) >= minWordFrequency)
            vocabWords.add(word);
    }

    @Override
    public double idf(String word) {
        return docFrequencies.getCount(word);
    }
}
