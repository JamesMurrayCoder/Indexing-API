package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Executors;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * Parses the dictionary into a ConcurrentSkipListMap that is available to other
 * classes
 *
 */
public class Dict implements Parse{
    private final ConcurrentSkipListMap<String,String> index = new ConcurrentSkipListMap<>();

    /**
     * Getter method for ConcurrentSkipListMap
     * @return the index
     */
    public ConcurrentSkipListMap<String, String> getIndex() {
        return index;
    }

    /**
     * Parses the dictionary
     * @param file the dictionary to be parses
     * @throws IOException .
     */
    //O(n) because the bigger the stopwords, the longer it will take. The process methods
    //is also O(n). So overall, it is O(n +n) = O(2n) = O(n)
    public void parse(String file) throws IOException {
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            Files.lines(Paths.get(file))
                    .forEach(text -> es.execute(() -> process(text)
                    ));
        }
    }
    //O(log n) It is time to add an element to the ConcurrentSkipListMap
    private void process (String text){
        String[] kv = text.split("[,:]");
        index.put(kv[0].toLowerCase(), kv[kv.length-1].trim());
    }

}
