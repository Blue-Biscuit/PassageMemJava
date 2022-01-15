package com.hufftech.Games;

import com.hufftech.Text.Passage;
import com.hufftech.Text.WordOutOfBoundsException;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.Scanner;

/**
 * A memorization game in which words in the passage are blanked from view as the user
 * re-types the full passage.
 */
public class BlankingGame {
    /**
     * Constructor.
     * @param passage The passage on which to play the game.
     */
    public BlankingGame(@NotNull Passage passage) {
        this.passage = passage;
        blanked = new boolean[passage.numWords()];
    }

    /**
     * Blanks a word in the passage.
     * @param index The word to blank.
     * @throws WordOutOfBoundsException If the given index is out of bounds of the passage.
     */
    public void blankWord(int index) throws WordOutOfBoundsException {
        if (inRange(index)) {
            blanked[index] = true;
        }
        else {
            throw new WordOutOfBoundsException(index, passage.numWords());
        }
    }

    /**
     * Blanks a random word in the passage, based upon the input random number generator.
     * @param r The random number generator.
     */
    public void blankRandomWord(@NotNull Random r) {
        int index;
        do {
            index = r.nextInt(passage.numWords());
        } while (wordIsBlanked(index) && !allIsBlanked());
        blankWord(index);
    }

    /**
     * Blanks a random word in the passage, based upon the time of the method call.
     */
    public void blankRandomWord() {
        blankRandomWord(new Random());
    }

    /**
     * Checks if the given word index is blanked.
     * @param index The index of the word to check.
     * @return True if this word has been blanked.
     * @throws WordOutOfBoundsException The word index given is out of bounds of the passage.
     */
    public boolean wordIsBlanked(int index) throws WordOutOfBoundsException {
        if (inRange(index)) {
            return blanked[index];
        }
        else {
            throw new WordOutOfBoundsException(index, passage.numWords());
        }
    }

    /**
     * Whether every word in the passage is blanked or not.
     * @return True if every word in the passage is blanked.
     */
    public boolean allIsBlanked() {
        for (int i = 0; i < passageWords(); i++) {
            if (!wordIsBlanked(i)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the word, or a blank of the same size, at the index.
     * @param index The index to get at.
     * @return The word or the blank.
     * @exception WordOutOfBoundsException If the index is out of bounds for this passage.
     */
    public String wordAt(int index) throws WordOutOfBoundsException {
        String word = passage.wordAt(index);
        if (blanked[index]) {
            word = new String(new char[word.length()]).replace('\0', '_'); // Thanks, stackoverflow!
        }

        return word;
    }

    /**
     * True if the input equals the full passage.
     * @param input The input string.
     * @return True if the input matches the passage.
     */
    public boolean matches(@NotNull String input) {
        return passage.matches(input);
    }

    /**
     * Gets the number of words in the passage associated with this game.
     * @return The number of words.
     */
    public int passageWords() {
        return passage.numWords();
    }

    @Override
    public String toString() {
        if (passageWords() > 0) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < passageWords() - 1; i++) {
                sb.append(wordAt(i));
                sb.append(' ');
            }
            sb.append(wordAt(passageWords() - 1));

            return sb.toString();
        }
        else {
            return "";
        }
    }

    private final Passage passage;
    private final boolean[] blanked;

    /**
     * Checks if the given index is in-range.
     * @param index The index to check.
     * @return True if the index is in range.
     */
    protected boolean inRange(int index) {
        return index >= 0 && index < passage.numWords();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        Passage p = new Passage(input);
        BlankingGame bg = new BlankingGame(p);

        for (int i = 0; i < p.numWords(); i++) {
            System.out.println(bg);
            bg.blankRandomWord();
        }
        System.out.println(bg);
    }
}
