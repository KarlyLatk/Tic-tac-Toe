package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogTest {
    GameLog gameLog;
    @BeforeEach
    public void setUp() {
        gameLog = new GameLog();
    }
    @Test
    public void testPrintLog(){
        gameLog.p1Wins++;
        assertEquals(gameLog.printLog(), "The current log is: \n\nPlayer X Wins\t1\nPlayer O Wins\t0\nTies\t\t\t0\n");
    }
}
