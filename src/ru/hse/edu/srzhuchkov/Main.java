package ru.hse.edu.srzhuchkov;

public class Main {

    public static void main(String[] args) {
        int n; // Number of players
        int m; // Number of rounds won to win
        int k; // Number of dices

        try {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
            m = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid arguments");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Valid arguments is: N K M.\n" + "N\tnumber of players;\n" + "K\tnumber of dice;\n" +
                    "M\tnumber of rounds need for the win.");
            return;
        }

        new Game(n, m, k).play();
    }
}
