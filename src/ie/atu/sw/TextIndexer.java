package ie.atu.sw;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * This is the central hub of the program. An object of this class can invoke methods that implement all
 * of the functionality of the application.
 */
public class TextIndexer {
    private String outputFile;
    private String textFile;
    private String dictionary;
    private String commonWords;

    private Dict d = new Dict();
    private CommonWords c = new CommonWords();
    private Book b = new Book(d.getIndex(), c.getWords());
    private AddedFunctionality ad = new AddedFunctionality();
    private Map<String, WordDetail> wordIndex;

    /**
     * Getter method for the wordIndex
     * @return the wordIndex
     */
    public Map<String, WordDetail> getWordIndex() {
        return b.getWordIndex();
    }

    /**
     * Parses the dicitionary, the common words file, the book. and creates
     * an index.
     * @throws Exception
     */
    public void execute() throws Exception {
        parseDictionary(dictionary);
        parseCommonWords(commonWords);
        indexBook(textFile);
    }

    /**
     * Shows the console based menu
     * @throws Exception .
     */
    //O(1) constant time operation
    public void menu() throws Exception {
       Menu m = new Menu();
       m.show();
    }

    /**
     * Parses the dicionary
     * @param dictionary the dictionary to be parsed
     * @throws Exception .
     */
    ////O(n), from the Dict class
    public void parseDictionary(String dictionary) throws Exception {
        d.parse(dictionary);
    }

    /**
     * Parses the common words
     * @param commonWords the common words file to be parsed
     * @throws Exception
     */
    //O(n) from the Common words class
    public void parseCommonWords(String commonWords) throws Exception {
        c.parse(commonWords);
    }

    /**
     * Parses the book and creates an index that is passes to the output file
     * @param book the book to be parsed
     * @throws Exception .
     */
    //O(n) from the Book class
    public void indexBook(String book) throws Exception {
        b.parse(book,outputFile);
    }

    /**
     * Displays the total number of unique words on the console
     */
    //O(n) from the AddedFunctionality class
    public void totalNumberOfUniqueWords(){
        System.out.println(ad.totalNumberOFUniqueWords(b.getWordIndex()));
    }

    /**
     * Prints the words of the index to the console
     * @throws FileNotFoundException
     */
    //O(n log n) from the AddedFunctionality class
    public void returnJustWords() throws FileNotFoundException {
        ad.wordsInOrder(b.getWordIndex());
    }

    /**
     * Prints the words of the index in reverse order to the console
     * @throws FileNotFoundException
     */
    //O(n log n) from the AddedFunctionality class
    public void returnReverseWords() throws FileNotFoundException {
        ad.wordsInReverseOrder(b.getWordIndex());
    }

    /**
     * Prints the 5 most common words of the index to the console.
     */
    //O(n2 log n) from the AddedFunctionality class
    public void mostCommonWords(){
        System.out.println("Most common words: ");
        ad.mostCommonWords(b.getWordIndex());
    }

    /**
     * Launches the GUI
     * @throws Exception .
     */
    //O(1) constant time operation
    public void launchGUI() throws Exception {
        MainFrame m = new MainFrame();
    }

    public TextIndexer() throws Exception {
    }


    public TextIndexer(String outputFile, String textFile, String dictionary, String commonWords) throws Exception {
        this.outputFile = outputFile;
        this.textFile = textFile;
        this.dictionary = dictionary;
        this.commonWords = commonWords;
    }

}
