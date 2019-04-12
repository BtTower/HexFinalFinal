import GameMechanics.AdjacencyMatrix;
import GameMechanics.GameMain;
import GameMechanics.Node;
import UI.StartFrame;

import java.util.Random;

public class Main {


    public static void main(String[] args) {

        StartFrame sf = new StartFrame();
        int []theValues = sf.startReturnValues();

        GameMain gm = new GameMain(theValues[0], theValues[1], theValues[2], theValues);
        gm.startGame();

    }


}
