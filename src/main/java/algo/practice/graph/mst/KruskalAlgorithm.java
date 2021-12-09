package algo.practice.graph.mst;

import algo.practice.graph.Edge;
import algo.practice.graph.UndirectedGraphWithWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * In Kruskal, we try to pick next minimum edges so that cycle is not formed.
 * Here we sort all edges, and then start picking minimum edges so that cycle is not formed
 * <p>
 * Spanning Tree : a subgraph that is a tree and connects all the vertices together.
 * <p>
 * This algo is used To find MST. A minimum spanning tree has (V – 1) edges
 * Appln for MST problems:
 * 1. Network design :- telephone, electrical, hydraulic, TV cable, computer, road
 * Ex: Want to connect 10 places/offices with minimum road/telephone wire cost.
 * 2. Solving NP hard prblm - travelling salesman prblm -  find the shortest path that visits each point at least once.
 * <p>
 * Implementation:- This is a greedy approach as we are picking the minimum edge at each step
 * 1. Sort all the edges in non-decreasing order of their weight.
 * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far. If cycle is not formed, include this edge. Else, discard it.
 * 3. Repeat step#2 until there are (V-1) edges in the spanning tree.
 */
public class KruskalAlgorithm {
    private static final Logger LOGGER = LoggerFactory.getLogger(KruskalAlgorithm.class);


    public static void findMSTByKruskal(UndirectedGraphWithWeight undirectedGraphWithWeight) {
        int[] parent = new int[undirectedGraphWithWeight.V];
        Arrays.fill(parent, -1);
        int mstEdgeCount = 0;
        Edge[] mstEdges = new Edge[undirectedGraphWithWeight.V - 1];
        //Sort edges in ascending order
        Collections.sort(undirectedGraphWithWeight.edges, Comparator.comparingInt(Edge::getWeight));
        for (Edge edge : undirectedGraphWithWeight.edges) {
            if (mstEdgeCount == (undirectedGraphWithWeight.V - 1)) {
                break;
            }
            int subSetOfSrcVertex = find(parent, edge.getSrc());
            int subSetOfdestVertex = find(parent, edge.getDest());
            if (subSetOfSrcVertex != subSetOfdestVertex) {
                union(parent, subSetOfSrcVertex, subSetOfdestVertex);
                mstEdges[mstEdgeCount] = edge;
                mstEdgeCount++;
            }
        }
        LOGGER.info("Kruskal MST Edges :- ");
        for (Edge edge : mstEdges) {
            LOGGER.info("{} -> {} : {}", edge.getSrc(), edge.getDest(), edge.getWeight());
        }

    }

    private static void union(int[] parent, int subSetOfSrcVertex, int subSetOfdestVertex) {
        parent[Math.min(subSetOfSrcVertex, subSetOfdestVertex)] = Math.max(subSetOfSrcVertex, subSetOfdestVertex);
    }

    private static int find(int[] parent, int vertex) {
        if (parent[vertex] == -1) {
            return vertex;
        }
        return find(parent, parent[vertex]);
    }

    public static void main(String[] args) {
        UndirectedGraphWithWeight undirectedGraphWithWeight = new UndirectedGraphWithWeight(9);

        // Adding edges one by one
        undirectedGraphWithWeight.addEdge(0, 1, 4);
        undirectedGraphWithWeight.addEdge(0, 7, 8);
        undirectedGraphWithWeight.addEdge(1, 2, 8);
        undirectedGraphWithWeight.addEdge(1, 7, 11);
        undirectedGraphWithWeight.addEdge(2, 3, 7);
        undirectedGraphWithWeight.addEdge(2, 5, 4);
        undirectedGraphWithWeight.addEdge(2, 8, 2);
        undirectedGraphWithWeight.addEdge(3, 4, 9);
        undirectedGraphWithWeight.addEdge(3, 5, 14);
        undirectedGraphWithWeight.addEdge(4, 5, 10);
        undirectedGraphWithWeight.addEdge(5, 6, 2);
        undirectedGraphWithWeight.addEdge(6, 7, 1);
        undirectedGraphWithWeight.addEdge(6, 8, 6);
        undirectedGraphWithWeight.addEdge(7, 8, 7);

        findMSTByKruskal(undirectedGraphWithWeight);
    }
}
