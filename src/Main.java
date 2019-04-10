import GameMechanics.GameMain;
import UI.StartFrame;

public class Main {

    public static void main(String[] args) {
        StartFrame sf = new StartFrame();
        int theSize = sf.startReturnSize();
        System.out.println(theSize);
        GameMain gm = new GameMain(theSize);
        gm.startGame();
    }
}
