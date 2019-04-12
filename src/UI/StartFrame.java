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
        int[]returnArray;
        returnArray = new int[]{this.size,this.player1Choice,this.player2Choice,this.player1FillArg,
        this.player2FillArg};
        return returnArray;
    }

    public void setHasStartedSize(boolean b, int theSize, int player1Choice, int player2Choice, int[]args){
        this.size = theSize;
        this.player1Choice = player1Choice;
        this.player2Choice = player2Choice;
        this.player1FillArg = args[0];
        this.player2FillArg = args[1];

        this.hasStarted = b;
    }

    public void closeFrame(){
        this.setVisible(false);
    }


}