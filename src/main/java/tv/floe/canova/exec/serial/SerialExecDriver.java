package tv.floe.canova.exec.serial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;

import tv.floe.canova.vector.engine.VectorMapTaskEngine;



public class SerialExecDriver {
	
	final Reporter voidReporter = Reporter.NULL;
	protected static JobConf defaultConf = new JobConf();
	private static FileSystem localFs = null;
	static {
		try {
			defaultConf.set("fs.defaultFS", "file:///");
			localFs = FileSystem.getLocal(defaultConf);
		} catch (IOException e) {
			throw new RuntimeException("init failure", e);
		}
	}

	private static Path workDir = new Path("/tmp/");

	Properties props;

	private String app_properties_file = "";

	protected InputSplit[] splits;	
	
	String inputFormatClassname = "org.apache.hadoop.mapred.TextInputFormat"; //"tv.floe.canova.input.format";
	InputFormat inputFormat = null;
	
	SerialExecOutputCollector collector = null;
	
	public SerialExecDriver( String propsFile ) {
		
		this.app_properties_file = propsFile;
		this.collector = new SerialExecOutputCollector();
		
	}
	
	public OutputCollector getCollector() {
		
		return (OutputCollector) this.collector;
		
	}
	
	/**
	 * TODO: Instantiate the input format from the property file's conf'd class
	 * 
	 * 
	 * @param input_path
	 * @param job
	 * @return
	 */
	private InputSplit[] computeSplits(Path input_path, JobConf job) {

		long block_size = localFs.getDefaultBlockSize();

		System.out.println("default block size: " + (block_size / 1024 / 1024)
				+ "MB");

		// ---- set where we'll read the input files from -------------
		FileInputFormat.setInputPaths(job, input_path);

		// try splitting the file in a variety of sizes
		TextInputFormat format = new TextInputFormat();
		format.configure(job);

		int numSplits = 1;

		InputSplit[] splits = null;

		try {
			splits = format.getSplits(job, numSplits);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return splits;

	}	

	public RecordReader getRecordReaderForSplit( InputSplit split, JobConf job ) {
		
		try {
			return this.inputFormat.getRecordReader( split, job, voidReporter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	

	  public static void mergeConfigs(Properties sourceConf, Configuration destConf) {
	    if (sourceConf == null || destConf == null) {
	    	System.out.println( "Merge > Null " );
	      return;
	    }
	    
	    for(Entry<Object, Object> entry : sourceConf.entrySet()) {
	    //	System.out.println( "Merge > " + entry.getKey().toString() + " == " + entry.getValue().toString() );
	      if (destConf.get(entry.getKey().toString()) == null) {
	        destConf.set(entry.getKey().toString(), entry.getValue().toString());
	      }
	    }
	  }	

	/**
	 * Setup components of the IR app run 1. load app.properties 2. msg arrays
	 * 3. calc local splits 4. setup master 5. setup workers based on number of
	 * splits
	 * 
	 */
	public void setup() {

		// ----- load the app.properties file

		// String configFile = (args.length < 1) ?
		// ConfigFields.DEFAULT_CONFIG_FILE : args[0];
		this.props = new Properties();
		// Configuration conf = getConf();

		try {
			FileInputStream fis = new FileInputStream(this.app_properties_file);
			props.load(fis);
			fis.close();
		} catch (FileNotFoundException ex) {
			// throw ex; // TODO: be nice
			System.out.println(ex);
		} catch (IOException ex) {
			// throw ex; // TODO: be nice
			System.out.println(ex);
		}
		
//	      for (Map.Entry<Object, Object> entry : props.entrySet()) {
//	          System.out.println(entry.getKey() + "=" + entry.getValue());
//	        }
		
		
		// get input format class
		
		Class<?> inputFormat_clazz;
		try {
			inputFormat_clazz = Class.forName(props
					.getProperty("tv.floe.canova.input.data.format"));
			
			Constructor<?> inputFormat_ctor = inputFormat_clazz.getConstructor();
			
			this.inputFormat = (InputFormat) inputFormat_ctor.newInstance(); // new
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
		
		
		

		// setup msg arrays

		// calc splits

		// ---- this all needs to be done in
		JobConf job = new JobConf(defaultConf);
		
		// app.input.path
		mergeConfigs( props, defaultConf );
		
		
		Path splitPath = new Path( props.getProperty("tv.floe.canova.input.path") );

		System.out.println( "input.path = " + splitPath );
		
		// TODO: work on this, splits are generating for everything in dir
		this.splits = computeSplits(splitPath, job);

		System.out.println("split count: " + splits.length);



	}	
	
	/**
	 * TODO:
	 * - read the props file, setup
	 * - compute the inputs
	 * - for each input -> open a file reader
	 * - connect the file reader to an InputFormat based on the conf/props
	 * - Create an instance of the MapTask
	 * - read K/V pairs from the input format's RecordReader til EOF
	 * 
	 * 
	 * 
	 * TODO: questions
	 * - how do we infer or setup the template variables?
	 * @throws IOException 
	 * 
	 */
	public static void run() throws IOException {
		
		SerialExecDriver driver = new SerialExecDriver( "src/test/resources/single_process.unit_test.properties" );
		// read props
		// compute split
		driver.setup();

		System.out.println( "vec eng: " + defaultConf.get("tv.floe.canova.vectorization.engine") );
		
		Text tmp_text = new Text();
		
		VectorMapTaskEngine<LongWritable, Text, NullWritable, Text> vector_engine = new VectorMapTaskEngine<LongWritable, Text, NullWritable, Text>( tmp_text );
		vector_engine.configure( defaultConf );
		
		if (vector_engine.isBadConf()) {
			System.out.println( "Invalid Conf!" );
			return;
		}
		
		
		// setup MapTask->Vectorizer engine
		
		for ( int x = 0; x < driver.splits.length; x++ ) {
			
			// get a RecordReader for split
			RecordReader rr = driver.getRecordReaderForSplit(driver.splits[x], driver.defaultConf );
			// for each record, 
			
			LongWritable key = (LongWritable) rr.createKey();
			Text val = (Text) rr.createValue();
			
			while (rr.getProgress() < 1.0) {
				
				// get the next k/v pair
				boolean gotKey = rr.next( key, val );
				
				System.out.println( "SerialExecDriver > " + val.toString() );
				
				// feed it to the map task harness w the vector engine
				// TODO: fix the reporter and collector
				vector_engine.map(key, val, driver.getCollector(), driver.voidReporter );
			//	System.out.println( "line: " + val.toString() ); 
				
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException {

		SerialExecDriver.run();
		
	}

}
