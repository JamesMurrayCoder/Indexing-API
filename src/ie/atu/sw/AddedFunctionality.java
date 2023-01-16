package ie.atu.sw;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * This is a class that allows a number of different operations to be performed on
 * the wordIndex.
 */
public class AddedFunctionality {

    /**
     * Takes a wordIndex and prints the words in alphabetical order
     * to the console. Prints 5 on a line.
     *
     * @param wordIndex the map that is input
     *
     */
    //O(n log n) - Because it has to iterate through all n elements of the Tree Set
    public void wordsInOrder(Map wordIndex){
        Set<String> keys = wordIndex.keySet();
        SortedSet<String> sortedKeys = new TreeSet<>(keys);

        StringBuilder sb = new StringBuilder();
        final int[] count = {0};
        sortedKeys.forEach(key -> {
            sb.append(String.format("%-20s", key));
            count[0]++;
            if (count[0] % 5 == 0) {
                sb.append(System.getProperty("line.separator"));
            }
        });

        System.out.println(sb.toString());
    }

    /**
     * Takes a wordIndex and prints the words in reverse alphabetical order
     *  to the console. Prints 5 on a line.
     * @param wordIndex the map that is input
     */
    //O(n log n) - The time complexity to create a SortedSet
    public void wordsInReverseOrder(Map wordIndex){
        Set<String> keys = new TreeSet<>(wordIndex.keySet());
        SortedSet<String> sortedKeys = ((TreeSet<String>) keys).descendingSet();

        StringBuilder sb = new StringBuilder();
        AtomicInteger count = new AtomicInteger();
        sortedKeys.forEach(key -> {
            sb.append(String.format("%-20s", key));
            count.getAndIncrement();
            if (count.get() % 5 == 0) {
                sb.append(System.getProperty("line.separator"));
            }
        });

        System.out.println(sb.toString());
    }

    /**
     * Takes a wordIndex and returns the number of words that only appear
     * once.
     * @param wordIndex the map that is input
     * @return the number of words that only appear once
     */
    //O(n) - Because it has to get all n elements of the Tree Set and check if they are unique
    public int totalNumberOFUniqueWords(Map<String, WordDetail>  wordIndex){
        int numberOfUniqueWords = 0;
        for (ConcurrentSkipListMap.Entry<String, WordDetail> entry : wordIndex.entrySet()) {
            if(entry.getValue().getPages().size() == 1){
                numberOfUniqueWords++;
            }
        }
        return numberOfUniqueWords;
    }

    /**
     * Takes a wordIndex and prints the 5 most frequently occurring words
     * @param wordIndex the map that is input
     */
    //O(n2 log n) - Because the outer loop is O(n) and the inner loop to sort them is O(n log n)
    public void mostCommonWords(Map<String, WordDetail>  wordIndex){
        Map<String, Integer> map = new HashMap<>();
        for (ConcurrentSkipListMap.Entry<String, WordDetail> entry : wordIndex.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getPages().size());
        }

        map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(5).forEach(System.out::println);

    }
}
