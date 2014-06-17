package tv.floe.canova.vector.format;

import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;


/**
 * In this case we're dealing with a text input/output formats
 * 
 */
public class libsvmVectoryFormat extends VectorFormat<Text, Text> {

	public static final int FEATURES = 10000;
	
	@Override
	public void configure(JobConf conf) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * We're mapping the data in record to an input vector and then the label (v_out)
	 * 
	 */
	@Override
	public void recordToVector(Text record, Vector v_in, Vector v_out) {

		String line = record.toString();

        String[] parts = line.split(" ");
        
        int classIndex = Integer.parseInt( parts[0] );
        v_out.set(0, classIndex);
        
        
        for (int x = 1; x < parts.length; x++) {
          // encoder_test.addToVector(parts[x], v);
          // System.out.println( parts[x] );
          String[] feature = parts[x].split(":");
          int index = Integer.parseInt(feature[0]) % FEATURES;
          double val = Double.parseDouble(feature[1]);
          
          //System.out.println( feature[1] + " = " + val );
          
          if (index < FEATURES) {
            v_in.set(index, val);
          } else {
            
            System.out.println("Could Hash: " + index + " to "
                + (index % FEATURES));
            
          }
          
        }		
		
		
		
		
	}

	/**
	 * 
	 * libsvm looks like:
	 * String record_0 = "-1 3:1 11:1 14:1 19:1 39:1 42:1 55:1 64:1 67:1 73:1 75:1 76:1 80:1 83:1 ";
	 * 
	 * 
	 * Since libsvm only has a single output label, then there can only be a single serialized output
	 * 
	 * 
	 */
	@Override
	public void vectorToRecord(Vector v_in, Vector v_out, Text outRecord) {
		
		String out = "";
		
		double labelVal = v_out.get(0);
		
		out += labelVal;
		
		Iterator<Element> vec_it = v_in.iterateNonZero();
		
		while (vec_it.hasNext()) {
			
			Element e = vec_it.next();
			out += " " + e.index() + ":" + e.get(); 
 			
		}
		
		
		outRecord.set(out);
		
	}

}
