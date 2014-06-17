package tv.floe.canova.data.vectorizer.image.mnist;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.Vector;

import tv.floe.canova.vector.format.VectorFormat;


/**
 * TODO
 * - gotta figure out how to deal w bytes from the input files
 * 
 * 
 * The strategy is that regardless of the input format
 * - we take that input type and convert it into a standard vector object
 * - from there any other vector factor can TAKE a vecotor, and produce an output type
 * 
 * @author josh
 *
 */
public class MNISTVectorFactory extends VectorFormat<ImageRecord, ImageRecord> {

	@Override
	public void configure(JobConf conf) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void recordToVector(ImageRecord record, Vector v_in, Vector v_out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vectorToRecord(Vector v_in, Vector v_out, ImageRecord outRecord) {
		// TODO Auto-generated method stub
		
	}
	
	
}
