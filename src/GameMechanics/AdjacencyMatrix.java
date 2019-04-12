package GameMechanics;

import java.util.ArrayList;

/**
 * Created by Gleb on 10/04/2019.
 */
public class AdjacencyMatrix {
    private int [][] adjMat;
    private int size;
    private int playerNumber;
    private int border1, border2;

    public AdjacencyMatrix(int size, int playerNumber){
        this.size = size;
        this.playerNumber = playerNumber;
        this.initMatrix();
    }



    private void initMatrix(){
        adjMat = new int[size*size + 2][ size*size + 2];
        int i = 0;
        addEdge(0,1);    // top left hex
        addEdge(0,size);
        i++;
        for(;i<size-1;i++){          // top row
            addEdge(i,i+1);
            addEdge(i,i+size);
        }
        addEdge(size-1,size+size-1);    // top right hex
        i++;
        for(;i<(size*(size-1));i++){            // rest except bottom row
            if((i+1)%size == 0){
                addEdge(i,i+size);      // right border
            } else{
                addEdge(i,i+1);
                addEdge(i,i+size);
                addEdge(i,i-size+1);
            }
        }
        for(;i<(size*size)-1;i++){             //bottom row except bottom right
            addEdge(i,i+1);
            addEdge(i,i-size+1);
        }
        this.border1 = size*size;
        this.border2 = border1 + 1;
        if(this.playerNumber == 1){                    // special nodes for player 0
            for(i =0;i<size;i++){
                addEdge(border1,i*size);
                addEdge(border2,i*size+size-1 );
            }
        } else {
            for(i=0;i<size;i++){              // special nodes for player 1
                addEdge(border1,i);
                addEdge(border2,size*(size-1)+i);
            }
        }
//        for(i=0;i<size*size+2;i++){    //nodes connect to self
//            adjMat[i][i] = 1;
//        }
    }

    public void displayThisMatrix(){
        this.displayMatrix(this.adjMat);
    }

    public void displayMatrix(int [][] theMatrix){
        for(int i=0;i<size*size + 2;i++){
            for(int j=0;j<size*size + 2;j++){
                System.out.print(theMatrix[i][j] + ",");
            }
            System.out.println();
        }
    }

    public void nodeWon(int node){
        ArrayList hasConnections = new ArrayList<Integer>();
        for(int i=0;i<size*size+2;i++){
            if(adjMat[i][node]>0){
                hasConnections.add(i);
                removeEdge(i,node);
            }
        }
        for(int i=0;i<hasConnections.size();i++){
            for(int j=i+1;j<hasConnections.size();j++){
                addEdge((Integer)hasConnections.get(i),(Integer)hasConnections.get(j));
            }
        }
    }

    public void nodeLost(int node){
        for(int i=0;i<size*size+2;i++){
            removeEdge(i,node);
        }
    }

    public int edgeWeight(int node1, int node2){
        return adjMat[node1][node2];
    }

    public void addEdge(int node1, int node2){
        adjMat[node1][node2] = adjMat[node2][node1] = 1;
    }

    public void removeEdge(int node1, int node2){
        adjMat[node1][node2] = adjMat[node2][node1] = 0;
    }

    public int[] shortestPathBetween(int node1, int node2){
        int [][] adjMatCopy = new int[size*size+2][size*size+2];
        for(int i=0;i<size*size+2;i++){
            for(int j=0;j<size*size+2;j++){
                adjMatCopy[i][j] = adjMat[i][j];
            }
        }
        MatrixShortestPath mp = new MatrixShortestPath(adjMatCopy,node1,node2,this.size);
        return mp.getShortestPath();
    }

    public boolean existsEdge(int node1, int node2){
        if(adjMat[node1][node2] > 0){
            return true;
        }
        return false;
    }

    public int pathsOfLengthBetween(int length,int node1,int node2){
        int[][] returnValue = matToPowerOf(length);
        return returnValue[node1][node2];
    }

    public int [][] matToPowerOf(int thePower){
        int [][] result = new int[size*size+2][size*size+2];
        int [][] temp = new int[size*size+2][size*size+2];
        for(int i=0;i<size*size+2;i++){
            for(int j=0;j<size*size+2;j++){
                result[i][j] = adjMat[i][j];
          }
        }
        for(int counter1=1;counter1<thePower;counter1++){
            System.out.println("here");

            for(int i=0;i<size*size+2;i++){
                for(int j=0;j<size*size+2;j++){
                    temp[i][j] = result[i][j];
                }
            }
            for(int j=0;j<size*size+2;j++){
                for(int k=0;k<size*size+2;k++){
                    result[j][k] = 0;
                    for(int l=0;l<size*size+2;l++){
                        result[j][k] +=  temp[j][l] * adjMat[l][k] ;
                    }
                }
            }

        }
        return result;
    }
}
