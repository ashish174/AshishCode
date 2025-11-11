package algo.practice.graph.mst;

import algo.practice.graph.coregraphclasses.UndirectedGraphByMatrixArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * In Prims, we try to keep two vertices Set : Covered(mstSet) & NonCovered
 * At every step, it considers all the edges that connect the two sets(Covered & NonCovered) and picks the minimum weight edge from these edges.
 * After picking the edge, it moves the other endpoint of the edge(in NonCovered) to the set containing MST(to Covered).
 *
 * At every step, check mstSet, get all edges in adjacency list of vertices in mstSet, who other vertex is not in mstSet
 *
 *
 * Spanning Tree(ST) : a subgraph that is a tree(connected) and connects all the vertices together.
 * Minimum Spanning Tree(MST): a subgraph that is a tree(connected) with least cost and connects all the vertices together
 *
 * Appln for MST problems:
 * 1. Network design :- telephone, electrical, hydraulic, TV cable, computer, road
 * Ex: Want to connect 10 places/offices with minimum road/telephone wire cost.
 * To find the least cost of completing/connecting a network of n nodes
 * 2. Solving NP hard prblm - travelling salesman prblm -  find the shortest path that visits each point at least once.
 *
 * create a minimum spanning tree(MST) :-  a minimum spanning tree means all vertices must be connected & with minimum edges
 * A minimum spanning tree has (V – 1) edges
 * A greedy algo that maintain two set of vertices visited(MST) & non-visited
 * Each time it considers all the edges that connect the two sets, and picks the minimum weight edge
 * We create a key[] for all vertex and tries to pick the vertex with lowest key.
 * Then we update the key[] for remianing vertex in adjacency list of new chosen vextex.
 *
 *  * Solves for the Minimum Spanning Tree (MST) using Prim's algorithm:
 *  * <ul>
 *  *   <li>1. Maintain a set of vertices included in the MST and track for each vertex the minimum weight edge that connects it to the growing MST.</li>
 *  *   <li>2. Repeatedly select the non-MST vertex with the lowest connecting edge weight, and add it to the MST.</li>
 *  *   <li>3. Update the connecting edge weights for neighbors of the newly added vertex.</li>
 *  * </ul>
 *
 *
 */
public class PrimsAlgorithm {
    public static final Logger LOGGER = LoggerFactory.getLogger(PrimsAlgorithm.class);

    /**
     * Finds the Minimum Spanning Tree (MST) of an undirected graph using Prim's algorithm.
     *
     * @param undirectedGraph the input undirected graph represented as an adjacency matrix.
     */
    public static void findMSTByPrims(UndirectedGraphByMatrixArray undirectedGraph) {
        // key[v] represents the minimum weight edge that can be used to connect vertex v to the MST
        // Key values used to pick the minimum weight edge in cut - key[v] holds the minimum edge cost to connect v to MST.
        // Key value is updated with respect to reachability from vertices in MST.
        int[] key = new int[undirectedGraph.V];
        // Array to store constructed MST - parent[i] holds the parent of vertex i in MST.
        int[] parent = new int[undirectedGraph.V];
        // To represent set of vertices included in MST.
        boolean[] mstSet = new boolean[undirectedGraph.V];
        // Initialize all key values as infinite so that they get replaced by actual edge weights.
        for (int i = 0; i < key.length; i++) {
            key[i] = Integer.MAX_VALUE;
        }
        //Make key of first vertex 0, so that it will be picked first in the below for loop
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < undirectedGraph.V; count++) {
      // Pick the minimum key vertex not yet included in mstSet.
      int u = findVertexWithMinKey(undirectedGraph, key, mstSet);
            //Include this new vertex in mst set
            mstSet[u] = true;
            // Update key values and parent index for the vertices adjacent to the newly picked vertex.
            // Only consider those not yet in MST and where the new edge is smaller than previously recorded.
            for (int v = 0; v < undirectedGraph.V; v++) {
                int edgeLengthFromU = undirectedGraph.adjMatrix[u][v];
                // If there is an edge, and v is not in MST, and weight is lower than previously chosen.
                if (!mstSet[v] && edgeLengthFromU != 0 && edgeLengthFromU < key[v]) {
                    key[v] = edgeLengthFromU; // Update key for v to the new minimum
                    parent[v] = u; // Update parent for v to u
                }
            }
        }
        // Print the constructed MST (using parent array which tells MST edges).
        printMST(undirectedGraph, parent);
    }

    /**
     * Finds the vertex with the minimum key value that is not yet included in the MST.
     *
     * @param undirectedGraph the input undirected graph.
     * @param key the key array representing the minimum weight edge from the MST to each vertex.
     * @param mstSet a boolean array indicating whether a vertex is included in the MST.
     * @return the index of the vertex with the minimum key value.
     */
    public static int findVertexWithMinKey(UndirectedGraphByMatrixArray undirectedGraph, int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int vertexWitMinKey = -1;
        // Search all vertices for the minimum key value among those not in MST
        for (int v = 0; v < undirectedGraph.V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                vertexWitMinKey = v;
            }
        }
        return vertexWitMinKey;
    }

    // A utility function to print the constructed MST stored in parent[]
    // Prints the edges of the Minimum Spanning Tree (MST) stored in the parent array.
    // Each edge is printed as parent[i]---i with its weight.
    public static void printMST(UndirectedGraphByMatrixArray undirectedGraph, int[] parent) {
        LOGGER.info("Prims MST includes:-");
        for (int i = 1; i < undirectedGraph.V; i++) {
            LOGGER.info("Edge : {}----{}  Weight {}", parent[i], i, undirectedGraph.adjMatrix[parent[i]][i]);
        }
    }

    public static void main(String[] args) {
        UndirectedGraphByMatrixArray undirectedGraph = new UndirectedGraphByMatrixArray(5);
        undirectedGraph.adjMatrix = new int[][]{{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};
        findMSTByPrims(undirectedGraph);
    }
}
