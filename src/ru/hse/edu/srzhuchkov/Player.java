package ru.hse.edu.srzhuchkov;

/**
 * Dice player
 */
public class Player extends Thread implements Comparable<Player> {
    private final int number; // Player's number like player #1
    private int wins = 0;
    private int currentScore = 0; // The result of the last roll

    private int lastRound = 0; // The last round in which the player made the roll

    private final Game host; // The game in which the player participates

    public Player(Game host, int number) {
        super(String.format("Player_%d", number));
        this.host = host;
        this.number = number;
    }

    /**
     * Makes a roll
     */
    public void roll() {
        if (host.isRoundEnd()) {
            return;
        }
        currentScore = host.roll();
        host.setLastRolled(this);
        host.setRollPause(true);
    }

    @Override
    public void run() {
        while (!host.isEnd()) { // Until the game is over
            synchronized (host) {
                while (!host.isEnd() && (host.isRoundEnd() || host.isRollPause() || host.getRound() == lastRound)) {
                    // Waiting for a turn
                    try {
                        host.wait(10);
                    } catch (InterruptedException e) {
                        host.notifyAll();
                        return;
                    }
                }

                lastRound = host.getRound();
                if (host.isEnd()) {
                    return;
                }
                roll();
                host.notifyAll();
            }
        }
    }

    /**
     * Compare players' rank. The player with higher score is less than the other
     *
     * @param other The player to be compared with
     * @return the value {@code 0} if this {@code Player}'s score is
     * equal to the argument {@code Player}'s; a value less than
     * {@code 0} if this {@code Player}'s score is greater
     * than the argument {@code Player}'s; and a value greater
     * than {@code 0} if this {@code Player}'s score is
     * less than the argument {@code Player}'s score.
     */
    @Override
    public int compareTo(Player other) {
        return -Integer.compare(wins, other.wins);
    }

    @Override
    public String toString() {
        return "Player_" + number;
    }

    public int getWins() {
        return wins;
    }

    /**
     * Increases the number of wins counter by 1
     */
    public void incWins() {
        wins++;
    }

    public int getCurrentScore() {
        return currentScore;
    }
}
