package com.hufftech.Text;

/**
 * Exception generated if the user tries to access a word out of bounds of the passage.
 */
public class WordOutOfBoundsException extends RuntimeException {
    public WordOutOfBoundsException(String message) {
        super(message);
    }
    public WordOutOfBoundsException(int index, int size) {
        this("Index " + index + " is out of bounds for passage size " + size + ".");
    }
}
