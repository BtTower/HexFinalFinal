package UI;

import javax.swing.*;

/**
 * Created by Gleb on 10/04/2019.
 */
public class StartFrame extends JFrame {
    private JPanel startPanel;
    private boolean hasStarted;
    private int size;

    public StartFrame() {
        this.hasStarted = false;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        startPanel = new StartPanel(this);
        this.getContentPane().add(startPanel);
        this.pack();
        this.setVisible(true);
    }

    public int startReturnSize(){
        while(true){
            System.out.print(' ');  // loop never terminates without this
            long now = System.currentTimeMillis();
            long delta = 500;
            while(System.currentTimeMillis()<now+delta){
            }
            if(this.hasStarted == true){
                break;
            }
        }
        return this.size;
    }

    public void setHasStartedSize(boolean b, int theSize){
        this.size = theSize;
        this.hasStarted = b;
    }

    public void closeFrame(){
        this.setVisible(false);
    }


}