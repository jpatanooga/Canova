package org.canova.nlp.annotator;


import opennlp.uima.tokenize.TokenizerModelResourceImpl;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.ExternalResourceFactory;
import org.apache.uima.resource.ResourceInitializationException;
import org.canova.nlp.movingwindow.Util;
import org.canova.nlp.tokenization.tokenizer.ConcurrentTokenizer;
import org.cleartk.opennlp.tools.Tokenizer;
import org.cleartk.token.type.Sentence;
import org.cleartk.token.type.Token;



/**
 * Overrides OpenNLP tokenizer to be thread safe
 */
public class TokenizerAnnotator extends Tokenizer {

    static {
        //UIMA logging
        Util.disableLogging();
    }
	
	public static AnalysisEngineDescription getDescription(String languageCode)
		      throws ResourceInitializationException {
		    String modelPath = String.format("/models/%s-token.bin", languageCode);
		    return AnalysisEngineFactory.createEngineDescription(
                    ConcurrentTokenizer.class,
                    opennlp.uima.util.UimaUtil.MODEL_PARAMETER,
                    ExternalResourceFactory.createExternalResourceDescription(
                            TokenizerModelResourceImpl.class,
                            ConcurrentTokenizer.class.getResource(modelPath).toString()),
                    opennlp.uima.util.UimaUtil.SENTENCE_TYPE_PARAMETER,
                    Sentence.class.getName(),
                    opennlp.uima.util.UimaUtil.TOKEN_TYPE_PARAMETER,
                    Token.class.getName());
		  }

	
	
	public static AnalysisEngineDescription getDescription()
		      throws ResourceInitializationException {
		    String modelPath = String.format("/models/%s-token.bin", "en");
		    return  AnalysisEngineFactory.createEngineDescription(
                    ConcurrentTokenizer.class,
                    opennlp.uima.util.UimaUtil.MODEL_PARAMETER,
                    ExternalResourceFactory.createExternalResourceDescription(
                            TokenizerModelResourceImpl.class,
                            ConcurrentTokenizer.class.getResource(modelPath).toString()),
                    opennlp.uima.util.UimaUtil.SENTENCE_TYPE_PARAMETER,
                    Sentence.class.getName(),
                    opennlp.uima.util.UimaUtil.TOKEN_TYPE_PARAMETER,
                    Token.class.getName());
		  }

	

	
	
	
	
	
	
	 
}
