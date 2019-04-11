package GameMechanics;

import Players.HumanPlayer;
import Players.PlayerInterface;
import Players.RandomPlayer;
import Players.ShortestPathSimple;
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

    public PlayerInterface selectPlayers(int playerChoice, int playerNumber, BoardFrame frame){
        switch (playerChoice){            // from StartPanel
            case 0:                         // 0 = RandomPlayer
                return new RandomPlayer(size,playerNumber);
            case 1:

                return new HumanPlayer(size,playerNumber,frame);
            case 2:
            return new ShortestPathSimple(size,playerNumber);
                default:
                    System.out.println("Error in player selector");
        }
        System.out.println("error in player selector");
        return null;
    }

    public int startGame(){
        BoardFrame frame;
        if(player2Choice == 1 || player1Choice ==1){
            frame = new BoardFrame(size,1);    // with human player says human player on top
        } else {
            frame = new BoardFrame(size);
        }
        player1 = selectPlayers(player1Choice,1,frame);
        player2 = selectPlayers(player2Choice,2,frame);
        int moveCounter = 0;
        while(!player1.getHasWon() && !player2.getHasWon()){
            long now = System.currentTimeMillis();
            long delta = 500;
            while(System.currentTimeMillis()<now+delta){
            }
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
        if(player2Choice == 1 || player1Choice ==1) {
            String string = "Player " + ((moveCounter+1)%2 + 1) + " won";
            frame.setBoardTextArea(string);
        }
        System.out.println("winner player :" + ((moveCounter+1)%2 + 1) );
        return ((moveCounter+1)%2 + 1);
    }


}
