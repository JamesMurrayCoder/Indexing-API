package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * This class parses the text file that was added by the user.
 */

public class Book{
    public Map<String, WordDetail> getWordIndex() {
        return wordIndex;
    }

    private final Map<String, WordDetail> wordIndex = new ConcurrentSkipListMap<>();
    private final Save s = new Save();
    private final ConcurrentSkipListSet<String> words;
    private final ConcurrentSkipListMap<String,String> index;
    private int line;
    private int page;

    public Book(ConcurrentSkipListMap index, ConcurrentSkipListSet words) throws Exception {
        this.index = index;
        this.words = words;
    }

    /**
     * Parses the text file and saves it to the output file
     * @param book the text to be parsed
     * @param outputFile the name of the output file
     * @throws Exception a runtime exception
     */
    //O(n) It has to read each line and therefore, the longer the book, the longer it'll take
    //The save method is also O(n) but they aren't nested.
    public void parse(String book, String outputFile) throws Exception {
        Files.lines(Paths.get(book)).forEach(text -> {
            line++;
            if (line % 40 == 0) page++;
            try {
                process(text, page);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        s.save(wordIndex, outputFile);
    }
    //O(n) Just one loop
    private void process(String text, int page) {
        String[] list = text.trim().toLowerCase().split("\\W+");
        for (String word : list) {
            if (!index.containsKey(word) || words.contains(word)) continue;
            WordDetail wordDetail = wordIndex.get(word);
            if (wordDetail == null) {
                wordDetail = new WordDetail(index.get(word), page);
                wordIndex.put(word, wordDetail);
            } else {
                wordDetail.addIndex(page);
                wordIndex.put(word, wordDetail);
            }
        }
    }
}
