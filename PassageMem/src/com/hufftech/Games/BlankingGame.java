package com.hufftech.Games;

import com.hufftech.Text.Passage;
import com.hufftech.Text.WordOutOfBoundsException;
import org.jetbrains.annotations.NotNull;

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
}
