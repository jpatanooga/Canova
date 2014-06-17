package tv.floe.canova.input.format.mnist;

import java.io.IOException;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.mahout.math.Matrix;

public class MNISTBinaryFileInputFormat<LongWritable, ImageRecord> extends FileInputFormat<LongWritable, ImageRecord> {


	public Matrix imageData = null;
	public Matrix labelData = null;


    /** the following constants are defined as per the values described at http://yann.lecun.com/exdb/mnist/ **/

    private static final int MAGIC_OFFSET = 0;
    private static final int OFFSET_SIZE = 4; //in bytes

    private static final int LABEL_MAGIC = 2049;
    private static final int IMAGE_MAGIC = 2051;

    private static final int NUMBER_ITEMS_OFFSET = 4;
    private static final int ITEMS_SIZE = 4;

    private static final int NUMBER_OF_ROWS_OFFSET = 8;
    private static final int ROWS_SIZE = 4;
    public static final int ROWS = 28;

    private static final int NUMBER_OF_COLUMNS_OFFSET = 12;
    private static final int COLUMNS_SIZE = 4;
    public static final int COLUMNS = 28;

    private static final int IMAGE_OFFSET = 16;
    private static final int IMAGE_SIZE = ROWS * COLUMNS;	
	
	
	
	
	
	
	
	
	
	
	
	public MNISTBinaryFileInputFormat() {
	//	setMinSplitSize(SequenceFile.SYNC_INTERVAL);
	}
/*
	@Override
	protected FileStatus[] listStatus(JobConf job) throws IOException {
		FileStatus[] files = super.listStatus(job);
		for (int i = 0; i < files.length; i++) {
			FileStatus file = files[i];
			if (file.isDirectory()) { // it's a MapFile
				Path dataFile = new Path(file.getPath(), MapFile.DATA_FILE_NAME);
				FileSystem fs = file.getPath().getFileSystem(job);
				// use the data file
				files[i] = fs.getFileStatus(dataFile);
			}
		}
		return files;
	}

	public RecordReader<K, V> getRecordReader(InputSplit split, JobConf job,
			Reporter reporter) throws IOException {

		reporter.setStatus(split.toString());

		return new SequenceFileRecordReader<K, V>(job, (FileSplit) split);
	}
*/











	@Override
	public RecordReader<LongWritable, ImageRecord> getRecordReader(InputSplit arg0, JobConf arg1, Reporter arg2) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}