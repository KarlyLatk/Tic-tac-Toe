package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class OpportunistPlayer {
    String playerMark;

    public String chooseMove(String[] board){
        // If it is the first move of the game (board is empty), the opportunistic computer player marks one of the four corner spaces.
        if(isBoardEmpty(board)){
            // Get a random one of the four corner spaces
            List<String> list = Arrays.asList("1", "3", "7", "9");
            Random rand = new Random();
            String randomCorner = list.get(rand.nextInt(list.size()));

            return randomCorner;
        }
        // If it is the second move of the game AND the center space is available, the opportunistic computer player marks the center space.
        if(isSecondMove(board) && board[4].equals("5")){
            return "5";
        }
        // If the opportunistic player can move in a space where it would win the game, it takes that spot.
        int winSpot = detectWinningSpot(board);
        if(winSpot != -1){
            return board[winSpot];
        }
        // If the opportunistic player can move in a space where it would block its opponent from winning, it takes that spot.
        int blockSpot = detectBlockingSpot(board);
        if(blockSpot != -1){
            return board[blockSpot];
        }
        // The opportunistic player moves in a random spot on the board.
        List<String> moveList = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            // Check if the spot on board is still in default state
            boolean isSpotEmpty = String.valueOf(i + 1).equals(board[i]);
            if(isSpotEmpty) {
                // If spot is empty, add it to potential moves
                moveList.add(board[i]);
            }
        }

        Random rand = new Random();
        String randomMove = moveList.get(rand.nextInt(moveList.size()));
        return randomMove;
    }

    public int detectWinningSpot(String[] board) {
        // Go through rows in array
        for(int i=0; i < board.length; i+=3){
            // Check for an open spot in the row that would be a win
            int check = checkForWinningSpotAmongThree(board, i, i+1, i+2);
            // Return that spot if found
            if(check != -1){
                return check;
            }
        }

        // Go through columns in array
        for(int i=0; i < (board.length/3); i+=1) {
            // Check for an open spot in the column that would be a win
            int check = checkForWinningSpotAmongThree(board, i, i+3, i+6);
            // Return that spot if found
            if(check != -1){
                return check;
            }
        }

        // Check diagonals in array
        // Check for an open spot in the first diagonal that would be a win
        int check = checkForWinningSpotAmongThree(board, 0, 4, 8);
        // Return that spot if found
        if(check != -1){
            return check;
        }

        // Check for an open spot in the second diagonal that would be a win
        check = checkForWinningSpotAmongThree(board, 2, 4, 6);
        // Return that spot if found
        if(check != -1){
            return check;
        }

        // Return -1 if no winning spots were found
        return -1;
    }

    public int detectBlockingSpot(String[] board) {
        // Go through rows in array
        for(int i=0; i < board.length; i+=3){
            // Check for an open spot in the row that would block
            int check = checkForBlockingSpotAmongThree(board, i, i+1, i+2);
            // Return that spot if found
            if(check != -1){
                return check;
            }
        }

        // Go through columns in array
        for(int i=0; i < (board.length/3); i+=1) {
            // Check for an open spot in the column that would block
            int check = checkForBlockingSpotAmongThree(board, i, i+3, i+6);
            // Return that spot if found
            if(check != -1){
                return check;
            }
        }

        // Check diagonals in array
        // Check for an open spot in the first diagonal that would block
        int check = checkForBlockingSpotAmongThree(board, 0, 4, 8);
        // Return that spot if found
        if(check != -1){
            return check;
        }

        // Check for an open spot in the second diagonal that would block
        check = checkForBlockingSpotAmongThree(board, 2, 4, 6);
        // Return that spot if found
        if(check != -1){
            return check;
        }

        // Return -1 if no blocking spots were found
        return -1;
    }

    public int checkForWinningSpotAmongThree(String[] board, int i1, int i2, int i3){
        if(board[i1].equals(playerMark)){
            if(board[i2].equals(playerMark)) {
                // If first and second spot matches player, return third spot if open
                if(isSpotEmpty(board, i3)){
                    return i3;
                }
            }
            else if(board[i3].equals(playerMark)) {
                // If first and third spot matches player, return second spot if open
                if(isSpotEmpty(board, i2)){
                    return i2;
                }
            }
        }
        else if(board[i2].equals(playerMark) && board[i3].equals(playerMark)){
            // If second and third spot matches player, return first spot if open
            if(isSpotEmpty(board, i1)){
                return i1;
            }
        }

        return -1;
    }

    public int checkForBlockingSpotAmongThree(String[] board, int i1, int i2, int i3){
        if(!board[i1].equals(playerMark) && !isSpotEmpty(board, i1)){
            if(board[i1].equals(board[i2])) {
                // If first and second spot matches opponent, return third spot if open
                if(isSpotEmpty(board, i3)){
                    return i3;
                }
            }
            else if(board[i1].equals(board[i3])) {
                // If first and third spot matches opponent, return second spot if open
                if(isSpotEmpty(board, i2)){
                    return i2;
                }
            }
        }
        else if(!board[i2].equals(playerMark) && !isSpotEmpty(board, i2) && board[i2].equals(board[i3])){
            // If second and third spot matches opponent, return first spot if open
            if(isSpotEmpty(board, i1)){
                return i1;
            }
        }

        return -1;
    }

    public boolean isBoardEmpty(String[] board) {
        for(int i = 0; i < board.length; i++) {
            // Check if the spot on board is still in default state
            boolean isSpotEmpty = isSpotEmpty(board, i);
            if(!isSpotEmpty) {
                // If spot is not empty, board cannot be empty
                return false;
            }
        }
        // Return true if all spots are still in default state
        return true;
    }

    public boolean isSecondMove(String[] board) {
        int movesTaken = 0;
        for(int i = 0; i < board.length; i++) {
            // Check if the spot on board is still in default state
            boolean isSpotEmpty = isSpotEmpty(board, i);
            if(!isSpotEmpty) {
                // If spot is not empty, a move has been made
                movesTaken++;
            }
        }
        // Return true if exactly one spot is still in default state
        if(movesTaken == 1){
            return true;
        }
        else return false;
    }

    // Check if a spot on the board is open/empty/in default state
    public boolean isSpotEmpty(String[] board, int i) {
        boolean isSpotOpen = String.valueOf(i + 1).equals(board[i]);

        return isSpotOpen;
    }
}
