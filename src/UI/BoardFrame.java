package UI;

/**
 * Created by Gleb on 10/04/2019.
 */
/**
 * Created by Gleb on 09/04/2019.
 */
import javax.swing.*;

public class BoardFrame extends JFrame {
    private int size ;

    private HexBoardPanel hexPanel;

    public BoardFrame(int size){
        this.size = size;
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        hexPanel = new HexBoardPanel(size);
        this.getContentPane().add(hexPanel);
        this.setSize(size*100, size*80);
        this.setVisible(true);
    }

    public void sssetSize(int newSize){
        this.hexPanel.ssetSize(newSize);
    }

    public void repaint(){
        hexPanel.repaint();
    }

    public void updateBoardAt(int value, int node){
        int nodex = node%size;
        int nodey = node/size;
        hexPanel.setBoard(value,nodex,nodey);
    }

}