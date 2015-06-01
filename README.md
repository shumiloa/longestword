# Longest Word Made of Other Words
Program that reads a file containing a sorted list of words (one word per line, no spaces, all lower case) then identifies the longest word in the file that can be constructed by concatenating copies of shorterwords also found in the file.

## Usage
-Build a program:

``git clone https://github.com/poprygun/longestword.git longestword``

``cd longestword``

``mvn package``

-Execute:

``java -jar target/longestwordmadeofotherwords-1.0-SNAPSHOT-jar-with-dependencies.jar``

Provide an absolute path to a file that lists words to be analyzed when asked in console.

## Overview
- Every word in the list is split at each character, and both partitions are tested for the existence in the file.

- Suffix is tested recursively against all words in file if prefix is a valid word found on the list.

## Design Considerations
- SRP (Single Responsibility Principle) is used for modeling the process, where each class solves one logical problem.

    - LongestWordMadeOtherWordsApp.class
          - Loads the application, collects an absolute path to input file with list of words.
          - Instantiates components needed for input evaluation.
          
    - LongestWordFinder.class
          - Iterates through words, finding the longest one.  Uses helper class to identify valid candidates.
    
    - WordCompositionAlgorithm.class
          - Iteratively splits the word at each character, evaluating suffix and prefix against the list.
          - If prefix is identified as a word in the list than suffix is evaluated recursively.

- TDD (Test Driven Development) techniques were used during the implementation.  Test harnesses include testing of exceptional conditions when invalid parameters are passed.
    
    - Test data is in resources/words.txt
    - Testing is performed using http://junit.org/

- Errors are bubbled up to calling program and logged using Log4j.

## Prerequisites
- JDK 7
- Maven 3.0.x


          
     




