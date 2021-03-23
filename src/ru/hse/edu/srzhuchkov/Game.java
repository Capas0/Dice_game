package ru.hse.edu.srzhuchkov;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Dice Game class
 */
public class Game {
    private final int FACES = 6; // Number of dice faces

    private final int n; // Number of players
    private final int m; // Number of rounds won to win
    private final int k; // Number of dices

    private int round = 1;

    private Player roundLeader; // The player with the best score in the current round
    private Player leader;
    private Player lastRolled; // The player who made the last roll

    private final ArrayList<Player> players;
    private final ArrayList<Dice> dice;

    private boolean end = false; // Is the game over

    private boolean roundEnd = true; // Is the round finished

    private boolean commentPause = false; // Did all the rolls in the round occur

    private boolean rollPause = false; // Did the roll happen

    public Game(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        players = new ArrayList<>(n);
        dice = new ArrayList<>(k);

        // Initializing dice
        for (int i = 0; i < k; i++) {
            dice.add(new Dice(FACES));
        }

        // Initializing players
        for (int i = 0; i < n; i++) {
            players.add(i, new Player(this, i + 1));
        }
        leader = players.get(0);
        for (var player : players) {
            player.start();
        }

        // Initializing a commentator
        new Commentator(this).start();
    }

    /**
     * Starts the game
     */
    public void play() {
        for (; !end; round++) {
            synchronized (this) {
                processRound();

                roundEnd = true;
                roundLeader.incWins(); // Increase the score of the winner of the round
                if (roundLeader.getWins() > leader.getWins()) {
                    leader = roundLeader;
                }

                if (leader.getWins() == m) { // If the game is over
                    Collections.sort(players);
                    end = true;
                }
                commentPause = true;

                while (commentPause) { // Waiting for the output of a comment about the round
                    try {
                        wait(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                notifyAll();
            }
        }
    }

    /**
     * Starts the round
     */
    private synchronized void processRound() {
        Player buf = null;
        roundEnd = false;
        roundLeader = null;
        lastRolled = null;
        for (int playersRolled = 0; playersRolled < n; ) { // All players must make a roll
            if (buf != lastRolled) { // If the player has not made a roll yet
                if (roundLeader == null) {
                    roundLeader = lastRolled;
                }
                if (lastRolled.getCurrentScore() > roundLeader.getCurrentScore()) {
                    roundLeader = lastRolled;
                }
                buf = lastRolled;
                playersRolled++;

                commentPause = true;
                if (lastRolled.getCurrentScore() == FACES * k || playersRolled == n) {
                    // The first player to get the maximum score wins the round
                    return;
                }
            }

            try {
                wait(10);
            } catch (InterruptedException e) {
                return;
            }
            notifyAll();
        }
    }

    /**
     * Rolls all the dice
     *
     * @return result of the roll of all dice
     */
    int roll() {
        int res = 0;
        for (var dice : dice) {
            res += dice.roll();
        }
        return res;
    }

    public Player getRoundLeader() {
        return roundLeader;
    }

    public Player getLeader() {
        return leader;
    }

    public boolean isEnd() {
        return end;
    }

    public Player getLastRolled() {
        return lastRolled;
    }

    public void setLastRolled(Player lastRolled) {
        this.lastRolled = lastRolled;
    }

    public boolean isRoundEnd() {
        return roundEnd;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getRound() {
        return round;
    }

    public boolean isCommentPause() {
        return commentPause;
    }

    public void setCommentPause(boolean commentPause) {
        this.commentPause = commentPause;
    }

    public boolean isRollPause() {
        return rollPause;
    }

    public void setRollPause(boolean rollPause) {
        this.rollPause = rollPause;
    }
}
