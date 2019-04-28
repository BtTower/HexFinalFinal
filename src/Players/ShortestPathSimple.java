package Players;

import GameMechanics.AdjacencyMatrix;
import GameMechanics.MatrixShortestPath;

import java.util.Random;


public class ShortestPathSimple implements PlayerInterface {
    private int size;
    private AdjacencyMatrix adjacencyMatrix;
    private int playerNumber;
    private Random rand;

    public ShortestPathSimple(int size, int playerNumber){
        this.size = size;
        this.playerNumber = playerNumber;
        this.adjacencyMatrix = new AdjacencyMatrix(this.size,this.playerNumber);
        rand = new Random();

    }
    public int getMove(){
        int path[];
        path = adjacencyMatrix.shortestPathBetween(size*size,size*size+1);
        int randomInt = rand.nextInt(path[0]) + 1;
        adjacencyMatrix.nodeWon(path[randomInt]);
        return path[randomInt];
    }
    public boolean getHasWon(){
        this.adjacencyMatrix.displayThisMatrix();
        System.out.println();
        return adjacencyMatrix.existsEdge(size*size,size*size+1);
    }

    public void updateOpponentsMove(int theirMove){
        adjacencyMatrix.nodeLost(theirMove);
    }
}
