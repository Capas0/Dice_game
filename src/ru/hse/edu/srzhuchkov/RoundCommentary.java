package ru.hse.edu.srzhuchkov;

/**
 * End of round commentary.
 * Contains a message with the round number,
 * the winner of the round, the result of his roll and the victory counter,
 * as well as the current leader of the game.
 */
public class RoundCommentary extends Commentary {
    public RoundCommentary(int round, Player winner, Player leader) {
        message = "\n" + "=".repeat(30) + String.format("\nEnd of round %d\n\n", round) +
                String.format("%s wins. Score: %d. Won %d times.\n",
                        winner, winner.getCurrentScore(), winner.getWins());
        if (leader != null) {
            message += String.format("\nCurrent leader: %s - %d wins.\n",
                    leader, leader.getWins());
        }
        message += "=".repeat(30) + "\n";
    }
}
