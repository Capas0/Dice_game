package ru.hse.edu.srzhuchkov;

/**
 * Commentary on the player's turn.
 * Contains a message with the result of the roll and the current leader of the round.
 */
public class RollCommentary extends Commentary {
    
    public RollCommentary(Player currentPlayer, Player leader) {
        message = String.format("%s - %02d.", currentPlayer, currentPlayer.getCurrentScore());
        if (leader != null) {
            message += String.format(" Leader: %s.", leader);
        }
    }
}
