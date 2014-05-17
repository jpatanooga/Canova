package tv.floe.canova.conf;

import java.util.Properties;


public class ConfigFields {

  public static final String APP_INPUT_PATH = "app.input.path";
  public static final String APP_OUTPUT_PATH = "app.output.path";

  public static final String APP_BATCH_SIZE = "app.batch.size";
  public static final String APP_ITERATION_COUNT = "app.iteration.count";

  public static final String INPUT_FORMAT_CLASS = "app.inputformat.classname";
  public static final String INPUT_FORMAT_CLASS_DEFAULT = "org.apache.hadoop.mapred.TextInputFormat";  

  public static final String OUTPUT_FORMAT_CLASS = "app.outputformat.classname";
  public static final String OUTPUT_FORMAT_CLASS_DEFAULT = "org.apache.hadoop.mapred.TextInputFormat";  

  public static final String INPUT_FORMAT_KEY_CLASS = "app.outputformat.classname";
  public static final String INPUT_FORMAT_VALUE_CLASS = "app.outputformat.classname";

  public static final String OUTPUT_VECTOR_FORMAT_CLASS = "app.outputformat.classname";
  
  
  public static void validateConfig(Properties props) throws IllegalArgumentException {
    StringBuffer errors = new StringBuffer();
    String missing = " is missing\n";
/*    
    if (!props.containsKey(JAR_PATH))
      errors.append("IterativeReduce JAR path [" + JAR_PATH + "]").append(missing);
    
    if (!props.containsKey(APP_JAR_PATH))
      errors.append("Application JAR path [" + APP_JAR_PATH + "]").append(missing);
  */  
    
    if (errors.length() > 0)
      throw new IllegalArgumentException(errors.toString());
    
    //JobConf c = new JobConf();
    
    
  }
  
  
}