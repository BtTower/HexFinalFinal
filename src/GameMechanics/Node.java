package GameMechanics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gleb on 13/04/2019.
 */
public class Node {
    private ArrayList<Node> children;
    private AdjacencyMatrix heroAm, villainAm;
    private int currentDepth;
    private int maxDepth;
    private int lastMove;
    private boolean isMaximiser;
    private boolean isRoot;
    private boolean isTerminal;
    private int size;
    private int miniMaxValue;
    private boolean allNodes;

    public Node(int lastMove, int depth, int maxDepth, boolean allNodes, AdjacencyMatrix parentHeroAm, AdjacencyMatrix parentVilAm){
        this.currentDepth = depth;
        this.maxDepth =maxDepth;
        this.lastMove = lastMove;
        this.allNodes = allNodes;
        if(this.currentDepth%2==0){
            this.isMaximiser = true;
        } else {
            this.isMaximiser = false;
        }
        if(depth==0){
            this.isRoot = true;
        } else{
            this.isRoot = false;
        }
        this.size = parentHeroAm.getSize();

        heroAm = new AdjacencyMatrix(parentHeroAm.getSize(),parentHeroAm.getPlayerNumber());
        heroAm.setAdjMat(parentHeroAm.copyMatrix());

        villainAm = new AdjacencyMatrix(parentVilAm.getSize(),parentVilAm.getPlayerNumber());
        villainAm.setAdjMat(parentVilAm.copyMatrix());

        if(!isRoot){
            if(!isMaximiser){
                heroAm.nodeWon(this.lastMove);
                villainAm.nodeLost(this.lastMove);
            } else {
                heroAm.nodeLost(this.lastMove);
                villainAm.nodeWon(this.lastMove);
            }
        }

        if(this.currentDepth == maxDepth || heroAm.existsEdge(size*size,size*size+1)
                || villainAm.existsEdge(size*size,size*size+1)){
            this.isTerminal =true;
        } else {
            this.isTerminal = false;
            this.fillChildren();
        }
    }

    private void fillChildren(){
        List freeMoves;
        if(this.allNodes) {

            if (this.isMaximiser) {
                freeMoves = heroAm.getFreeNodesList();
            } else {
                freeMoves = villainAm.getFreeNodesList();
            }
        } else {
            freeMoves = new ArrayList();
            int []tmp = heroAm.shortestPathBetween(size*size,size*size+1);
            for(int i=1;i<=tmp[0];i++){
                freeMoves.add(tmp[i]);

            }

            tmp = villainAm.shortestPathBetween(size*size,size*size+1);
            for(int i=1;i<=tmp[0];i++){
                freeMoves.add(tmp[i]);
            }

        }
        this.children = new ArrayList<Node>();
        for(int i=0;i<freeMoves.size();i++){
            this.children.add(new Node((int)freeMoves.get(i),this.currentDepth+1,this.maxDepth,this.allNodes,this.heroAm,this.villainAm ));
        }
    }


    public AdjacencyMatrix getHeroAm() {
        return heroAm;
    }

    public AdjacencyMatrix getVillainAm() {
        return villainAm;
    }

    public ArrayList getChildren() {
        return children;
    }

    public int getLastMove() {
        return lastMove;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public int getCurrentDepth() {
        return currentDepth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public boolean isMaximiser() {
        return isMaximiser;
    }

    public int getMiniMaxValue(){
        return miniMaxValue;
    }
    public void setMiniMaxValue(int setTo){
        this.miniMaxValue = setTo;
    }
}
