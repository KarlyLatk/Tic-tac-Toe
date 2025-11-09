package org.example;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      boolean playAgain = true;
      GameLog log = new GameLog();
      do {
          String player = log.pFirst;
          TicTacToe game = new TicTacToe();
          boolean continueGame = true;
          System.out.println("Welcome to Tic-Tac-Toe!");

          do{
              System.out.println(game.printBoard());
              System.out.println("It is " + player + "'s turn");
              System.out.print("What is your move? ");
              String move = in.nextLine();
              // Check if move is valid
              if(game.move(player, move)){
                  //Swap turn
                  if (player.equals(log.p1Mark)) {
                      player = log.p2Mark;
                  }
                  else{
                      player = log.p1Mark;
                  }
              }
              else{
                  System.out.println("That is not a valid move! Try again.");
              }
              if(!game.checkForWin().equals("")){
                 continueGame = false;
              }
          }while(continueGame);

          if (game.checkForWin().equalsIgnoreCase("T")){
              log.draws++;
              System.out.print("It's a tie! ");
          }
          // Count player wins using GameLog
          else{
              String winner = game.checkForWin();
              System.out.print("Player " + winner + " wins! ");

              if(winner.equals(log.p1Mark)){
                  log.p1Wins++;
                  log.pFirst = log.p2Mark;
              }
              else if(winner.equals(log.p2Mark)){
                  log.p2Wins++;
                  log.pFirst = log.p1Mark;
              }
          }
          //Print logs out
          System.out.println(log.printLog());
          System.out.println("Would you like to play again? (yes/no)? ");
          String reply = in.nextLine();
          while(!(reply.equalsIgnoreCase("yes")) && !(reply.equalsIgnoreCase("no"))){
              System.out.println("That is not a valid entry!");
              System.out.print("Would you like to play again? (yes/no)? ");
              reply = in.nextLine();
          }
          if (reply.equalsIgnoreCase("no")){
              System.out.println("Writing the game log to disk. Please see game.txt for the final statistics! ");
              log.writeLog();
              playAgain = false;
          }
          else{
              System.out.println("Great! This time " + log.pFirst + " will go first!");
          }

      }while(playAgain);

  }
}
