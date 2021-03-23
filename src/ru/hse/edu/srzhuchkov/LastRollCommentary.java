package ru.hse.edu.srzhuchkov;

/**
 * Comment on the last roll of the round.
 * Unlike the {@link RollCommentary}, it does not contain information about the current leader of the round.
 */
public class LastRollCommentary extends RollCommentary {
    public LastRollCommentary(Player currentPlayer) {
        super(currentPlayer, null);
    }
}
