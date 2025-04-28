package algo.practice.graph.shortestpath;

import algo.practice.graph.coregraphclasses.UndirectedGraphByMatrixArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * To find the shortest path (Shortest Path Tree - SPT) in a graph
 * Given a graph and a source vertex in the graph, find the shortest paths from the source to all vertices in the given graph.
 * Dijkstra’s algorithm is very similar to Prim’s algorithm. We maintain two set of vertices
 * one set contains vertices included in the shortest-path tree,
 * other set includes vertices not yet included in the shortest-path tree.
 * At every step of the algorithm, we find a vertex that is in the other set (not included) and has a minimum distance from the source.
 */
public class DijkstraAlgorithm {
    private static final Logger LOGGER = LoggerFactory.getLogger(DijkstraAlgorithm.class);


    public static void findSPTUsingDijkstra(UndirectedGraphByMatrixArray undirectedGraphByMatrixArray, int src) {
        // This will hold the shortest distance of current vertex from src
        int[] dist = new int[undirectedGraphByMatrixArray.V];
        // This Set is to denote whether vertex are included in sptSet or not
        boolean[] sptSet = new boolean[undirectedGraphByMatrixArray.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int count = 0; count < undirectedGraphByMatrixArray.V - 1; count++) {
            int u = findMinDistanceVertexNotYetIncluded(undirectedGraphByMatrixArray, dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < undirectedGraphByMatrixArray.V; v++) {
                if (!sptSet[v]
                        && undirectedGraphByMatrixArray.adjMatrix[u][v] != 0
                        && dist[u] + undirectedGraphByMatrixArray.adjMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + undirectedGraphByMatrixArray.adjMatrix[u][v];
                }
            }
        }
        LOGGER.info("Shortest Distance using Dijkstra : ");
        for (int i = 0; i < dist.length; i++) {
            LOGGER.info("{} -> {} : {}", src, i, dist[i]);
        }
    }

    private static int findMinDistanceVertexNotYetIncluded(UndirectedGraphByMatrixArray undirectedGraphByMatrixArray, int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minDistanceVertex = -1;
        for (int v = 0; v < sptSet.length; v++) {
            if (!sptSet[v] && min > dist[v]) {
                min = dist[v];
                minDistanceVertex = v;
            }
        }
        return minDistanceVertex;

    }


    public static void main(String[] args) {
        UndirectedGraphByMatrixArray undirectedGraphByMatrixArray = new UndirectedGraphByMatrixArray(9);
        // Adding edges one by one
        undirectedGraphByMatrixArray.adjMatrix = new int[][]
                {
                        {0, 4, 0, 0, 0, 0, 0, 8, 0},
                        {4, 0, 8, 0, 0, 0, 0, 11, 0},
                        {0, 8, 0, 7, 0, 4, 0, 0, 2},
                        {0, 0, 7, 0, 9, 14, 0, 0, 0},
                        {0, 0, 0, 9, 0, 10, 0, 0, 0},
                        {0, 0, 4, 14, 10, 0, 2, 0, 0},
                        {0, 0, 0, 0, 0, 2, 0, 1, 6},
                        {8, 11, 0, 0, 0, 0, 1, 0, 7},
                        {0, 0, 2, 0, 0, 0, 6, 7, 0}
                };

        findSPTUsingDijkstra(undirectedGraphByMatrixArray, 0);
    }
}
