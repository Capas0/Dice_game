package ru.hse.edu.srzhuchkov;

import java.util.Random;

public class Dice {
    private final int faces; // Number of dice faces
    private final Random rnd = new Random();

    public Dice(int faces) {
        this.faces = faces;
    }

    /**
     * Performs a roll of the dice
     * @return result of the dice roll
     */
    public int roll() {
        return 1 + rnd.nextInt(faces);
    }
}
