package org.canova.nlp.tokenization.tokenizer;


/**
 * Token preprocessing
 * @author Adam Gibson
 *
 */
public interface TokenPreProcess {

	/**
	 * Pre process a token
	 * @param token the token to pre process
	 * @return the preprocessed token
	 */
	String preProcess(String token);
	
	
}
