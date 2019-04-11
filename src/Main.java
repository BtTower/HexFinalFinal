import GameMechanics.GameMain;
import UI.StartFrame;

public class Main {
    public int test1212;

    public static void main(String[] args) {
        StartFrame sf = new StartFrame();
        int []theValues = sf.startReturnValues();
        GameMain gm = new GameMain(theValues[0],theValues[1],theValues[2]);
        gm.startGame();






    }
}
