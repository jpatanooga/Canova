package tv.floe.canova.vector.format;

import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.Vector;


public class RawDataNonVectorFormat<InputRecordType, OutputRecordType> extends VectorFormat<InputRecordType, OutputRecordType> {

	public void configure(JobConf conf) {
		
	}

	@Override
	public void recordToVector(InputRecordType record, Vector v_in, Vector v_out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vectorToRecord(Vector v_in, Vector v_out, OutputRecordType outRecord) {
		// TODO Auto-generated method stub
		
	}

	
	
}
