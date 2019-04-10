package GameMechanics;

import Players.PlayerInterface;
import Players.RandomPlayer;
import UI.BoardFrame;

import java.awt.*;
import java.util.Random;

/**
 * Created by Gleb on 10/04/2019.
 */
public class GameMain {
    private int size;
    private PlayerInterface player1;    // player 1 = red
    private PlayerInterface player2;    // player 2 = blue


    public GameMain(int size){
        this.size = size;
        player1 = new RandomPlayer(size,1);
        player2 = new RandomPlayer(size,2);
    }

    public void startGame(){
        BoardFrame frame = new BoardFrame(size);
        int moveCounter = 0;
        int move =0;
        while(!player1.getHasWon() && !player2.getHasWon()){

            int moveOF = (moveCounter%2);
            if(moveOF == 0){
                move = player1.getMove();
                player2.updateOpponentsMove(move);
            } else {
                move = player2.getMove();
                player1.updateOpponentsMove(move);
            }
            System.out.println(move);
            frame.updateBoardAt(moveOF+1,move);
            frame.repaint();
            moveCounter++;

        }
        System.out.println("done" + moveCounter + "winner is " + ((moveCounter%2)));
    }
}
