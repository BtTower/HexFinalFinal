package Players;

import GameMechanics.AdjacencyMatrix;
import GameMechanics.MatrixShortestPath;

import java.util.Random;

/**
 * Created by Gleb on 11/04/2019.
 */
public class ShortestPathBlocking implements PlayerInterface {
    private int size;
    private AdjacencyMatrix ourAdjacencyMatrix , theirAdjacencyMatrix;
    private int playerNumber;
    private int theirNumber;
    private Random rand;

    public ShortestPathBlocking(int size, int playerNumber){
        this.size = size;
        this.playerNumber = playerNumber;
        switch (this.playerNumber){
            case 1 :
                this.theirNumber = 2;
                break;
            default:
                this.theirNumber = 1;
        }
        this.ourAdjacencyMatrix = new AdjacencyMatrix(this.size,this.playerNumber);
        this.theirAdjacencyMatrix = new AdjacencyMatrix(this.size,this.theirNumber);
        rand = new Random();

    }
    public int getMove(){
        int ourPath[];
        int theirPath[];
        ourPath = ourAdjacencyMatrix.shortestPathBetween(size*size,size*size+1);
        theirPath = theirAdjacencyMatrix.shortestPathBetween(size*size,size*size+1);

        int shared = findIfShareValue(ourPath,theirPath);
        if(shared!= -1){
            theirAdjacencyMatrix.nodeLost(shared);
            ourAdjacencyMatrix.nodeWon(shared);
            return shared;
        }
        if(ourPath[0]<= theirPath[0]){
            int randomInt = rand.nextInt(ourPath[0]) + 1;
            theirAdjacencyMatrix.nodeLost(ourPath[randomInt]);
            ourAdjacencyMatrix.nodeWon(ourPath[randomInt]);
            return ourPath[randomInt];
        } else {
            int randomInt = rand.nextInt(theirPath[0]) + 1;
            theirAdjacencyMatrix.nodeLost(theirPath[randomInt]);
            ourAdjacencyMatrix.nodeWon(theirPath[randomInt]);
            return theirPath[randomInt];
        }
    }

    public int findIfShareValue(int []array1, int[]array2){
        for(int i = 1;i<array1.length;i++){
            for(int j=1;j<array2.length;j++){
                if(array1[i]==array2[j]){
                    return array1[i];
                }
            }
        }
        return -1;
    }


    public boolean getHasWon(){

        return ourAdjacencyMatrix.existsEdge(size*size,size*size+1);
    }

    public void updateOpponentsMove(int theirMove){
        theirAdjacencyMatrix.nodeWon(theirMove);
        ourAdjacencyMatrix.nodeLost(theirMove);
    }
}
