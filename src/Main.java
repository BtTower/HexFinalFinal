import GameMechanics.AdjacencyMatrix;
import GameMechanics.GameMain;
import UI.BoardFrame;
import UI.StartFrame;

public class Main {


    public static void main(String[] args) {

        StartFrame sf = new StartFrame();
        int []theValues = sf.startReturnValues();
//        for(int i=0;i<12;i++){
//            System.out.print(theValues[i] + ",");
//        }
//        System.out.println();

        int redWins, blueWins;
        redWins = blueWins = 0;
        for(int i=0;i<theValues[10];i++){
            GameMain gm = new GameMain(theValues[0], theValues[1], theValues[2], theValues);
            if(gm.startGame()==1){
                System.out.println("Red wins run " + (i+1));
                redWins++;
            } else {
                System.out.println("Blue wins run " + (i+1) );
                blueWins ++;
            }
        }
        System.out.println("Red " + redWins + ":" + blueWins + " Blue" + "player " + theValues[1] + " " + theValues[2]);

//        for(int i =7;i<15;i+=2){
//            int []theValues = {i,6,0,500,500,4,1,1,1,0,1,0};
//            GameMain gm = new GameMain(theValues[0], theValues[1], theValues[2], theValues);
//            gm.startGame();
//        }










//        BoardFrame bf = new BoardFrame(4,true);
//        bf.updateBoardAt(1,5);
//        bf.updateBoardAt(1,6);
//        bf.updateBoardAt(2,13);
//
//        AdjacencyMatrix am = new AdjacencyMatrix(4,1);
//        am.nodeWon(5);
//        am.nodeWon(6);
//        am.nodeLost(13);
//        am.displayThisMatrix();















    }


}
