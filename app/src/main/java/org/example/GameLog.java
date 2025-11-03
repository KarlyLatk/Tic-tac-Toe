package org.example;

public class GameLog {
    public int p1Wins;
    public int p2Wins;
    public int draws;

    public String p1Mark;
    public String p2Mark;

    public String pFirst;

    public GameLog(){
        p1Wins = 0;
        p2Wins = 0;
        draws = 0;

        p1Mark = "X";
        p2Mark = "O";

        pFirst = p1Mark;

    }

    public String printLog(){
        return "The current log is: \n\n" + "Player " + p1Mark + " Wins\t" + p1Wins +  "\nPlayer " + p2Mark + " Wins\t" + p2Wins + "\nTies\t\t\t" + draws + "\n";
    }

    public void writeLog(){
        GameWriter writer = new GameWriter("game.txt");
        writer.write_data(printLog());
    }
}
