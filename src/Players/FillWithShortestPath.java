package Players;

import GameMechanics.AdjacencyMatrix;
import GameMechanics.MatrixShortestPath;
import GameMechanics.RandomFill;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gleb on 11/04/2019.
 */
public class FillWithShortestPath implements PlayerInterface {
    private int size;
    private AdjacencyMatrix ourAdjacencyMatrix , theirAdjacencyMatrix;
    private int playerNumber;
    private int theirNumber;
    private int fillCount;

    public FillWithShortestPath(int size, int playerNumber, int fillCount){
        this.size = size;
        this.fillCount =fillCount;
        this.playerNumber = playerNumber;
        switch (this.playerNumber){
            case (1) :
                this.theirNumber = 2;
            default:
                this.theirNumber = 1;
        }
        this.ourAdjacencyMatrix = new AdjacencyMatrix(this.size,this.playerNumber);
        this.theirAdjacencyMatrix = new AdjacencyMatrix(this.size,this.theirNumber);

    }
    public int getMove(){
        int ourPath[];
        int theirPath[];
        ourPath = ourAdjacencyMatrix.shortestPathBetween(size*size,size*size+1);
        theirPath = theirAdjacencyMatrix.shortestPathBetween(size*size,size*size+1);
//        int arrayToConscider[] = new int [ourPath[0]+theirPath[0]];
//        System.arraycopy(ourPath,1,arrayToConscider,0,ourPath[0]);
//        System.arraycopy(theirPath,1,arrayToConscider,ourPath[0],theirPath[0]);
        ArrayList arrayToFill = new ArrayList<Integer>();{
            for(int i =0;i<ourPath[0];i++){
                arrayToFill.add(ourPath[i+1]);
            }
            for(int i =0;i<theirPath[0];i++){
                arrayToFill.add(theirPath[i+1]);
            }
        }

        RandomFill rf = new RandomFill(this.ourAdjacencyMatrix,this.fillCount);
        int bestMove = rf.bestMove(arrayToFill);
        this.ourAdjacencyMatrix.nodeWon(bestMove);
        this.theirAdjacencyMatrix.nodeWon(bestMove);
        return bestMove;

    }




    public boolean getHasWon(){

        return ourAdjacencyMatrix.existsEdge(size*size,size*size+1);
    }

    public void updateOpponentsMove(int theirMove){
        theirAdjacencyMatrix.nodeWon(theirMove);
        ourAdjacencyMatrix.nodeLost(theirMove);
    }
}
