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
        int winner = gm.startGame();
        System.out.println("winner is player:" + winner);
    }
}
