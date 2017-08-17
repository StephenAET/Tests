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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //WordCounter wordCounter = new WordCounter();
        //wordCounter.getRailWayChildrenHashMap();
        Bowling bowling = new Bowling();
        bowling.getBowlingScore("X|7/|9-|X|-8|8/|-6|X|X|X||81");
    }
}