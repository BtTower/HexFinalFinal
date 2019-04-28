package Players;

import GameMechanics.AdjacencyMatrix;
import UI.BoardFrame;

import static java.lang.Math.sin;
import static java.lang.StrictMath.sqrt;


public class HumanPlayer implements PlayerInterface {
    private int size;
    private AdjacencyMatrix adjacencyMatrix;
    private int playerNumber;
    private BoardFrame frame;
    private int[] allowedMoves;    // too track if move is allowed
                                    // can't check adjmat as tile could be surrounded by opponents tile

    public HumanPlayer(int size , int playerNumber, BoardFrame frame){
        this.frame = frame;
        this.size = size;
        this.allowedMoves = new int[size*size];
        this.playerNumber = playerNumber;
        adjacencyMatrix = new AdjacencyMatrix(this.size,this.playerNumber);


    }


    public int getMove(){
        this.frame.setBoardTextArea("Human Turn");

        while(true) {
            int value[] = this.frame.getClicks();
            while (value[0] == 0) {       // set to 0 in Frame, when click, values change;
                long now = System.currentTimeMillis();
                long delta = 100;
                while (System.currentTimeMillis() < now + delta) {
                }
                value = this.frame.getClicks();
            }
            int theMove = convertToHex(value[0], value[1]);
            if(theMove >= 0 && theMove <size*size){
                if(allowedMoves[theMove] == 0){
                    this.allowedMoves[theMove] = 1;
                    this.adjacencyMatrix.nodeWon(theMove);
                    this.frame.setBoardTextArea("");
                    return theMove;
                }
            }
            this.frame.setBoardTextArea("Invalid Move");
        }
    }
    public boolean getHasWon(){
        return adjacencyMatrix.existsEdge(size*size,size*size+1);
    }
    public void updateOpponentsMove(int theirMove){
        this.allowedMoves[theirMove] = 1;
        adjacencyMatrix.nodeLost(theirMove);
    }

    public int convertToHex(int xPoint, int yPoint){
        int xCordOfHex, yCordOfHex;
        double xPointD, yPointD;
        xPointD = xPoint;
        yPointD = yPoint;
        int sl = 30;
        double xSide =  (0.5 * sl * sqrt(3));
        yPointD = yPointD - 20;
        xPointD = (xPointD - 75 + 0.5*xSide + yPoint /23);   //yPoint /23 for rounding errors as you go down board

        yCordOfHex = (int)((2./3*yPointD)/sl);
        xCordOfHex = (int)(sqrt(3)/3 *xPointD-(1./3)*yPointD)/sl;
        int hexNumber = yCordOfHex * size + xCordOfHex;
//        System.out.println("(" + xPointD + "," + yPointD + ")  =  (" + xCordOfHex +  "," + yCordOfHex + ")  Node no:" + hexNumber + "   by player: " + this.playerNumber);
        if(xCordOfHex>= this.size || xCordOfHex<0 || yCordOfHex>=this.size || yCordOfHex<0){
            hexNumber = -1;
        }
        return hexNumber;
    }

}
