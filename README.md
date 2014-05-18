Canova
======

# Description

A tool focused simply on vectorizing raw data into usable vector formats across machine learning tools

# Example

Convert the MNIST dataset from raw binary files to the svmLight text format.

# Execution

Runs as both a local serial process and a MapReduce scale out process with no code changes.

# Targetted Vector Formats
* svmLight
* libsvm
* Metronome
* ARFF

# Built-In General Functionality
* Understands how to take general text and convert it into vectors with stock techniques such as kernel hashing and TF-IDF [TODO]
