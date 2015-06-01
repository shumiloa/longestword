package com.sc;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LongestWordMadeOtherWordsApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide an absolute path to the file with list of words to analyze.");

        final String pathToFile = scanner.nextLine();
        if (!exists(pathToFile)){
            System.out.println("File you specified >" + pathToFile + "< does not exist.");
            System.exit(1);
        }

        System.out.println("File " + pathToFile + " will be analyzed.");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(pathToFile));
            final Set<String> wordsToAnalize = wordsCollectionFrom(reader);
            WordCompositionAlgorithm wordCompositionAlgorithm = new WordCompositionAlgorithm(wordsToAnalize);

            LongestWordFinder longestWordFinder = new LongestWordFinder(wordCompositionAlgorithm);
            final String longestWordFromOtherWords = longestWordFinder.findLongestConstructedWordFrom(wordsToAnalize);

            if (0 < longestWordFromOtherWords.length())
                System.out.println("Longest word found: " + longestWordFromOtherWords);
            else
                System.out.println("No matching words found.");
        } catch (IOException e) {
            LOG.error("Error processing " + pathToFile, e);
        } finally {
            cleanupResources(reader);
        }
    }

    private static void cleanupResources(BufferedReader reader) {
        try {
            if (null != reader)
                reader.close();
        } catch (IOException e) {
            LOG.error("Cleanup Error", e);
        }
    }

    private static boolean exists(String pathToFile) {
        return new File(pathToFile).isFile();
    }

    static Set<String> wordsCollectionFrom(BufferedReader reader) throws IOException {
        String currentWord;
        Set<String> repoOfWords = new HashSet<String>();
        while ((currentWord = reader.readLine()) != null)
            repoOfWords.add(currentWord);

        return Collections.unmodifiableSet(repoOfWords);
    }
    private static final Logger LOG = Logger.getLogger(LongestWordMadeOtherWordsApp.class);

}
