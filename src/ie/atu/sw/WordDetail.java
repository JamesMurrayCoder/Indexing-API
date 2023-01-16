package ie.atu.sw;

import java.util.*;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * Class used to store the details of the book. It contains a string,
 * which is used to hold the definition, and a Set, which has the pages
 * of the book the word appeared. A set was used to ensure repeat values
 * were removed.
 */
public class WordDetail {

    private final String definition;
    private TreeSet<Integer> pages;

    public WordDetail(String definition, int page){
        this.definition = definition;
        addIndex(page);
    }

    /**
     * Allows the index to be created when the book is being parsed
     * @param page the page number the word appeared on.
     */
    //Tree Set add time complexity is O(log n)
    public void addIndex(int page){
        if (pages == null) pages =  new TreeSet<>();
        pages.add(page);

        }

    /**
     * Getter method for the definition
     * @return the definition
     */
    //O(1) simple getter
    public String getDefinition() {
        return definition;
    }

    /**
     * Getter for the pages
     * @return the set of pages
     */
    //O(1) simple getter
    public TreeSet<Integer> getPages() {
        return pages;
    }



}
