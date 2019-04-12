package GameMechanics;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Gleb on 11/04/2019.
 */
public class MatrixShortestPath {
    private int [][] adjMat;
    private int node1,node2;
    private int size;
    private int [] visitedDepth;
    private int [] previous;
    private Random rand;

    public MatrixShortestPath(int [][] adjMat,int node1,int node2,int size){
        this.adjMat = adjMat;
        this.node1 = node1;
        this.node2 = node2;
        this.size = size;
        this.rand = new Random();
    }

    public int[] getShortestPath(){            // finds a random shortest path
        visitedDepth = new int[size*size+2];
        previous = new int[size*size+2];
        Arrays.fill(visitedDepth,-1);
        Arrays.fill(previous,-1);
        int currentNode = node1;
        int currentDepth = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(node1);
        while(visitedDepth[node2] == -1){
            currentNode = queue.poll();
            currentDepth = visitedDepth[currentNode] + 1;
            int arrayOfNodes[] = new int[size*size+2];
            int arrayLength = 0;
            for(int i=0;i<(size*size+2);i++){

                if(existsEdge(currentNode,i) && visitedDepth[i] ==-1){
//                    queue.add(i);
                    arrayOfNodes[arrayLength] = i;
                    arrayLength ++;
                    previous[i] = currentNode;
                    visitedDepth[i] = currentDepth;
                }

            }
            int arrayToShuffle[] = new int[arrayLength];    // to make it choose random shortest path. non determinism isnice
            System.arraycopy( arrayOfNodes, 0, arrayToShuffle, 0, arrayLength );
            shuffleArray(arrayToShuffle);
            for(int j=0;j<arrayLength;j++){
                queue.add(arrayToShuffle[j]);
            }
        }
        int [] returnValue = new int[currentDepth+1];
        returnValue[0] = currentDepth;
//        System.out.println("depth: " + currentDepth);
        currentNode = node2;
        for(int i=1;i<=currentDepth;i++){
//            System.out.println("array[" + i + "] has node " + previous[currentNode]);
            returnValue[i] = previous[currentNode];
            currentNode = previous[currentNode];
        }
        return returnValue;
    }


    public void shuffleArray(int[] array) {   // Fisher-Yates Shuffle
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            int iVal = array[index];
            array[index] = array[i];
            array[i] = iVal;
        }
    }


    public boolean existsEdge(int node1, int node2){
        if(adjMat[node1][node2] > 0){
            return true;
        }
        return false;
    }
}
