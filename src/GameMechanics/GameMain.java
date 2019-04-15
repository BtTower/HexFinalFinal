package GameMechanics;

import Players.*;
import UI.BoardFrame;

/**
 * Created by Gleb on 10/04/2019.
 */
public class GameMain {
    private int size;
    private PlayerInterface player1;    // player 1 = red
    private PlayerInterface player2;    // player 2 = blue
    private int player1Choice,player2Choice;
    private int [] inputArgs;
    private boolean showBoard;
    private int moveDelay;


    public GameMain(int size,int player1Choice, int player2Choice, int[] args){

        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
        this.size = size;
        this.inputArgs = args;
        this.moveDelay = args[11];
        if(args[9] == 1){
            this.showBoard = true;
        } else{
            this.showBoard =false;
        }
    }


    public PlayerInterface selectPlayers(int playerChoice, int playerNumber, BoardFrame frame){
        switch (playerChoice){            // from StartPanel
            case 0:                         // 0 = RandomPlayer
                return new EvaluationPlayers(size,playerNumber,inputArgs[4+playerNumber],inputArgs[6+playerNumber],0,true);
            case 1:
                return new EvaluationPlayers(size,playerNumber,inputArgs[4+playerNumber],inputArgs[6+playerNumber],inputArgs[2+playerNumber]);
            case 2:
                return new EvaluationPlayers(size,playerNumber,inputArgs[4+playerNumber],inputArgs[6+playerNumber],0);
            case 3:
                return new HumanPlayer(size,playerNumber,frame);
            case 4:
                return new ShortestPathBlocking(size,playerNumber);
            case 5:
                return new ShortestPathSimple(size,playerNumber);
            case 6:
                return new SimpleRandomFillPlayer(size,playerNumber,inputArgs[2+playerNumber]);
            case 7:
                return new FillWithShortestPath(size,playerNumber,inputArgs[2+playerNumber]);
            case 8:
                return new RandomPlayer(size,playerNumber);
            default:
                System.out.println("error in player selector");
                return null;
        }

    }


    public int startGame(){
        BoardFrame frame;
        if(player2Choice == 3 || player1Choice == 3){
            frame = new BoardFrame(size,1);    // with human player says human player on top
        } else {
            frame = new BoardFrame(size,this.showBoard);
        }
        player1 = selectPlayers(player1Choice,1,frame);
        player2 = selectPlayers(player2Choice,2,frame);
        int moveCounter = 0;
        while(!player1.getHasWon() && !player2.getHasWon()){
            long now = System.currentTimeMillis();
            if(moveDelay>0){
                long delta = moveDelay;
                while(System.currentTimeMillis()<now+delta){
                }
            }
            int move;
            if(moveCounter%2 == 0){
                move = player1.getMove();
                player2.updateOpponentsMove(move);
            } else {
                move = player2.getMove();
                player1.updateOpponentsMove(move);
            }
            if(frame.colourAt(move)!=0){
                System.out.println("INVALID MOVE " + move);
                return -1;
            }

            frame.updateBoardAt((moveCounter%2)+1,move);
            frame.repaint();
            moveCounter ++;
//            System.out.println("move took " + (System.currentTimeMillis()-now-this.moveDelay) + "ms BOARD SIZE" + this.size );
//
//            return (int)(System.currentTimeMillis()-now-this.moveDelay);

        }
        if(player2Choice == 3 || player1Choice ==3) {
            String string = "Player " + ((moveCounter+1)%2 + 1) + " won";
            frame.setBoardTextArea(string);
        }
        return ((moveCounter+1)%2 + 1);
    }


}
