package com.sc;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LongestWordFinderTest {

    @Before
    public void prepareCompAlgorithm() throws Exception {

        reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/words.txt")));

        words = LongestWordMadeOtherWordsApp.wordsCollectionFrom(reader);

        compositionAlgorithm = new WordCompositionAlgorithm(words);
    }

    @Test
    public void shouldFindLongestFromIterator() throws Exception {

        LongestWordFinder longestWordFinder = new LongestWordFinder(compositionAlgorithm);

        final String longestFound = "ratcatdogcat";
        assertEquals(longestFound, longestWordFinder.findLongestConstructedWordFrom(words));

    }

    @Test(expected = ExceptionInInitializerError.class)
    public void shouldInitalizeWithProperAlgo() throws Exception {
        new LongestWordFinder(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldPassNotNullListOfWords() throws Exception{
        LongestWordFinder longestWordFinder = new LongestWordFinder(compositionAlgorithm);
        longestWordFinder.findLongestConstructedWordFrom(null);
    }

    @After
    public void cleanup() throws Exception {
        if (null != reader) reader.close();
    }



    private Set<String> words;
    private WordCompositionAlgorithm compositionAlgorithm;
    private BufferedReader reader;

}
