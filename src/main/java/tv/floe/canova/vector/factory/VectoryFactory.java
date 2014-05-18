package tv.floe.canova.vector.factory;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.Vector;

public abstract class VectoryFactory {

	public abstract void configure(JobConf conf);

	
	// for when we're dealing w a text based file format
	public abstract void textToVector( Text t, Vector v_in, Vector v_out );
	public abstract void vectorToText( Vector v_in, Vector v_out, Text t );
	
	// TODO: not sure if I like this method --- 
	// for when we're dealing with a binary file format
	public abstract void bytesToVector( byte[] b, Vector v_in, Vector v_out );
	public abstract void vectorToBytes( Vector v_in, Vector v_out, byte[] b );
	
}
