package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Executors;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * The CommonWords class parses the common words file. It creates a ConcurrentSkipListSet
 * that is available to be used by other classes
 */

public class CommonWords implements Parse{
    private final ConcurrentSkipListSet<String> words = new ConcurrentSkipListSet<>();

    /**
     * Getter method for the ConcurrentSkipListSet
     * @return ConcurrentSkipListSet
     */
    //O(1) just returns the value
    public ConcurrentSkipListSet<String> getWords() {
        return words;
    }

    /**
     * Parses the common words file
     * @param stopwords the stopwords to be parsed
     * @throws Exception .
     */
    //O(n) because the bigger the stopwords, the longer it will take. The process methods
    //is also O(n). So overall, it is O(n +n) = O(2n) = O(n)
    public void parse(String stopwords) throws Exception{
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(stopwords))
                    .forEach(text -> es.execute(() -> process(text)
                    ));
        }
    }
    //O(n) It must loop through all the elements
    private void process (String text){
        Arrays.stream(text.split("\\r+")).forEach(w -> this.words.add(w.toLowerCase()));
    }
}
