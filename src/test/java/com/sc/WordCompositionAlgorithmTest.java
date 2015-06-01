package com.sc;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WordCompositionAlgorithmTest {

    @BeforeClass
    public static void prepareWords() {
        Set<String> wordsRepository = new HashSet<String>(Arrays.asList(words));
        wordCompositionAlgo = new WordCompositionAlgorithm(wordsRepository);
    }

    @Test
    public void wordIsNotInRegistry() throws Exception {
        assertFalse(isCandidate("synchrophasotron"));
    }

    @Test
    public void shortestWordCantBeBuildFromOthers() throws Exception {
        assertFalse(isCandidate("cats"));
    }

    @Test
    public void testWordContainingOtherWords() throws Exception {
        assertTrue(isCandidate("catsdogcats"));
    }


    /**
     * Validate exceptional conditions
     */

    @Test(expected = ExceptionInInitializerError.class)
    public void shouldValidateInitialization(){
        new WordCompositionAlgorithm(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustPassNonEmptyStringToAnalyze(){
        wordCompositionAlgo.isConstructedFromOtherWords("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustPassNotNullStringToAnalyze(){
        wordCompositionAlgo.isConstructedFromOtherWords(null);
    }

    public static boolean isCandidate(final String toAnalyze) {
       return wordCompositionAlgo.isConstructedFromOtherWords(toAnalyze);
    }

    private final static String[] words = {"cat", "cats", "catsdogcats", "catxdogcatsrat", "dog", "dogcatsdog", "hippopotamuses", "rat"};
    private static WordCompositionAlgorithm wordCompositionAlgo;

}
