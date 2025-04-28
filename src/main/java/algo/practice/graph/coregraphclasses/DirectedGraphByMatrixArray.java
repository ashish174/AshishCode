package algo.practice.graph.coregraphclasses;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirectedGraphByMatrixArray {
    public static Logger logger = LoggerFactory.getLogger(DirectedGraphByMatrixArray.class);

    public int V;
    public int[][] adjMatrix;

    public DirectedGraphByMatrixArray(int v) {
        V = v;
        adjMatrix = new int[V][V];
    }

    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
    }

    static void printGraph(int[][] adjMatrixArr) {
        PrintArray.print2DSquareMatrix(adjMatrixArr);
    }

    public static void main(String[] args) {
        int V = 5;
        DirectedGraphByMatrixArray directedGraph = new DirectedGraphByMatrixArray(V);

        // Adding edges one by one
        directedGraph.addEdge(0, 1, 1);
        directedGraph.addEdge(0, 4, 1);
        directedGraph.addEdge(1, 2, 1);
        directedGraph.addEdge(1, 3, 1);
        directedGraph.addEdge(1, 4, 1);
        directedGraph.addEdge(2, 3, 1);
        directedGraph.addEdge(3, 4, 1);

        printGraph(directedGraph.adjMatrix);

    }
}
