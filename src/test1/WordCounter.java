/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.stream.Stream;

/**
 *
 * @author Stephen
 */
public class WordCounter {

    /**
     * Get the HashMap for the RailWayChildren
     *
     * @return
     */
    public HashMap<String, Integer> getRailWayChildrenHashMap() {

        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

        //Get Project Root (Assuming file exists in project)
        String root = System.getProperty("user.dir");

        //Get the Path to the File
        Path path = Paths.get(root + "\\" + "Railway-Children-by-E-Nesbit.txt");

        //Add each line in the text to the list of strings
        try (Stream<String> s = lines(path)) {
            s.forEach(line -> wordMap.putAll(updateWordCount(line, wordMap)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return wordMap;
    }

    /**
     * Print out word map
     * @param wordMap 
     */
    public void printWordMap(HashMap<String, Integer> wordMap) {

        //Print out each word and count
        for (HashMap.Entry<String, Integer> entry : wordMap.entrySet()) {

            String word = entry.getKey();
            int count = entry.getValue();

            System.out.println(word + " = " + count
                    + " is" + (isPrime(count) ? " prime" : "n't prime"));
        }
    }

    /**
     * Get Stream of Strings from Path
     *
     * @param path
     * @return
     * @throws IOException
     */
    public Stream<String> lines(Path path) throws IOException {
        return Files.lines(path);
    }

    public HashMap<String, Integer> updateWordCount(String string, HashMap<String, Integer> map) {

        //Check that the string isnt a new line, ie empty
        if (!string.isEmpty()) {

            //Remove non-alphanumeric characters from word
            string = string.replaceAll("[^a-zA-Z\\\\s]", " ");

            //Extract individual words
            for (String word : string.split(" ")) {

                //Remove capatilisations which may be treated as new words
                word = word.toLowerCase();

                if (!word.isEmpty()) {
                    //If the word is new, add it to the map, 
                    //otherwise increment the count
                    int count = 0;
                    if (!map.isEmpty() && map.containsKey(word)) {
                        count = map.get(word);
                    }
                    map.put(word, count + 1);
                }
            }
        }
        return map;
    }

    /**
     * Get a hash-map with word counts
     *
     * @param string
     */
    public HashMap<String, Integer> updateWordCount(String string) {
        return updateWordCount(string, new HashMap<String, Integer>());
    }

    /**
     * Check if Integer is Prime
     *
     * @param number
     * @return
     */
    public boolean isPrime(int number) {

        if (number == 1) {
            return false;
        }

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
