package tv.floe.canova.vector.engine;

import java.io.IOException;

import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.mahout.math.Vector;

public class VectorMapTaskEngine<K1, V1, K2, V2> extends MapReduceBase
		implements Mapper<K1, V1, K2, V2> {

	
	
	/**
	 * Allows us to setup some state for things like word counts
	 * 
	 * @param conf
	 */
	public void configure(JobConf conf) {
		// this.configuration = conf;

		// Basic pojo code here

	}

	/**
	 * This is where we pass in the data from the source file which has been
	 * de-serialized into the templatized records
	 * 
	 * This works just like a normal Map function would in Hadoop's MapReduce
	 * 
	 * We use this pattern to support both the serial version
	 * 
	 * Output is of the form of 2 Mahout Vectors: vector-input, vector-output -
	 * this allows us to specify the input vectors to the training algorithm and
	 * the corresponding labels or outcomes
	 * 
	 * The underlying system should convert the vectors into the OutputFormat
	 * specified with the specified record formats
	 * 
	 * Examples: svmLightVectorWritable, MetronomeVectorWritable,
	 * ARFFVectorWritable
	 * 
	 * @param key
	 * @param value
	 */
	public void convert(K1 key, V1 value, Vector vec_input, Vector vec_output) {

		// TODO: convert <K1, V1> from raw data into vectors in/out

	}

	@Override
	public void map(K1 key_in, V1 value_in,
			OutputCollector<K2, V2> outputCollector, Reporter reporter)
			throws IOException {
		// TODO Auto-generated method stub

	}

}
