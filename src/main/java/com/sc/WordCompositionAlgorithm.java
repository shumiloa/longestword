package com.sc;


import java.util.Set;

public final class WordCompositionAlgorithm {
    public WordCompositionAlgorithm(Set<String> words) {
        if (null == words) throw new ExceptionInInitializerError("Must provide a set of words to search.");
        this.words = words;
    }

    private WordCompositionAlgorithm(){

    }

    /**
     * Split the word and verify if parts are members of words collection
     * Second part analized recursively
     * @param toAnalize string to validate
     * @return if word wholly composed from other words
     */
    public boolean isConstructedFromOtherWords(final String toAnalize) {
        if (null == toAnalize || 0 == toAnalize.trim().length())
                            throw new IllegalArgumentException("Must provide a word to verify.");
        int i = 1;
        while (i < toAnalize.length()) {
            String prefix = toAnalize.substring(0, i);
            String suffix = toAnalize.substring(i);

            if (words.contains(prefix)) {
                if (words.contains(suffix)) {
                    return true;
                } else {
                    if (isConstructedFromOtherWords(suffix)) return true;
                }
            }
            i++;
        }
        return false;
    }

    private Set<String> words;
}
