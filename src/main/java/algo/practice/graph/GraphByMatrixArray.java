package algo.practice.graph;

import java.util.ArrayList;
import java.util.List;
import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphByMatrixArray {
    public static Logger logger = LoggerFactory.getLogger(Graph.class);

    private int V;
    private int[][] adjMatrix;

    public GraphByMatrixArray(int v) {
        V = v;
        adjMatrix = new int[V][V];
    }

    public void addEdge(int u, int v, int weight) {
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight;
    }

    static void printGraph(int[][] adjMatrixArr) {
        PrintArray.print2DSquareMatrix(adjMatrixArr);
    }

    public static void main(String[] args) {
        int V = 5;
        GraphByMatrixArray undirectedGraph = new GraphByMatrixArray(V);

        // Adding edges one by one
        undirectedGraph.addEdge(0, 1, 1);
        undirectedGraph.addEdge(0, 4, 1);
        undirectedGraph.addEdge(1, 2, 1);
        undirectedGraph.addEdge(1, 3, 1);
        undirectedGraph.addEdge(1, 4, 1);
        undirectedGraph.addEdge(2, 3, 1);
        undirectedGraph.addEdge(3, 4, 1);

        printGraph(undirectedGraph.adjMatrix);

    }
}
