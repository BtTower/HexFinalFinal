package Players;

import GameMechanics.AdjacencyMatrix;
import GameMechanics.RandomFill;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Gleb on 10/04/2019.
 */
public class SimpleRandomFillPlayer implements PlayerInterface {
    private int size;
    private int playerNumber;
    private int fillCount;


    private AdjacencyMatrix adjacencyMatrix;

    public SimpleRandomFillPlayer(int size , int playerNumber,int fillCount) {
        this.fillCount = fillCount;
        this.size = size;
        this.playerNumber = playerNumber;
        adjacencyMatrix = new AdjacencyMatrix(this.size, this.playerNumber);
    }



    public int getMove(){
        long time = System.currentTimeMillis();
        RandomFill rf = new RandomFill(this.adjacencyMatrix,this.fillCount);
        int bestMove = rf.bestMove(this.adjacencyMatrix.getFreeNodesList());

        time = System.currentTimeMillis() - time;
        System.out.println("Took" + time + "ms");
        this.adjacencyMatrix.nodeWon(bestMove);
        return bestMove;
    }

    public boolean getHasWon(){
        return adjacencyMatrix.existsEdge(size*size,size*size+1);
    }
    public void updateOpponentsMove(int theirMove){
        adjacencyMatrix.nodeLost(theirMove);
    }
}
