Canova
======

# Description

A tool focused simply on vectorizing raw data into usable vector formats across machine learning tools.

# Example

 * Convert the MNIST dataset from raw binary files to the svmLight text format.
 * Convert raw text into the Metronome vector format
 * Convert raw text into TF-IDF based vectors in a text vector format {svmLight, metronome, arff}
 * Convert raw text into the word2vec in a text vector format {svmLight, metronome, arff}

# Targeted Vectorization Engines

 * MNIST to vectors
 * Text to vectors
    * TF-IDF
    * Bag of Words
    * word2vec

# Execution

Runs as both a local serial process and a MapReduce scale out process with no code changes.

# Targetted Vector Formats
* svmLight
* libsvm
* Metronome
* ARFF

# Built-In General Functionality
* Understands how to take general text and convert it into vectors with stock techniques such as kernel hashing and TF-IDF [TODO]
