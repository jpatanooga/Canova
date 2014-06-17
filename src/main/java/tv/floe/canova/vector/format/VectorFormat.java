package tv.floe.canova.vector.format;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.Vector;

/**
 * A work in progress
 * 
 * TODO
 * - figure out how we're gonna deal w non-text
 * 
 * 
 * 
 * NOTES
 * - so the incoming Value / Text could be templatized --- based on what the record
 * 	reader of the input format spits out
 * 
 * @author josh
 *
 */
public abstract class VectorFormat<InputRecordType, OutputRecordType> {

	public abstract void configure(JobConf conf);

	
	// for when we're dealing w a text based file format
	public abstract void recordToVector( InputRecordType record, Vector v_in, Vector v_out );
	public abstract void vectorToRecord( Vector v_in, Vector v_out, OutputRecordType outRecord );
	
	// TODO: not sure if I like this method --- 
	// for when we're dealing with a binary file format
//	public abstract void bytesToVector( byte[] b, Vector v_in, Vector v_out );
//	public abstract void vectorToBytes( Vector v_in, Vector v_out, byte[] b );
	
}
