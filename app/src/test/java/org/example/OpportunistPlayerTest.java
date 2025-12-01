package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpportunistPlayerTest {
    OpportunistPlayer comp;
    String[] board;

    @BeforeEach
    public void setUp(){
        comp = new OpportunistPlayer();
        comp.playerMark = "X";
        board = new String[]{"1", "2", "3",
                             "4", "5", "6",
                             "7", "8", "9"};
    }

    @Test
    public void testChooseMove(){
        board[6] = "O";
        assertEquals(comp.chooseMove(board), "5");
        board[8] = "O";
        assertEquals(comp.chooseMove(board), "8");
        board[3] = "X";
        board[4] = "X";
        assertEquals(comp.chooseMove(board), "6");
        board[1] = "Anything";
        board[5] = "Anything";
        board[7] = "Anything";
        board[0] = "Anything";
        assertEquals(comp.chooseMove(board), "3");
    }

    @Test
    void detectWinningSpot() {
        board[0] = "X";
        board[4] = "X";
        assertEquals(comp.detectWinningSpot(board), 8);
    }

    @Test
    void detectBlockingSpot() {
        board[2] = "O";
        board[4] = "O";
        assertEquals(comp.detectBlockingSpot(board), 6);
    }

    @Test
    void testCheckForWinningSpotAmongThree() {
        board[0] = "X";
        board[1] = "X";
        assertEquals(comp.checkForWinningSpotAmongThree(board, 0, 1, 2), 2);
    }

    @Test
    void testCheckForWinningSpotAmongThreeFail() {
        board[0] = "X";
        board[1] = "X";
        board[2] = "X";
        assertEquals(comp.checkForWinningSpotAmongThree(board, 0, 1, 2), -1);
    }

    @Test
    void testCheckForBlockingSpotAmongThree() {
        board[0] = "O";
        board[1] = "O";
        assertEquals(comp.checkForBlockingSpotAmongThree(board, 0, 1, 2), 2);
    }

    @Test
    void testCheckForBlockingSpotAmongThreeFail() {
        board[0] = "O";
        board[1] = "O";
        board[2] = "O";
        assertEquals(comp.checkForBlockingSpotAmongThree(board, 0, 1, 2), -1);
    }

    @Test
    void testIsBoardEmptyTrue() {
        assertEquals(comp.isBoardEmpty(board), true);
    }

    @Test
    void testIsBoardEmptyFalse() {
        board[0] = "Anything";
        assertEquals(comp.isBoardEmpty(board), false);
    }

    @Test
    void testIsSecondMoveTrue() {
        board[0] = "Anything";
        assertEquals(comp.isSecondMove(board), true);
    }

    @Test
    void testIsSecondMoveFalse() {
        assertEquals(comp.isSecondMove(board), false);
    }

    @Test
    void testIsSpotEmptyTrue() {
        assertEquals(comp.isSpotEmpty(board, 0), true);
    }
    @Test
    void testIsSpotEmptyFalse() {
        board[0] = "Anything";
        assertEquals(comp.isSpotEmpty(board, 0), false);
    }
}
