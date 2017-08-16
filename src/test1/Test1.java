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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.stream.Stream;

/**
 *
 * @author Stephen
 */
public class Test1 {

    private static HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Get Project Root (Assuming file exists in project)
        String root = System.getProperty("user.dir");
        //Get the Path to the File
        Path path = Paths.get(root +"\\"+ "Railway-Children-by-E-Nesbit.txt");

        String bigString = "";

        //Add each line in the text to the list of strings
        try (Stream<String> s = lines(path)) {
            s.forEach(line -> updateWordCount(line));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Print out each word and count
        for (SortedMap.Entry<String, Integer> entry : wordMap.entrySet()) {

            String word = entry.getKey();
            int count = entry.getValue();

            System.out.println(word + " = " + count 
                    + " is" + (isPrime(count) ? " prime" : "n't prime"));
        }
    }

    public static Stream<String> lines(Path path) throws IOException {
        return Files.lines(path);
    }

    public static void updateWordCount(String string) {

        //Check that the string isnt a new line, ie empty
        if (!string.isEmpty()) {

            //Extract individual words
            for (String word : string.split(" ")) {

                //Remove non-alphanumeric characters from word
                word = word.replaceAll("[^a-zA-Z\\\\s]", "");

                //Remove capatilisations which may be treated as new words
                word = word.toLowerCase();

                if (!word.isEmpty()) {
                    //If the word is new, add it to the map, 
                    //otherwise increment the count
                    if (wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }
        }
    }

    public static boolean isPrime(int number) {
        
        if (number == 1){
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
