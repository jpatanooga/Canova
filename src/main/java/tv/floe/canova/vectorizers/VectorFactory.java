package tv.floe.canova.vectorizers;

import org.apache.hadoop.conf.Configuration;

public class VectorFactory<K, V> {
	
	/**
	 * Allows us to setup some state for things like word counts
	 * 
	 * @param conf
	 */
	public void setup(Configuration conf) {
		
		
		
	}
	
	/**
	 * This is where we pass in the data from the source file which has been
	 * de-serialized into the templatized records
	 * 
	 * This works just like a normal Map function would in Hadoop's MapReduce
	 * 
	 * We use this pattern to support both the serial version 
	 * 
	 * Output is of the form of 2 Mahout Vectors: vector-input, vector-output
	 * - this allows us to specify the input vectors to the training algorithm
	 * 		and the corresponding labels or outcomes
	 * 
	 * The underlying system should convert the vectors into the OutputFormat specified
	 * with the specified record formats
	 * 
	 * Examples: svmLightVectorWritable, MetronomeVectorWritable, ARFFVectorWritable
	 * 
	 * @param key
	 * @param value
	 */
	public void convert( K key, V value ) {
		
		
	}
	
	

}
