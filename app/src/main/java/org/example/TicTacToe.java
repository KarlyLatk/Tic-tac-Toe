package org.example;

public class TicTacToe {
    public String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public boolean move (String player, String space) {
        if(space.equalsIgnoreCase("X") || space.equalsIgnoreCase("O")) {
            return false;
        }
        for(int i = 0; i < board.length; i++) {
            if(board[i].equals(space)) {
                board[i] = player;
                return true;
            }
        }
        return false;
    }

    public String checkForWin() {
        // Check rows in array
        for(int i = 0; i < board.length; i+=3) {
           if(board[i].equals(board[i+1]) && board[i].equals(board[i+2])) {
               return board[i];
           }
        }
        // Check columns in array
        for(int i = 0; i < (board.length/3); i+=1) {
            if(board[i].equals(board[i+3]) && board[i].equals(board[i+6])) {
                return board[i];
            }
        }
        // Check diagonals in array
        if(board[0].equals(board[4]) && board[0].equals(board[8])){
            return board[0];
        }
        if(board[2].equals(board[4]) && board[2].equals(board[6])){
            return board[2];
        }

        //Check for open spaces
        for(int i = 0; i < board.length; i++) {
            if(!board[i].equalsIgnoreCase("X") && !board[i].equalsIgnoreCase("O")) {
                return "";
            }
        }
        // Returns "T" for tie

        return "T";
    }

    public String printBoard() {
        return board[0] + " | " + board[1] + " | " + board[2] + "\n-----------\n" +
                board[3] +  " | " + board[4] + " | " + board[5] + "\n-----------\n" +
                board[6] + " | " + board[7] + " | " + board[8];
    }
}
