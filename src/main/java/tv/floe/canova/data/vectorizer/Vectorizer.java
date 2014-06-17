package tv.floe.canova.data.vectorizer;

import org.apache.mahout.math.Vector;

import tv.floe.canova.vector.format.VectorFormat;

public abstract class Vectorizer<R> {

	/**
	 * What happens here?
	 * 
	 * we take a generic record that was supplied at execution time and 
	 * - we've read raw data from a file
	 * - we need to convert this raw data into a vector
	 * - if its already in a vector format, we just need to read that format; IdentityVectorizer used in that case
	 * - if its raw data, we need to run it through the Vectorizer and produce vectors (RawDataFormat ?)
	 * 
	 * 
	 * @param rec
	 * @param vf
	 */
	public abstract void vectorize( R rec, Vector input, Vector output );
	
	public abstract Vector generateDimensionedModelInputVector();
	
	/**
	 * We assume its a dimension per class in multi-label situations with 1.0's for the label
	 * 
	 * In NN's any number of vectors in the output vector could be a double between { 0.0, 1.0 }
	 * 
	 */
	public abstract Vector generateDimensionedModelOutputVector();
	
}
