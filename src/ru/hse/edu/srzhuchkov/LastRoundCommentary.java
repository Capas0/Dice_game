package ru.hse.edu.srzhuchkov;

/**
 * Last round commentary.
 * Unlike the {@link RoundCommentary}, it does not contain information about the current leader of the game.
 */
public class LastRoundCommentary extends RoundCommentary {
    public LastRoundCommentary(int round, Player winner) {
        super(round, winner, null);
    }
}
