package tv.floe.canova.exec.serial;

import java.io.IOException;

import org.apache.hadoop.mapred.OutputCollector;

/**
 * TODO
 * - serialize the output to disk
 * - Figure out the story around using Hadoop's built in OutputFormats
 * 
 * @author josh
 *
 * @param <K>
 * @param <V>
 */
public class SerialExecOutputCollector<K, V> implements OutputCollector<K, V> {

	@Override
	public void collect(K arg0, V arg1) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println( "SerialExecOutputCollector > " + arg1 );
		
	}

}
