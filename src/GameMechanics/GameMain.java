package GameMechanics;

import Players.PlayerInterface;
import Players.RandomPlayer;
import UI.BoardFrame;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gleb on 10/04/2019.
 */
public class GameMain {
    private int size;
    private PlayerInterface player1;    // player 1 = red
    private PlayerInterface player2;    // player 2 = blue
    private int player1Choice,player2Choice;


    public GameMain(int size,int player1Choice, int player2Choice){
        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
        this.size = size;

    }

    public RandomPlayer selectPlayers(int playerChoice, int playerNumber){
        switch (playerChoice){
            case 0:
                return new RandomPlayer(size,playerNumber);
                default:
                    System.out.println("Error in player selector");
        }
        System.out.println("error in player selector");
        return null;
    }

    public int startGame(){
        player1 = selectPlayers(player1Choice,1);
        player2 = selectPlayers(player2Choice,2);
        BoardFrame frame = new BoardFrame(size);
        int moveCounter = 0;
        while(!player1.getHasWon() && !player2.getHasWon()){
//            long now = System.currentTimeMillis();
//            long delta = 500;
//            while(System.currentTimeMillis()<now+delta){
//            }
            int move;
            if(moveCounter%2 == 0){
                move = player1.getMove();
                player2.updateOpponentsMove(move);
            } else {
                move = player2.getMove();
                player1.updateOpponentsMove(move);
            }
            frame.updateBoardAt((moveCounter%2)+1,move);
            frame.repaint();
            moveCounter ++;
        }
        return ((moveCounter+1)%2 + 1);
    }

    public void test(){
        System.out.print("1: ");
        int move = player1.getMove();
        System.out.print("1: ");
        player2.updateOpponentsMove(move);
        System.out.print("2: ");
        move=player2.getMove();
        System.out.print("2: ");
        player1.updateOpponentsMove(move);
    }
}
