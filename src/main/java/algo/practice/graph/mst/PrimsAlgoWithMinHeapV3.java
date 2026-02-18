package algo.practice.graph.mst;
import algo.practice.graph.coregraphclasses.UndirectedGraphByMatrixArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.Comparator;

public class PrimsAlgoWithMinHeapV3 {
    public static final Logger LOGGER = LoggerFactory.getLogger(PrimsAlgorithm.class);

    /**
     * Finds the Minimum Spanning Tree (MST) of an undirected graph using Prim's algorithm,
     * optimized with a min-heap (priority queue).
     *
     * @param undirectedGraph the input undirected graph represented as an adjacency matrix.
     */
    public static void findMSTByPrims(UndirectedGraphByMatrixArray undirectedGraph) {
        int V = undirectedGraph.V;
        int[] key = new int[V];           // Minimum weight to connect to MST
        int[] parent = new int[V];        // Store MST structure
        boolean[] inMST = new boolean[V]; // True if vertex is in MST

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;         // Start from vertex 0
        parent[0] = -1;

        // Min Heap: (key, vertex)
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(pair -> pair.key));
        //insert
        pq.offer(new Pair(0, 0));

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int u = current.vertex;

            if (inMST[u]) continue; // Already included
            inMST[u] = true;

            // Scan all vertices adjacent to u
            for (int v = 0; v < V; v++) {
                int weight = undirectedGraph.adjMatrix[u][v];
                // Edge exists, v not in MST, and edge is smaller than previously recorded
                if (weight > 0 && !inMST[v] && weight < key[v]) {
                    key[v] = weight;
                    parent[v] = u;
                    //insert
                    pq.offer(new Pair(key[v], v)); // Add/Update v in heap
                }
            }
        }

        printMST(undirectedGraph, parent, key);
    }

    private static class Pair {
        int key, vertex;
        Pair(int key, int vertex) {
            this.key = key;
            this.vertex = vertex;
        }
    }

    // Prints the edges in the MST as found by Prim's Algorithm
    public static void printMST(UndirectedGraphByMatrixArray graph, int[] parent, int[] key) {
        LOGGER.info("Prim's MST includes:");
        for (int i = 1; i < graph.V; i++) {
            LOGGER.info("Edge: {} --- {}  Weight: {}", parent[i], i, graph.adjMatrix[parent[i]][i]);
        }
    }

    public static void main(String[] args) {
        UndirectedGraphByMatrixArray graph = new UndirectedGraphByMatrixArray(5);
        graph.adjMatrix = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};
        findMSTByPrims(graph);
    }

}
