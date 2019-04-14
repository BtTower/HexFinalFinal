import GameMechanics.AdjacencyMatrix;
import GameMechanics.GameMain;
import UI.BoardFrame;
import UI.StartFrame;

public class Main {


    public static void main(String[] args) {

        StartFrame sf = new StartFrame();
        int []theValues = sf.startReturnValues();

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
        System.out.println("Red " + redWins + ":" + blueWins + " Blue");















    }


}
