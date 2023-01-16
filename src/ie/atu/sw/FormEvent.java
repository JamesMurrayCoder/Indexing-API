package ie.atu.sw;

import java.util.EventObject;
import java.util.Map;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * Provides a FormEvent to pass to the Form Listener
 */
public class FormEvent extends EventObject {

    /**
     * Getter for the Map
     * @return wordIndex map
     */
    //O(1) just returns the value
    public Map<String, WordDetail> getWordIndex() {
        return wordIndex;
    }

    private final Map<String, WordDetail> wordIndex;

    public FormEvent(Object source,String outputFile, String textFile,String dict, String commonWords) throws Exception {
        super(source);
        TextIndexer textIndexer = new TextIndexer(outputFile,textFile,dict,commonWords);
        textIndexer.execute();
        this.wordIndex =textIndexer.getWordIndex();

    }


}