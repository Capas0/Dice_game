package ru.hse.edu.srzhuchkov;

/**
 * Abstract class for commentaries
 */
public abstract class Commentary {
    protected String message; // Commentary text

    @Override
    public String toString() {
        return message;
    }
}
