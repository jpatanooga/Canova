package tv.floe.canova.vector.factory;

import org.apache.hadoop.io.Text;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.junit.Test;

import tv.floe.canova.vector.format.libsvmVectoryFormat;

import junit.framework.TestCase;

public class TestLibsvmVectorFactory extends TestCase {
	
	@Test
	public void testConvertVector() {
		
		String xor_0 = "0 0:0 1:0";
		String xor_1 = "-1 0:0 1:1";

		libsvmVectoryFormat factory = new libsvmVectoryFormat();
		
		Text txt = new Text();
		txt.set(xor_0);
		
		Vector v_in = new RandomAccessSparseVector(3);
		Vector v_out = new RandomAccessSparseVector(3);
		
		factory.recordToVector( txt, v_in, v_out );
		
		// test the vector layouts
		assertEquals( 0.0, v_out.get(0) );
		assertEquals( 0.0, v_in.get(0) );
		assertEquals( 0.0, v_in.get(1) );
		
		Vector v_in_2 = new RandomAccessSparseVector(3);
		v_in_2.set(0, 1.0);
		v_in_2.set(2, 2.0);
		//v_in_2.set(4, 3.0);
		Vector v_out_2 = new RandomAccessSparseVector(3);
		v_in_2.set(0, 5.0);
		// setup the vectors
		
		Text txt_2 = new Text();
		txt_2.set("");
		
		
		factory.vectorToRecord( v_in_2, v_out_2, txt_2 );
		
		System.out.println( "line out: " + txt_2.toString() );
		
	}

}
