package Players;

import java.util.Random;

/**
 * Created by Gleb on 10/04/2019.
 */
public class RandomPlayer implements PlayerInterface {
    private int size;
    private int [] board;
    private int freeNumber;
    private Random rand;
    private boolean hasWon;

    public RandomPlayer(int size){
        hasWon = false;
        this.size = size;
        this.freeNumber = size*size;
        board = new int [size*size];      // 0 = free, 1 = taken
        rand = new Random();
    }
    public int getMove(){
        int randomValue = rand.nextInt(size*size);
        while(board[randomValue]!=0){
            randomValue = rand.nextInt(size*size);
        }
        board[randomValue] = 1;

        return randomValue;
    }
    public boolean getHasWon(){
        return this.hasWon;
    }
    public void updateOpponentsMove(int theirMove){
        board[theirMove] = 1;
    }
}
