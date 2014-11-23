package org.canova.nlp.metadata;

import org.canova.api.berkeley.Counter;
import org.canova.api.conf.Configuration;
import org.canova.api.util.Index;
import org.canova.api.util.MathUtils;
import org.canova.nlp.vectorizer.TextVectorizer;

/**
 * Created by agibsonccc on 11/21/14.
 */
public class DefaultVocabCache implements VocabCache {

    private Counter<String> wordFrequencies = new Counter<>();
    private Counter<String> docFrequencies = new Counter<>();
    private int minWordFrequency;
    private Index vocabWords = new Index();
    private double numDocs = 0;

    @Override
    public void incrementNumDocs(double by) {
        numDocs += by;
    }

    @Override
    public double numDocs() {
        return numDocs;
    }

    @Override
    public String wordAt(int i) {
        return vocabWords.get(i).toString();
    }

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
    public Index vocabWords() {
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
        if(wordFrequencies.getCount(word) >= minWordFrequency && vocabWords.indexOf(word) < 0)
            vocabWords.add(word);
    }

    @Override
    public double idf(String word) {
        return docFrequencies.getCount(word);
    }

    @Override
    public double tfidf(String word, double frequency) {
        return MathUtils.tfidf(MathUtils.tf((int) frequency),MathUtils.idf(numDocs,idf(word)));
    }
}
