package tv.floe.canova.data.vectorizer.text;

import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import tv.floe.canova.data.vectorizer.Vectorizer;
import tv.floe.canova.data.vectorizer.text.hashing.HashKernel;

/**
 * TODO:
 * - do we want to add a delimiter to split sentence from label?
 * - whats the best way to pass parameters?
 * 
 * 
 * 
 * 
 * 
 * @author josh
 *
 * @param <Text>
 */
public class KernelHashingVectorizer<Text> extends Vectorizer<Text> {

	HashKernel hk = null; 
	int featureVectorSize = 100;
	int outputVectorSize = 10;
			
	/**
	 * We need to pass in a parameter for the vector width
	 * 
	 */
	public KernelHashingVectorizer() {
		
		this.hk = new HashKernel( featureVectorSize );
		
	}
	
	/**
	 *
	 * The data should be in a line of text format
	 * 
	 * Steps:
	 * 
	 * 1. parse the record into source record and label parts
	 * 2. take the record and hash it into a feature vector
	 * 3. convert the label into an output vector
	 * 
	 * TODO:
	 * - what constraints do we put on incoming records (sequences of words)?
	 * - what checks/constraints do we put on having labels?
	 * - what is the standard way to represent (internally) output vectors? [gonna have to go with a multi-dim vector to accomodate NNs]
	 */
	@Override
	public void vectorize(Text rec, Vector input, Vector output) {
		
		System.out.println( "KernelHashingVectorizer > " + rec.toString() );
		
		//Vector v = this.hk.createCorrectlySizedVector();
		
		hk.hash(rec.toString(), input);
				
		
	}
	
	public Vector generateDimensionedModelInputVector() {
		
		return new RandomAccessSparseVector( this.featureVectorSize );
	}
	
	/**
	 * We assume its a dimension per class in multi-label situations with 1.0's for the label
	 * 
	 * In NN's any number of vectors in the output vector could be a double between { 0.0, 1.0 }
	 * 
	 */
	public Vector generateDimensionedModelOutputVector() {
		
		return new RandomAccessSparseVector( this.outputVectorSize );
	}
	
}
