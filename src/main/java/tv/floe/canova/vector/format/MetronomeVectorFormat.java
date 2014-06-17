package tv.floe.canova.vector.format;

import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;


public class MetronomeVectorFormat extends VectorFormat<Text, Text> {

	@Override
	public void configure(JobConf conf) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void recordToVector(Text record, Vector v_in, Vector v_out) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void vectorToRecord(Vector v_in, Vector v_out, Text outRecord) {

		System.out.println( "Metronome > vectorToRecord > " );
		
		if ( v_in == null ) { 
			System.out.println( "v_in is null!" );
		}

		if ( null == outRecord ) { 
			System.out.println( "outRecord is null!" );
		}
		
		String in_str = "";
		
		Iterator<Element> iterNonZeroIn = v_in.iterateNonZero();
		
		while (iterNonZeroIn.hasNext()) {
			
			Element e = iterNonZeroIn.next();
			in_str += e.index() + ":" + e.get() + " ";
			
		}
		
		String out_str = "";
		
		Iterator<Element> iterNonZeroOut = v_in.iterateNonZero();
		
		while (iterNonZeroOut.hasNext()) {
			
			Element e = iterNonZeroOut.next();
			out_str += e.index() + ":" + e.get() + " ";
			
		}
		
		outRecord.clear();
		outRecord.set( in_str + " | " + out_str );
				
	}
	
}
