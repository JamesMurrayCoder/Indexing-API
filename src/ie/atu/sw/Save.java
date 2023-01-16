package ie.atu.sw;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * Saves the map to the desired file
 */
public class Save {
    /**
     * Save the map
     * @param words the map to be saved
     * @param file the name of the output file
     * @throws FileNotFoundException .
     */
    //O(n) Because the bigger the input, the longer it will take. It has to write every line.
    public void save(Map<String, WordDetail> words, String file) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);

        for (ConcurrentSkipListMap.Entry<String, WordDetail> entry : words.entrySet()) {
            pw.write(entry.getKey()+ " : " + entry.getValue().getDefinition() + entry.getValue().getPages() + System.getProperty("line.separator"));
        }
        pw.close();
    }

}
