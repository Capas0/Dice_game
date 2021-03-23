package ru.hse.edu.srzhuchkov;

/**
 * Game commentator. Reports the results of the rounds and the final score.
 */
public class Commentator extends Thread {
    private final Game host; // Commented game

    public Commentator(Game host) {
        super("Commentator");
        this.host = host;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (host) {
                while (!host.isCommentPause()) { // Waiting for the end of the round
                    try {
                        host.wait(1000);
                    } catch (InterruptedException e) {
                        host.setCommentPause(false);
                        return;
                    }
                }

                if (host.isEnd()) {
                    // Output of the results of the last round and the final score
                    System.out.println(new LastRoundCommentary(host.getRound(), host.getRoundLeader()));
                    System.out.println(new FinalCommentary(host.getPlayers()));
                    host.setCommentPause(false);
                    return;
                }

                if (host.isRoundEnd()) {
                    // Output of round results
                    System.out.println(new LastRollCommentary(host.getLastRolled()));
                    System.out.println(new RoundCommentary(host.getRound(), host.getRoundLeader(), host.getLeader()));
                }
                else {
                    // Output of roll results
                    System.out.println(new RollCommentary(host.getLastRolled(), host.getRoundLeader()));
                }

                host.setCommentPause(false);
                host.setRollPause(false);

                host.notifyAll();
            }
        }
    }
}
