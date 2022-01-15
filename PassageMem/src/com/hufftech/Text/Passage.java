package com.hufftech.Text;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A list of words in a passage of text.
 */
public class Passage implements Iterable<String> {

    /**
     * Constructor.
     * @param passage The passage.
     * @throws PassageDefinitionException If the passed-in passage is blank.
     */
    public Passage(@NotNull String passage) throws PassageDefinitionException {
        // I. Args check: 'passage' cannot be an empty string.
        // II. Setup simple fields.
        // III. Build the words list from the passage.

        passage = passage.trim();

        // I. Args check: 'passage' cannot be an empty string.
        if (passage.isEmpty()) {
            throw new PassageDefinitionException("Passage cannot be empty.");
        }

        // II. Setup simple fields.
        this.passage = passage;

        // III. Build the words list from the passage.
        int startOfWord = 0;
        int endOfWord = 0;
        int len = passage.length();
        String[] workingWords = new String[len];
        int insertLoc = 0;

        for (; endOfWord < len; endOfWord++) {
            if (passage.charAt(endOfWord) == ' ') {
                workingWords[insertLoc++] = passage.substring(startOfWord, endOfWord);
                startOfWord = endOfWord + 1;
            }
        }
        workingWords[insertLoc++] = passage.substring(startOfWord);

        this.words = new String[insertLoc];
        System.arraycopy(workingWords, 0, this.words, 0, insertLoc);
    }

    /**
     * Gets the word at the given 0-indexed word number.
     * @param index The index to get at.
     * @return The word at index.
     * @exception WordOutOfBoundsException If the index is out of bounds for this passage.
     */
    public @NotNull String wordAt(int index) throws WordOutOfBoundsException {
        if (inRange(index)) {
            return words[index];
        }
        else {
            throw new WordOutOfBoundsException(index, numWords());
        }
    }

    /**
     * Gets the number of words in the passage.
     * @return The number of words.
     */
    public int numWords() {
        return words.length;
    }

    /**
     * Gets the full passage.
     * @return The full passage, as a string.
     */
    public @NotNull String full() {
        return passage;
    }

    /**
     * True if the input passage matches the passage.
     * @param input The input to test.
     * @return True if they match.
     */
    public boolean matches(@NotNull String input) {
        boolean result = passage.equals(input.trim());

        if (result) {
            successes++;
        }
        else {
            fails++;
        }

        return result;
    }

    @Override
    public @NotNull Iterator<String> iterator() {
        return Arrays.stream(words).iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Passage p){
            return p.full().equals(full());
        }
        else {
            return false;
        }
    }

    private final String[] words;
    private final String passage;
    private int fails = 0;
    private int successes = 0;

    /**
     * True if the index is in range of this passage.
     * @param index The index to check.
     * @return True if index is in range.
     */
    protected boolean inRange(int index) {
        return index >= 0 && index < numWords();
    }

    /**
     * Passage test method.
     * @param args Unused.
     */
    public static void main(String[] args) {


        // Test constructor.
        boolean testConstructor = true;

        final int NUM_WORDS = 10000;
        StringBuilder sb = new StringBuilder();

        main: for (int i = 1; i <= NUM_WORDS; i++) {
            sb.append("word ");
            Passage p = new Passage(sb.toString());

            if (p.numWords() != i) {
                testConstructor = false;
                System.out.println("Test constructor: unexpected size " + p.numWords() + " on pass " + i);
                break;
            }

            for (int j = 0; j < p.numWords(); j++) {
                if (!p.wordAt(j).equals("word")) {
                    testConstructor = false;
                    System.out.print("Unexpected word '" + p.wordAt(j) + "' at loc " + j + " on pass " + i);
                    break main;
                }
            }
        }

        if (testConstructor){
            System.out.println("Test constructor: Successful.");
        }

        // Test constructor error.
        boolean constructorError = true;
        try {
            Passage p = new Passage("");
            System.out.println("Test constructor error: failed to generate PassageDefinitionException on empty input.");
            constructorError = false;
        }
        catch (PassageDefinitionException ignored) {
        }

        try {
            Passage p = new Passage("\t ");
            System.out.println("Test constructor error: failed to generate PassageDefinitionException on blank input.");
            constructorError = false;
        }
        catch (PassageDefinitionException ignored) {
        }

        if (constructorError) {
            System.out.println("Test constructor error: Successful.");
        }

        // Test iterator.
        boolean iterator = true;

        Passage p = new Passage("Example passage of size five.");
        String[] iter = new String[p.numWords()];
        String[] manual = new String[p.numWords()];

        int i1 = 0;
        for (String e : p) {
            iter[i1++] = e;
        }
        for (int i = 0; i < p.numWords(); i++) {
            manual[i] = p.wordAt(i);
        }

        for (int i = 0; i < p.numWords(); i++) {
            if (!iter[i].equals(manual[i])) {
                iterator = false;
                System.out.print("Test iterator(): Words between iterator array and manual copy array do not match at ");
                System.out.println("loc " + i + ": " + iter[i] + " vs. " + manual[i]);
                break;
            }
        }

        if (iterator) {
            System.out.println("Test iterator(): Successful.");
        }

        // Test full()
        boolean full = true;

        String passage = "This is my example passage.";
        p = new Passage(passage);

        if (!p.full().equals(passage)) {
            full = false;
            System.out.print("Test full(): passage input '" + passage + "' was not equal to passage output ");
            System.out.println("'" + p.full() + "'.");
        }

        passage = "\tThis is a tabbed example passage.\t";
        p = new Passage(passage);

        if (!p.full().equals(passage.trim())) {
            full = false;
            System.out.print("Test full(): trimmed passage input '" + passage.trim() + "' was not equal to passage output ");
            System.out.println("'" + p.full() + "'.");
        }

        if (full) {
            System.out.println("Test full(): Successful.");
        }
    }


}
