package tv.floe.canova.data.vectorizer.image;

import org.apache.mahout.math.Vector;

import tv.floe.canova.data.vectorizer.Vectorizer;

/**
 * So in hadoop this ends up using a MultiFileInputFormat where we're dealing with a
 * directory full of small JPEG files.
 * 
 * TODO:
 * - change the "Text" generic parameter to something of a "JPEG" type writable
 * 
 * @author josh
 *
 * @param <Text>
 */
public class JPEGVectorizer<Text> extends Vectorizer<Text> {

	/**
	 *
	 * The data should be in a line of text format
	 * 
	 */
	@Override
	public void vectorize(Text rec, Vector input, Vector output) {
		
		System.out.println( "KernelHashingVectorizer > " + rec.toString() );
		
		//input.set(0, 1.0);
		//output.set(0, 2.0);
		
	}

    @Override
    public Vector generateDimensionedModelInputVector() {
        return null;
    }

    @Override
    public Vector generateDimensionedModelOutputVector() {
        return null;
    }
}
