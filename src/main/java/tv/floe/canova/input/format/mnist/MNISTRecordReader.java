package tv.floe.canova.input.format.mnist;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapred.RecordReader;

import tv.floe.canova.data.vectorizer.image.mnist.ImageRecord;

public class MNISTRecordReader implements RecordReader<LongWritable, ImageRecord> {

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LongWritable createKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageRecord createValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPos() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getProgress() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean next(LongWritable arg0, ImageRecord arg1) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
