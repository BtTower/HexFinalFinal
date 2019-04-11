package UI;

/**
 * Created by Gleb on 10/04/2019.
 */
/**
 * Created by Gleb on 09/04/2019.
 */
import javax.swing.*;

public class BoardFrame extends JFrame {
    private int size;
    private int clickx, clicky;

    private HexBoardPanel hexPanel;

    public BoardFrame(int size){
        this.size = size;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        hexPanel = new HexBoardPanel(size,this);
        this.getContentPane().add(hexPanel);
        this.setSize(size*100, size*80);
        this.setVisible(true);
    }

    public BoardFrame(int size, int humanPlayer){
        this.size = size;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        hexPanel = new HexBoardPanel(size,humanPlayer,this);
        this.getContentPane().add(hexPanel);
        this.setSize(size*100, size*60);
        this.setVisible(true);
        clickx = clicky = 0;
    }


    public void repaint(){
        hexPanel.repaint();
    }

    public void updateBoardAt(int value, int node){
        int nodex = node%size;
        int nodey = node/size;
        hexPanel.setBoard(value,nodex,nodey);
    }

    public void setClick(int clickx, int clicky){
        this.clickx = clickx;
        this.clicky = clicky;
    }

    public int[] getClicks(){
        int [] returnValues;
        returnValues = new int[]{clickx,clicky};
        this.clicky = this.clickx = 0;
        return returnValues;

    }

    public void setBoardTextArea(String string){
        this.hexPanel.setTextArea(string);
    }

}