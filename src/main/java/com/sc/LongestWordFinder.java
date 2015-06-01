package com.sc;

import java.io.IOException;
import java.util.Set;


public final class LongestWordFinder {

    public LongestWordFinder(WordCompositionAlgorithm wordConstructor) {
        if (null == wordConstructor) throw new ExceptionInInitializerError("Please provide valid algorithm for processing.");
        this.wordConstructor = wordConstructor;
    }

    /**
     * Prevent from initalizing without valid processor
     */
    private LongestWordFinder() {
    }

    public String findLongestConstructedWordFrom(Set<String> wordsToProcess) throws IOException {
        if (null == wordsToProcess) throw new IllegalArgumentException("Please provide a set of words to iterate through.");

        String longestWord = "";
        for (String lineWord : wordsToProcess) {

            if (wordConstructor.isConstructedFromOtherWords(lineWord)) {
                if (longestWord.length() < lineWord.length()) {
                    longestWord = lineWord;
                }
            }

        }

        return longestWord;

    }

    private WordCompositionAlgorithm wordConstructor;

}
