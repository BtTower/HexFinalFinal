import GameMechanics.AdjacecyMatrix;
import GameMechanics.GameMain;
import UI.BoardFrame;
import UI.StartFrame;

public class Main {

    public static void main(String[] args) {
        StartFrame sf = new StartFrame();
        int []theValues = sf.startReturnValues();
        GameMain gm = new GameMain(theValues[0],theValues[1],theValues[2]);
//        gm.test();
        int redWon, blueWon;
        redWon = blueWon = 0;

        int winner = gm.startGame();
        if(winner == 1){
            redWon ++;
        } else {
            blueWon++;
        }

        winner = gm.startGame();
        if(winner == 1){
            redWon ++;
        } else {
            blueWon++;
        }


        System.out.println("Redwon:" + redWon + ":" + blueWon + "bluewon");
    }
}
