package tv.floe.canova.vector.engine;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;

import tv.floe.canova.data.vectorizer.Vectorizer;
import tv.floe.canova.vector.format.VectorFormat;

/**
 * TODO
 * - how are we going to instantiate the VectoryFactory instance
 * 		how are we feeding in the properties file to the JobConf
 * 
 * 
 * - need to seperate the concept of reading data from vectorizing data
 * 		- example we dont want to simply convert svmlight to metronome
 * 		- we want to support converting raw text to TF-IDF
 * 		- thus we need to create an input format for each vector type
 * 		- certain input types imply an "identity" conversion (ie, none)
 * 
 * @author josh
 *
 * @param <K1>
 * @param <V1>
 * @param <K2>
 * @param <V2>
 */
public class VectorMapTaskEngine<K1, V1, NullWritable, V2> extends MapReduceBase
		implements Mapper<K1, V1, NullWritable, V2> {

	private boolean badConf = false;
	
	// conf file keys
	public static final String INPUT_VECTOR_FORMAT = "tv.floe.canova.input.vector.format";
	public static final String OUTPUT_VECTOR_FORMAT = "tv.floe.canova.output.vector.format";
	public static final String VECTORIZER = "tv.floe.canova.vectorization.engine";

	private static final String RAWDATAFORMAT = "tv.floe.canova.vector.format.RawDataNonVectorFormat";
	private static final String IDENTITY_VECTORIZER = "tv.floe.canova.data.vectorizer.IdentityVectorizer";
	
	// defaults
	private String inputVectorFormatClassname = "tv.floe.canova.vector.format.RawDataNonVectorFormat";
	private VectorFormat inputVectorFormat = null;

	// defaults	
	private String outputVectorFormatClassname = "tv.floe.canova.vector.format.MetronomeVectorFormat";
	private VectorFormat outputVectorFormat = null;

	
	// defaults
	private String vectorizerClassname = "tv.floe.canova.data.vectorizer.IdentityVectorizer";
	private Vectorizer vectorizer = null;
	
	
	private Vector vec_in = null;
	private Vector vec_out = null;
	
	NullWritable nullWritable = null; //
	//private K2 key_out = null;
	private V2 val_out = null;
	
	public VectorMapTaskEngine(V2 instance) {
		
		this.vec_in = new RandomAccessSparseVector(10);
		this.vec_out = new RandomAccessSparseVector(10);
		
		this.val_out = instance;
		
	}
	
	/**
	 * TODO:
	 * - setup from the generics an instance of the V2 val_out instance
	 * 
	 * 
	 * 
	 */
	private void setupConfiguredClasses() {
		
		// setup the vector factory
		
		Class<?> inputVecFormat_clazz;
		try {
			inputVecFormat_clazz = Class.forName( this.inputVectorFormatClassname );
					//props.getProperty("tv.floe.canova.input.format"));
			
			Constructor<?> inputFormat_ctor = inputVecFormat_clazz.getConstructor();
			
			this.inputVectorFormat =  (VectorFormat) inputFormat_ctor.newInstance(); // new
																	// Object[]
																	// {
																	// ctorArgument
																	// });

			if (null != this.inputVectorFormat) {
				System.out.println( "Input Vector Format: " + this.inputVectorFormatClassname );
			}
			
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
		
		
		
		
		Class<?> outputVecFormat_clazz;
		try {
			outputVecFormat_clazz = Class.forName( this.outputVectorFormatClassname );
					//props.getProperty("tv.floe.canova.input.format"));
			
			Constructor<?> outputFormat_ctor = outputVecFormat_clazz.getConstructor();
			
			this.outputVectorFormat =  (VectorFormat) outputFormat_ctor.newInstance(); // new
																	// Object[]
																	// {
																	// ctorArgument
																	// });

			if (null != this.outputVectorFormat) {
				System.out.println( "Output Vector Format: " + this.outputVectorFormatClassname );
			}
			
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
		

		
		
		Class<?> vectorizer_clazz;
		try {
			vectorizer_clazz = Class.forName( this.vectorizerClassname );
					//props.getProperty("tv.floe.canova.input.format"));
			
			Constructor<?> vectorizer_ctor = vectorizer_clazz.getConstructor();
			
			this.vectorizer =  (Vectorizer) vectorizer_ctor.newInstance(); // new
																	// Object[]
																	// {
																	// ctorArgument
																	// });

			if (null != this.vectorizer) {
				System.out.println( "Vectorizer: " + this.vectorizer );
			}
			
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
		this.inputVectorFormatClassname = conf.get( INPUT_VECTOR_FORMAT, this.inputVectorFormatClassname );
		
		this.outputVectorFormatClassname = conf.get( OUTPUT_VECTOR_FORMAT, this.outputVectorFormatClassname );

		this.vectorizerClassname = conf.get( VECTORIZER, this.vectorizerClassname );
		
		System.out.println( "vectorizerClassname: " + this.vectorizerClassname );
		
		// need to check: if we are raw data, AND Identity vectorizer, this doesnt work
		this.badConf = false;
		
		if ( RAWDATAFORMAT.equals( this.inputVectorFormatClassname ) && IDENTITY_VECTORIZER.equals( this.vectorizerClassname ) ) {
			
			this.badConf = true;
			
		}
		
		if ( RAWDATAFORMAT.equals( this.inputVectorFormatClassname ) ) {

			System.out.println( "Converting Raw Data Into Vectors..." );
			
		} else {
			
			System.out.println( "Converting Existing Vectors Into Other Types of Vectors..." );
			
		}
		
		//this.nullWritable = new NullWritable();
		
		
		setupConfiguredClasses();
		
	}
	
	public boolean isBadConf() {
		
		return this.badConf;
		
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
	public void map(K1 key_in, V1 value_in, OutputCollector<NullWritable, V2> outputCollector, Reporter reporter)
			throws IOException {

		
		/*
		 * What happens here?
		 * 
		 * we take a generic record that was supplied at execution time and 
		 * - we've read raw data from a file
		 * - we need to convert this raw data into a vector
		 * - if its already in a vector format, we just need to read that format; IdentityVectorizer used in that case
		 * - if its raw data, we need to run it through the Vectorizer and produce vectors (RawDataFormat ?)
		 * 
		 */
		
		
		// at this point the input format has done its job and we should have a record in some format
		// each value in should be a complete record that needs to be vectorized
		
		
		this.vec_in.assign(0.0);
		this.vec_out.assign(0.0);

		// This switch is the best way I know how to separate out this concept
		// probably a more clever way to do this
		
		// 1. is the value_in a raw data record or is it already 

		if ( RAWDATAFORMAT.equals( this.inputVectorFormatClassname ) ) {

			// we need to do some vectorization work, ignore the input vector format
			this.vectorizer.vectorize( value_in, this.vec_in, this.vec_out );
			
		} else {
			
			// the data has been previously vectorized
			this.inputVectorFormat.recordToVector( value_in, vec_in, vec_out );
			
		}
		
		
		
		// we now have vectors
		
		// 2. now that we have vectors, we need to figure out where we're going
		this.outputVectorFormat.vectorToRecord( vec_in, vec_out, this.val_out );
		
		// now write the K2/V2 into the output
		outputCollector.collect( this.nullWritable, this.val_out );
		

	}

}
