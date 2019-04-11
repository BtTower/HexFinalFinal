package Players;

import GameMechanics.AdjacencyMatrix;
import UI.BoardFrame;

import static java.lang.Math.sin;
import static java.lang.StrictMath.sqrt;

/**
 * Created by Gleb on 11/04/2019.
 */
public class HumanPlayer implements PlayerInterface {
    private int size;
    private AdjacencyMatrix adjacencyMatrix;
    private int playerNumber;
    private BoardFrame frame;

    public HumanPlayer(int size , int playerNumber, BoardFrame frame){
        this.frame = frame;
        this.size = size;
        this.playerNumber = playerNumber;
        adjacencyMatrix = new AdjacencyMatrix(this.size,this.playerNumber);


    }


    public int getMove(){
        int value[] = this.frame.getClicks();
        while(value[0]==0){
            System.out.print(" ");
            long now = System.currentTimeMillis();
            long delta = 500;
            while(System.currentTimeMillis()<now+delta){
            }
            value = this.frame.getClicks();
        }
        int theMove = convertToHex(value[0],value[1]);
        this.adjacencyMatrix.nodeWon(theMove);
        return convertToHex(value[0],value[1]);
    }
    public boolean getHasWon(){
        return adjacencyMatrix.existsEdge(size*size,size*size+1);
    }
    public void updateOpponentsMove(int theirMove){
        adjacencyMatrix.nodeLost(theirMove);
    }

    public int convertToHex(int xPoint, int yPoint){
        int xCordOfHex, yCordOfHex;
        int sl = 30;
        int xSide = (int) (sl * sin(30));
        xPoint = xPoint - 75 - xSide;
        yPoint -= 20;
        yCordOfHex = (int)((2./3*yPoint)/sl);
        xCordOfHex = (int)(sqrt(3)/3 *xPoint-1./3*yPoint)/sl;
        int hexNumber = yCordOfHex * size + xCordOfHex;
        return hexNumber;
    }

}
