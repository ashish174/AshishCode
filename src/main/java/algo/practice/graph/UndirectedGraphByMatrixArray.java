package algo.practice.graph;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UndirectedGraphByMatrixArray {
    public static Logger logger = LoggerFactory.getLogger(UndirectedGraph.class);

    public int V;
    public int[][] adjMatrix;

    public UndirectedGraphByMatrixArray(int v) {
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
        UndirectedGraphByMatrixArray undirectedGraph = new UndirectedGraphByMatrixArray(V);

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
