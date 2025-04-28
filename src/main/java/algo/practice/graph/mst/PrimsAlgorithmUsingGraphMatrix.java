package algo.practice.graph.mst;

import algo.practice.graph.coregraphclasses.UndirectedGraphByMatrixArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Appln for MST problems:
 * 1. Network design :- telephone, electrical, hydraulic, TV cable, computer, road
 * Ex: Want to connect 10 places/offices with minimum road/telephone wire cost.
 * 2. Solving NP hard prblm - travelling salesman prblm -  find the shortest path that visits each point at least once.
 *
 * Indirect applications.
 * – max bottleneck paths
 * – LDPC codes for error correction
 * – image registration with Renyi entropy
 * – learning salient features for real-time face verification
 * – reducing data storage in sequencing amino acids in a protein
 * – model locality of particle interactions in turbulent fluid flows
 * – autoconfig protocol for Ethernet bridging to avoid cycles in a network
 *
 *
 * create a minimum spanning tree(MST) :-  a spanning tree means all vertices must be connected & with minimum edges
 * A greedy algo that maintain two set of vertices visited(MST) & non-visited
 * Each time it considers all the edges that connect the two sets, and picks the minimum weight edge
 * We create a key[] for all vertex and tries to pick the vertex with lowest key.
 * Then we update the key[] for remianing vertex in adjacency list of new chosen vextex.
 *
 *
 */
public class PrimsAlgorithmUsingGraphMatrix {
    public static final Logger LOGGER = LoggerFactory.getLogger(PrimsAlgorithmUsingGraphMatrix.class);

    public static void findMSTByPrims(UndirectedGraphByMatrixArray undirectedGraph) {
        int[] key = new int[undirectedGraph.V];
        int[] parent = new int[undirectedGraph.V];
        boolean[] mstSet = new boolean[undirectedGraph.V];
        //Initialize key for all vertex to infinity/Integer.MAX_VALUE
        for (int i = 0; i < key.length; i++) {
            key[i] = Integer.MAX_VALUE;
        }
        //Make key of first vertex 0, so that it will be picked first in the below for loop
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < undirectedGraph.V; count++) {
            int u = findVertexWithMinKey(undirectedGraph, key, mstSet);
            //Include this new vertex in mst set
            mstSet[u] = true;
            //update key of neighbours of the newly picked vertex & the parent value
            for (int v = 0; v < undirectedGraph.V; v++) {
                int edgeLengthFromU = undirectedGraph.adjMatrix[u][v];
                if (!mstSet[v] && edgeLengthFromU != 0 && edgeLengthFromU < key[v]) {
                    key[v] = edgeLengthFromU;
                    parent[v] = u;
                }
            }
        }
        printMST(undirectedGraph, parent);
    }

    public static int findVertexWithMinKey(UndirectedGraphByMatrixArray undirectedGraph, int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE;
        int vertexWitMinKey = -1;
        for (int v = 0; v < undirectedGraph.V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                vertexWitMinKey = v;
            }
        }
        return vertexWitMinKey;
    }

    // A utility function to print the constructed MST stored in
    // parent[]
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
