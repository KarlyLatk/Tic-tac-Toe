package org.example;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      boolean continueGame = true;
      boolean playAgain = true;
      do {
          String player = "X";
          TicTacToe game = new TicTacToe();
          System.out.println("Welcome to Tic-Tac-Toe!");
          do{
              System.out.println(game.printBoard());
              System.out.print("What is your move? ");
              String move = in.nextLine();
              // Check if move is valid
              if(game.move(player, move)){
                  //Swap turn
                  if (player.equals("X")) {
                      player = "O";
                  }
                  else{
                      player = "X";
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
              System.out.println("It's a tie!");
          }
          else{
              System.out.println("Player " + game.checkForWin() + " wins!");;
          }
          System.out.println("Would you like to play again? (yes/no)? ");
          String reply = in.nextLine();
          while(!(reply.equalsIgnoreCase("yes")) && !(reply.equalsIgnoreCase("no"))){
              System.out.println("That is not a valid entry!");
              System.out.print("Would you like to play again? (yes/no)? ");
              reply = in.nextLine();
          }
          if (reply.equalsIgnoreCase("no")){
              System.out.println("Goodbye!");
              playAgain = false;
          }

      }while(playAgain);

  }
}
