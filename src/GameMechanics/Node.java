package GameMechanics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gleb on 12/04/2019.
 */
public class Node {
    private int maxDepth;
    private int depth;
    private AdjacencyMatrix adjacencyMatrixHero,adjacencyMatrixVillain;
    private List<Node> children;   // hero is current player at level
    private Node parent;
    private boolean isTerminal;
    private int size;
    private int move;
    private List freeNodes;




    public Node(int move, Node parent, int depth, int maxDepth,
                AdjacencyMatrix parentAdjMatHero, AdjacencyMatrix parentAdjMatVillain){
        this.maxDepth = maxDepth;
        this.size = parentAdjMatHero.getSize();
        this.move = move;
        this.children = new ArrayList<Node>();

        this.depth = depth;

        int [][]matTemp = parentAdjMatHero.copyMatrix();
        this.adjacencyMatrixVillain = new AdjacencyMatrix(size,parentAdjMatHero.getPlayerNumber());
        this.adjacencyMatrixVillain.setAdjMat(matTemp);


        matTemp = parentAdjMatVillain.copyMatrix();
        this.adjacencyMatrixHero = new AdjacencyMatrix(size,parentAdjMatVillain.getPlayerNumber());
        this.adjacencyMatrixHero.setAdjMat(matTemp);

        if(this.depth != 0){
            this.parent = parent;
            adjacencyMatrixVillain.nodeWon(move);
            adjacencyMatrixHero.nodeLost(move);
        }

        this.freeNodes = new ArrayList<Integer>();
        matTemp = adjacencyMatrixHero.copyMatrix();
        for(int i=0;i<size*size;i++){
            for(int j=0;j<size*size;j++){
                if(matTemp[i][j]>0){
                    this.freeNodes.add(i);
                    break;
                }
            }
        }
        if(depth<maxDepth && !adjacencyMatrixHero.existsEdge(size*size,size*size+1)
                && !adjacencyMatrixVillain.existsEdge(size*size,size*size+1)){
            this.isTerminal = false;
            this.makeChildren();
        } else{
            Node node = this;
            System.out.print("\ndepth " + this.depth + " moves ");
            for(int i=0;i<this.depth;i++){
                System.out.print(node.getMove() + " - ");
                node = node.getParent();
            }
            this.isTerminal = true;
        }
    }

    private void makeChildren(){
        for(int i=0;i<freeNodes.size();i++){
            this.children.add(new Node((Integer)freeNodes.get(i),this,this.depth+1,this.maxDepth, this.adjacencyMatrixHero,this.adjacencyMatrixVillain));
        }
    }

    public void printChildren(){
        for(int i=0;i<children.size();i++){
            System.out.println(children.get(i).getMove());
        }
        children.get(0).adjacencyMatrixHero.displayThisMatrix();
        System.out.println();
        children.get(0).adjacencyMatrixVillain.displayThisMatrix();
    }

    public int getMove(){
        return this.move;
    }

    public AdjacencyMatrix getAdjacencyMatrixHero(){
        return this.adjacencyMatrixHero;
    }

    public AdjacencyMatrix getAdjacencyMatrixVillain(){
        return this.adjacencyMatrixVillain;
    }

    public Node getParent(){
        return this.parent;
    }



}
