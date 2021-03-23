package ru.hse.edu.srzhuchkov;

import java.util.ArrayList;

/**
 * End-of-game commentary.
 * Contains a message with the winning player and the score of all players.
 */
public class FinalCommentary extends Commentary {
    public FinalCommentary(ArrayList<Player> players) {
        StringBuilder res = new StringBuilder("End game\n\n" +
                String.format("%s is the champion! Congratulations!\n\n", players.get(0)));
        for (var player : players) {
            res.append(String.format("%s - %d wins.\n", player, player.getWins()));
        }
        message = res.toString();
    }
}
