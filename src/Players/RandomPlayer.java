package Players;

import GameMechanics.AdjacecyMatrix;

import java.util.Random;

/**
 * Created by Gleb on 10/04/2019.
 */
public class RandomPlayer implements PlayerInterface {
    private int size;
    private int playerNumber;
    private int [] board;
    private Random rand;
    private AdjacecyMatrix adjacecyMatrix;

    public RandomPlayer(int size , int playerNumber){
        this.size = size;
        this.playerNumber = playerNumber;
        adjacecyMatrix = new AdjacecyMatrix(this.size,this.playerNumber);

        board = new int [size*size];      // 0 = free, 1 = taken
        rand = new Random();
    }


    public int getMove(){
        int randomValue = rand.nextInt(size*size);
        while(board[randomValue]!=0){
            randomValue = rand.nextInt(size*size);
        }
        board[randomValue] = 1;
        this.adjacecyMatrix.nodeWon(randomValue);
        return randomValue;
    }

    public boolean getHasWon(){
        return adjacecyMatrix.existsEdge(size*size,size*size+1);
    }
    public void updateOpponentsMove(int theirMove){
        adjacecyMatrix.nodeLost(theirMove);
        board[theirMove] = 1;
    }
}
