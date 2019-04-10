package UI;

/**
 * Created by Gleb on 10/04/2019.
 */
import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

/**
 * Created by Gleb on 09/04/2019.
 */
public class HexBoardPanel extends JPanel {
    private int size;
    private int sl = 30;
    private int [][] board;

    public HexBoardPanel(int size){
        this.size=size;
        board = new int[size][size];
    }

    public void paintComponent(Graphics g){
        drawEdges(g);

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                drawSpecificHex(g, i,j);
            }
        }
    }

    public void drawEdges(Graphics g){
        int xDis = abs((int) (sl * sin(30)));  //x distance betwen 2 hexes /2
        g.setColor(Color.BLUE);
        g.fillRect(75 - xDis,5 ,(size - 1)*xDis*2,30);
        g.fillRect(75 +(size-2)*xDis ,(int)(  size*(1.5)*30 -10),(size - 1)*xDis*2,30);
        g.setColor(Color.RED);
        int[] redY, redX;
        redY = new int[]{50,50,(size)*(45),(size)*(45)};
        redX = new int[]{75 -xDis*2,100,(size)*xDis + 25,75 -xDis*2+(size-1)*xDis};
        g.fillPolygon(redX,redY,4);
        for(int i=0;i<4;i++){
            redY[i] -= 30;
            redX[i] += xDis * size * 2;
        }
        redX[1] = redX[0] - 50;
        redX[2] = redX[3] - 50;
        g.fillPolygon(redX,redY,4);
    }


    public void drawSpecificHex(Graphics graphics, int x, int y){
        int x0 = 75;
        int y0 = 20;    // 1st hex coords;
        int xDis = abs((int) (sl * sin(30)));  //x distance betwen 2 hexes /2
        if(board[x][y] == 1){
            graphics.setColor(Color.RED);
        } else if(board[x][y] == 2){
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.ORANGE);
        }
        graphics.fillPolygon(getHexagonEdges(x0 + y*xDis + x*2*xDis,(int)(y0 + y*(1.5)*sl)));
        graphics.setColor(Color.BLACK);
        graphics.drawPolygon(getHexagonEdges(x0 + y*xDis + x*2*xDis,(int)(y0 + y*(1.5)*sl)));


    }


    public Polygon getHexagonEdges(int x0, int y0) {     // takes top right x
        int[] xc, yc;
        int xSide = (int) (sl * sin(30));
        yc = new int[]{y0, y0 + sl, y0 + (int)(1.5*sl) , y0 + sl, y0, y0 - (int)(0.5*sl)};
        xc = new int[]{x0, x0, x0 + (xSide), x0 + 2 * xSide, x0 + 2 *  xSide, x0 + xSide};  // switch xc and yc for flat orientation
        return new Polygon(xc, yc, 6);
    }


    public void ssetSize(int newSize){
        this.size = newSize;
    }

    public void setBoard(int value, int x, int y){
        board[x][y] = value;
    }


}