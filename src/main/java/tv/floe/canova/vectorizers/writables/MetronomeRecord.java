package tv.floe.canova.vectorizers.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;

/**
 * Should be able to take a (Mahout / JBLAS) math vector and do Serde on it for
 * conversion purposes
 * 
 * 
 * 
 * Should this not inherit from the Text writable given that its always text?
 * 
 * TODO - this class needs to self describe a format for the on disk vector -
 * ie: how do we talk the vector object and lay it out on disk
 * 			-> toString() should do it
 * 
 * TODO - this class needs to parse the line format from disk
 * 			-> normally the Text object just takes the line of string and sets the bytes
 * 			-> we need to override the set() method to also convert the text into a Vector
 * 
 * @author josh
 * 
 */
public class MetronomeRecord extends Text {

	// TODO what is the data representation? -> Vector in/out

	// TODO: how do we define a schema
	// - gonna have to do it directly in this class in .toString()

	public float x;
	public float y;
	public float z;

	public MetronomeRecord(Vector vec_in, Vector vec_out) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

/*	public MetronomeRecord(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
*/
	public MetronomeRecord() {
		this(0.0f, 0.0f, 0.0f);
	}

	// need these two methods for moving data around 
	public void write(DataOutput out) throws IOException {
		out.writeFloat(x);
		out.writeFloat(y);
		out.writeFloat(z);
	}

	public void readFields(DataInput in) throws IOException {
		x = in.readFloat();
		y = in.readFloat();
		z = in.readFloat();
	}

	/**
	 * This method is used when we want to serialize out to Text
	 * 
	 * we want to scan along the vectors and sparsely write out the elements
	 * 
	 */
	public String toString() {
		return Float.toString(x) + ", " + Float.toString(y) + ", "
				+ Float.toString(z);
	}


	public int compareTo(MetronomeRecord other) {
//		float myDistance = distanceFromOrigin();
//		float otherDistance = other.distanceFromOrigin();

//		return Float.compare(myDistance, otherDistance);
		return 0;
	}

	public boolean equals(Object o) {
		if (!(o instanceof MetronomeRecord)) {
			return false;
		}

		MetronomeRecord other = (MetronomeRecord) o;
		return this.x == other.x && this.y == other.y && this.z == other.z;
	}

	public int hashCode() {
		return Float.floatToIntBits(x) ^ Float.floatToIntBits(y)
				^ Float.floatToIntBits(z);
	}
}
