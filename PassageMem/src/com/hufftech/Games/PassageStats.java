package com.hufftech.Games;

import com.hufftech.Text.Passage;
import org.jetbrains.annotations.NotNull;

/**
 * Statistics associated to performance learning a passage.
 */
public class PassageStats {
    /**
     * Constructs a completely new statistics object,
     * with nil scores.
     */
    public PassageStats(@NotNull Passage p) {
        passage = p;

        successes = 0;
        fails = 0;
    }

    /**
     * Copy constructor.
     * @param ps the PassageStats object to copy.
     */
    public PassageStats(@NotNull PassageStats ps) {
        successes = ps.getSuccesses();
        fails = ps.getFails();
        passage = ps.getPassage();
    }

    /**
     * Increments the number of successes.
     */
    public void incSuccesses() {
        successes++;
    }

    /**
     * Increments the number of fails.
     */
    public void incFails() {
        fails++;
    }

    /**
     * Gets the number of successes.
     * @return The number of successes.
     */
    public int getSuccesses() {
        return successes;
    }

    /**
     * Gets the number of fails.
     * @return The number of fails.
     */
    public int getFails() {
        return fails;
    }

    /**
     * Gets the passage associated with the statistics.
     * @return The passage.
     */
    public Passage getPassage() {
        return passage;
    }

    private final Passage passage;
    private int successes;
    private int fails;
}
