package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicTacToeTest {
    TicTacToe gameTest;
    @BeforeEach
    public void setUp() {
        gameTest = new TicTacToe();
    }
    @Test
    public void testInvalidMove(){
        assertEquals(gameTest.move("player1", "0"), false);
    }
    @Test
    public void testValidMove(){
        assertEquals(gameTest.move("player1", "1"), true);
    }
    @Test
    public void testForWin(){
        gameTest.board = new String[]{"X", "X", "X", "4", "5", "6", "7", "8", "9"};
        assertEquals(gameTest.checkForWin(), "X");
    }
    @Test
    public void testForDraw(){
        gameTest.board = new String[]{"X", "O", "X", "O", "X", "O", "O", "X", "O"};
        assertEquals(gameTest.checkForWin(), "T");
    }
    @Test
    public void testForOpenSpace(){
        gameTest.board = new String[]{"X", "O", "X", "O", "X", "6", "O", "X", "O"};
        assertEquals(gameTest.checkForWin(), "");
    }
}
