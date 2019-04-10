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
    private PlayerInterface player1;
    private PlayerInterface player2;

    public GameMain(int size){
        this.size = size;
        player1 = new RandomPlayer(size);
        player2 = new RandomPlayer(size);
    }

    public void startGame(){
        BoardFrame frame = new BoardFrame(size);
        int moveCounter = 0;
        int move =0;

        for(int i =0;i<16;i++){
//            long now = System.currentTimeMillis();
//            long delta = 200;
//            while(System.currentTimeMillis()<now+delta){
//            }
            int moveOF = (i%2) +1;
            if(moveOF == 0){
                if(i==0){

                } else {
                    player1.updateOpponentsMove(move);
                }
                move = player1.getMove();
                System.out.println(move);
                int movex = move%size;
                int movey = move/size;
                frame.updateBoardAt(moveOF,movex,movey);
                frame.repaint();
                moveCounter++;
            } else {
                move = player2.getMove();
                System.out.println(move);
                int movex = move%size;
                int movey = move/size;
                frame.updateBoardAt(moveOF,movex,movey);
                frame.repaint();
                moveCounter++;
            }

        }
        System.out.println("done");
    }
}
