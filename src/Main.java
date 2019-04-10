import GameMechanics.AdjacecyMatrix;
import GameMechanics.GameMain;
import UI.BoardFrame;
import UI.StartFrame;

public class Main {

    public static void main(String[] args) {
        StartFrame sf = new StartFrame();
        int theSize = sf.startReturnSize();
        GameMain gm = new GameMain(theSize);
        gm.startGame();


    }
}
