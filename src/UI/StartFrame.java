package UI;

import javax.swing.*;

/**
 * Created by Gleb on 10/04/2019.
 */
public class StartFrame extends JFrame {
    private JPanel startPanel;
    private boolean hasStarted;
    private int size;
    private int player1Choice, player2Choice;
    private int player1FillArg, player2FillArg;
    private int player1Depth, player2Depth;
    private int[] argsArray;

    public StartFrame() {
        this.hasStarted = false;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startPanel = new StartPanel(this);
        this.getContentPane().add(startPanel);
        this.pack();
        this.setVisible(true);
    }

    public int[] startReturnValues(){
        while(true){
            System.out.print(' ');  // loop never terminates without this
            long now = System.currentTimeMillis();
            long delta = 500;
            while(System.currentTimeMillis()<now+delta){
            }
            if(this.hasStarted == true){
                System.out.println();     // above prints ^^^
                break;
            }
        }

        return this.argsArray;
    }

    public void setHasStartedSize(boolean b, int theSize, int player1Choice, int player2Choice, int[]args){


        this.argsArray = new int[12];
        argsArray[0] = theSize;
        argsArray[1] = player1Choice;
        argsArray[2] = player2Choice;
        System.arraycopy(args,0,argsArray,3,9);

        this.hasStarted = b;
    }

    public void closeFrame(){
        this.setVisible(false);
    }


}