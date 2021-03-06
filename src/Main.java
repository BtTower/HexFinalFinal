import GameMechanics.GameMain;
import UI.StartFrame;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        StartFrame sf = new StartFrame();
        int[] theValues = sf.startReturnValues();

        for (int i = 0; i < 12; i++) {
            System.out.print(theValues[i] + ",");
        }

        System.out.println();
        System.out.println("started " + LocalDateTime.now());

        int redWins, blueWins;
        redWins = blueWins = 0;
        for (int i = 0; i < theValues[10]; i++) {
            long now = System.currentTimeMillis();
            GameMain gm = new GameMain(theValues[0], theValues[1], theValues[2], theValues);
            if (gm.startGame() == 1) {
                System.out.println("Red wins run " + (i + 1));
                redWins++;
            } else {
                System.out.println("Blue wins run " + (i + 1));
                blueWins++;
            }
            System.out.println("Took " + (System.currentTimeMillis() - now));
        }
        System.out.println("Red " + redWins + ":" + blueWins + " Blue" + "player " + theValues[1] + " " + theValues[2]);
    }
}



























