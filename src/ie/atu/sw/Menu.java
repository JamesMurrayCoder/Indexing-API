package ie.atu.sw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * A class that displays the options available to the user.
 */
public class Menu {

    private final Scanner s;
    private boolean keepRunning = true;
    private String outputFile;
    private String textFile;
    private String dictionary;
    private String commonWords;
    private TextIndexer ti;

    public Menu(){
        s = new Scanner(System.in);
    }
    //O(1) no loops
    private void showOptions() {
        System.out.println();
        System.out.println("************************************************************");
        System.out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
        System.out.println("*                                                          *");
        System.out.println("*              Virtual Threaded Text Indexer               *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.println("(1) Specify Text File");
        System.out.println("(2) Configure Dictionary");
        System.out.println("(3) Configure Common Words");
        System.out.println("(4) Specify Output File");
        System.out.println("(5) Execute");
        System.out.println("(6) Return the number of unique words");
        System.out.println("(7) Print the words");
        System.out.println("(8) Print the words in reverse order");
        System.out.println("(9) Print the five most common words");
        System.out.println("(10) Launch GUI");
        System.out.println("(11) Quit");

        //Output a menu of options and solicit text from the user
        System.out.print("Select Option [1-11]>");
        System.out.println();
    }

    /**
     * Contains a switch statement that allows the user to select one of the
     * methods.
     * @throws Exception .
     */
    //O(1) all constant time operations
    public void show() throws Exception {
        while(keepRunning){
            showOptions();
            int choice = Integer.parseInt(s.next());

            switch (choice){
                case 1 -> setTextFile();
                case 2 -> configureDictionary();
                case 3 -> configureCommonWords();
                case 4 -> setOutputFile();
                case 5 -> execute();
                case 6 -> printTotalNumberOFUniqueWords();
                case 7 -> printJustTheWords();
                case 8 -> printJustTheWordsReverse();
                case 9 -> printFiveMostCommonWords();
                case 10 -> launchGUI();
                case 11 -> {
                    System.out.println("[INFO} Shutting down... please wait...");
                    keepRunning = false;
                }
                default -> System.out.println("[ERROR] Invalid input");
            }
        }
    }

    //O(1) all constant time operations
    private void setTextFile(){
        System.out.println("Please enter the text file:");
        textFile = s.next();
    }
    //O(1) all constant time operations
    private void configureDictionary(){
        System.out.println("Please enter the dictionary:");
        dictionary = s.next();
    }
    //O(1) all constant time operations
    private void configureCommonWords(){
        System.out.println("Please enter the common words file");
        commonWords = s.next();
    }
    //O(1) all constant time operations
    private void setOutputFile(){
        System.out.println("Please enter the output file");
        outputFile = s.next();
    }
    /*
        parseDictionary(dictionary); - O(n)
        parseCommonWords(commonWords); - O(n)
        indexBook(textFile); - O(n)
        So it is overall O(n)
     */
    private void execute(){
        try {
            this.ti = new TextIndexer(outputFile,textFile,dictionary,commonWords);
            ti.execute();
        } catch (Exception e) {
            handleException();
        }

    }
    //O(n) From the additional functionality class, totalNumberOfUniqueWords method is O(n)
    private void printTotalNumberOFUniqueWords() {
        try{
            ti.totalNumberOfUniqueWords();
        } catch (Exception e) {
            if (this.ti == null) {
                System.out.println("Please index the file to continue");
            }
        }
    }
    ////O(n log n) From the additional functionality class
    private void printJustTheWords(){
        try{
            ti.returnJustWords();
        } catch (Exception e){
            if(this.ti == null){
                System.out.println("Please index the file to continue");
            }
        }

    }
    //O(n log n) From the additional functionality class
    private void printJustTheWordsReverse(){
        try{
            ti.returnReverseWords();
        } catch (Exception e) {
            if (this.ti == null) {
                System.out.println("Please index the file to continue");
            }
        }
    }
    //O(n2 log n) From the additional functionality class
    private void printFiveMostCommonWords(){
        try{
            ti.mostCommonWords();
        } catch (Exception e){
            if (this.ti == null) {
                System.out.println("Please index the file to continue");
            }
        }

    }
    //O(1) constant time operations
    private void launchGUI() throws Exception {
        TextIndexer ti = new TextIndexer();
        ti.launchGUI();
    }
    //O(n) might have to loop through all elements
    private void handleException() {
        System.out.println("The following field(s) must be entered to continue:");
        Map<String, Object> variables = new HashMap<>();
        variables.put("textFile", textFile);
        variables.put("dictionary", dictionary);
        variables.put("commonWords", commonWords);
        variables.put("outputFile", outputFile);

        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            if (entry.getValue() == null) {
                System.out.println(entry.getKey());
            }
        }
    }
}


