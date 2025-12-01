package org.example;

import java.util.Scanner;

public class GameMenu {
    GameLog log;
    OpportunistPlayer comp;
    Scanner in;

    public void startUp(){
        in = new Scanner(System.in);
        boolean playAgain = true;
        log = new GameLog();
        comp = new OpportunistPlayer();
        do {
            System.out.println("Welcome to Tic-Tac-Toe!");
            int gameChoice = getGameChoice();
            String winner = "";

            // Choosing between which games the player will play
            switch(gameChoice) {
                case 1:
                    // Human vs Human
                    winner = pvp();
                    break;
                case 2:
                    // Human vs Computer
                    System.out.println("Great! The computer will go second.");
                    winner = pvc();
                    break;
                case 3:
                    // Computer vs Human
                    System.out.println("Great! The computer will go first.");
                    winner = cvp();
                    break;
            }

            if (winner.equalsIgnoreCase("T")) {
                log.draws++;
                System.out.print("It's a tie! ");
            }
            // Count player wins using GameLog
            else {
                System.out.print("Player " + winner + " wins! ");

                if (winner.equals(log.p1Mark)) {
                    log.p1Wins++;
                    log.pFirst = log.p2Mark;
                } else if (winner.equals(log.p2Mark)) {
                    log.p2Wins++;
                    log.pFirst = log.p1Mark;
                }
            }
            //Print logs out
            System.out.println(log.printLog());
            System.out.println("Would you like to play again? (yes/no)? ");
            String reply = in.nextLine();
            while (!(reply.equalsIgnoreCase("yes")) && !(reply.equalsIgnoreCase("no"))) {
                System.out.println("That is not a valid entry!");
                System.out.print("Would you like to play again? (yes/no)? ");
                reply = in.nextLine();
            }
            if (reply.equalsIgnoreCase("no")) {
                System.out.println("Writing the game log to disk. Please see game.txt for the final statistics! ");
                log.writeLog();
                playAgain = false;
            } else {
                System.out.println("Great! This time " + log.pFirst + " will go first!");
            }

        } while (playAgain);
    }

    public int getGameChoice() {
        int parsed = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("What kind of game would you like to play?");
        System.out.println("1. Human vs. Human");

        // Who goes first
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");

        // Keep going until choice is found
        while(!(parsed >= 1 && parsed <= 3)) {
            try{
                System.out.print("What is your selection? ");
                String choice = in.nextLine();
                // Try to parse the input string to an integer
                parsed = Integer.parseInt(choice);

                // If it is a valid integer, but not the correct number, throw an exception
                if (!(parsed >= 1 && parsed <= 3)) {
                    throw new Exception();
                }
            }catch(Exception e){
                System.out.println("Invalid choice. Please try again.");
            }
        }
        return parsed;
    }

    // Human vs Human Game
    public String pvp(){
        String player1 = log.p1Mark;
        String player2 = log.p2Mark;
        String player = log.pFirst;
        TicTacToe game = new TicTacToe();
        boolean continueGame = true;
        do {
            System.out.println(game.printBoard());
            System.out.println("It is " + player + "'s turn");
            System.out.print("What is your move? ");
            String move = in.nextLine();
            // Check if move is valid
            if (game.move(player, move)) {
                //Swap turn
                if (player.equals(player1)) {
                    player = player2;
                } else {
                    player = player1;
                }
            } else {
                System.out.println("That is not a valid move! Try again.");
            }
            if (!game.checkForWin().isEmpty()) {
                continueGame = false;
            }
        } while (continueGame);

        return game.checkForWin();
    }

    // Human vs Computer Game
    public String pvc(){
        TicTacToe game = new TicTacToe();
        comp.playerMark = log.p2Mark;
        boolean continueGame = true;
        do {
            System.out.println(game.printBoard());
            System.out.println("It is " + log.p1Mark + "'s turn");
            System.out.print("What is your move? ");
            String move = in.nextLine();
            // Check if player move is valid
            if (game.move(log.p1Mark, move)) {
                if (!game.checkForWin().isEmpty()) {
                    return game.checkForWin();
                }
                System.out.println("Computer is making its move...");
                game.move(log.p2Mark, comp.chooseMove(game.board));
            } else {
                System.out.println("That is not a valid move! Try again.");
            }
            if (!game.checkForWin().isEmpty()) {
                System.out.println(game.printBoard());
                continueGame = false;
            }

        } while (continueGame);
        return game.checkForWin();
    }

    // Computer vs Human Game
    public String cvp(){
        comp.playerMark = log.p1Mark;
        TicTacToe game = new TicTacToe();
        boolean continueGame = true;
        do {
            System.out.println("Computer is making its move...");
            game.move(log.p1Mark, comp.chooseMove(game.board));
            System.out.println(game.printBoard());
            if (!game.checkForWin().isEmpty()) {
                return game.checkForWin();
            }
            System.out.println("It is " + log.p2Mark + "'s turn");
            System.out.print("What is your move? ");
            String move = in.nextLine();
            // Check if move is valid
            if (game.move(log.p2Mark, move)) {

            } else {
                System.out.println("That is not a valid move! Try again.");
            }
            if (!game.checkForWin().isEmpty()) {
                continueGame = false;
            }
        } while (continueGame);

        return game.checkForWin();
    }
}
