package tv.floe.canova.vector.engine;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.mahout.math.Vector;

import tv.floe.canova.vector.factory.VectoryFactory;

/**
 * TODO
 * - how are we going to instantiate the VectoryFactory instance
 * 		how are we feeding in the properties file to the JobConf
 * 
 * @author josh
 *
 * @param <K1>
 * @param <V1>
 * @param <K2>
 * @param <V2>
 */
public class VectorMapTaskEngine<K1, V1, K2, V2> extends MapReduceBase
		implements Mapper<K1, V1, K2, V2> {

	private String inputVectorFactoryClassname = "";
	private VectoryFactory inputVectoryFactory = null;

	private String outputVectorFactoryClassname = "";
	private VectoryFactory outputVectoryFactory = null;
	
	
	private Vector vec_in = null;
	private Vector vec_out = null;
	
	private void setupConfiguredClasses() {
		
		// setup the vector factory
		
		Class<?> inputVecFactory_clazz;
		try {
			inputVecFactory_clazz = Class.forName( this.inputVectorFactoryClassname );
					//props.getProperty("tv.floe.canova.input.format"));
			
			Constructor<?> inputFormat_ctor = inputVecFactory_clazz.getConstructor();
			
			this.inputVectoryFactory =  (VectoryFactory) inputFormat_ctor.newInstance(); // new
																	// Object[]
																	// {
																	// ctorArgument
																	// });

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	/**
	 * Allows us to setup some state for things like word counts
	 * 
	 * @param conf
	 */
	public void configure(JobConf conf) {
		// this.configuration = conf;

		// TODO: parse the vectory factory classname
		this.inputVectorFactoryClassname = "";
		
		this.outputVectorFactoryClassname = "";

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

	@Override
	public void map(K1 key_in, V1 value_in, OutputCollector<K2, V2> outputCollector, Reporter reporter)
			throws IOException {

		// 1. we want to convert from the incoming format to an intermediate vector representation
		this.inputVectoryFactory.textToVector( null , this.vec_in, this.vec_out );
		
		// we now have vectors
		
		// 2. now that we have vectors, we need to figure out where we're going
		// if the output value has a Text value then
		this.outputVectoryFactory.vectorToText(this.vec_in, this.vec_out, null);
		
		// if the output value is binary then
		//this.outputVectoryFactory
		
		// now write the K2/V2 into the output
		outputCollector.collect( null, null );
		

	}

}
