package tv.floe.canova.data.vectorizer.text;

import org.apache.mahout.math.Vector;
import tv.floe.canova.data.vectorizer.Vectorizer;

public class TF_IDF_Vectorizer<Text> extends Vectorizer<Text> {

	/**
	 *
	 * The data should be in a line of text format
	 * 
	 */
	@Override
	public void vectorize(Text rec, Vector input, Vector output) {
		// TODO Auto-generated method stub
		
		System.out.println( "TF_IDF_Vectorizer > " + rec.toString() );
		
		input.set(0, 1.0);
		output.set(0, 2.0);
		
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
