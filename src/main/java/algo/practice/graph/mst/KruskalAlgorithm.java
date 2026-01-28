package algo.practice.graph.mst;

import algo.practice.graph.coregraphclasses.Edge;
import algo.practice.graph.coregraphclasses.UndirectedGraphWithWeight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * For Weighted undirected graphs : Finding Set of edges connecting all nodes with minimum cost. No starting vertex given normally.
 * If starting vertex is given, then you can start with that vertex.
 *  Finds and prints the Minimum Spanning Tree (MST) of an undirected, weighted graph using Kruskal's Algorithm.
 *  Cycle detection is efficiently handled using a union-find (disjoint-set) data structure.
 *
 * In Kruskal, we try to pick next minimum edges so that cycle is not formed.
 * Here we sort all edges, and then start picking minimum edges so that cycle is not formed
 * <p>
 * Spanning Tree(ST) : a subgraph that is a tree and connects all the vertices together.
 * Minimum Spanning Tree(MST): a subgraph that is a tree(connected) with least cost and connects all the vertices together
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
 *
 * #############################################################################
 * This solution used Union-Find Datastructure
 * Union Find (Disjoint Set Union/DSU)
 * What is it?
 * The Union Find data structure (also called Disjoint Set Union or DSU) is a structure for keeping track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
 * It supports two operations very efficiently:
 * Find: Determine which subset a particular element belongs to (this identifies the “representative” or “root” of its set).
 * Union: Merge the subsets that contain two specified elements into a single subset.
 *
 * Basic Structure
 * - Each element points to a parent.
 * - The root/representative of a set is an element that is its own parent.
 * - Sets are represented by trees, usually with each node having a link to its parent.
 * #############################################################################
 *
 */
public class KruskalAlgorithm {
    private static final Logger LOGGER = LoggerFactory.getLogger(KruskalAlgorithm.class);


    public static void findMSTByKruskal(UndirectedGraphWithWeight undirectedGraphWithWeight) {
        // parent[] is used to track the parent of each vertex, for union-find operations (cycle detection)
        int[] parent = new int[undirectedGraphWithWeight.V];
        Arrays.fill(parent, -1);

        // Counts edges included in MST; MST will have V-1 edges
        int mstEdgeCount = 0;
        Edge[] mstEdges = new Edge[undirectedGraphWithWeight.V - 1];
        // 1. Sort all the edges in increasing order of their weight
        Collections.sort(undirectedGraphWithWeight.edges, Comparator.comparingInt(Edge::getWeight));

        // 2. Traverse sorted edges, and add the smallest edge to MST
        // if it doesn't form a cycle(i.e. both edge vertex don't belong to same set)
        for (Edge edge : undirectedGraphWithWeight.edges) {
            // If MST is complete (V-1 edges), break out
            if (mstEdgeCount == (undirectedGraphWithWeight.V - 1)) {
                break;
            }
            // Find the set (using union-find) for source and destination;
            // this is used for cycle detection(i.e. both edge vertex don't belong to same set)
            int subSetOfSrcVertex = find(parent, edge.getSrc());
            int subSetOfdestVertex = find(parent, edge.getDest());
            // If including this edge does not form a cycle, include it in MST
            if (subSetOfSrcVertex != subSetOfdestVertex) {
                // Union the sets (meaning, they are now connected)
                union(parent, subSetOfSrcVertex, subSetOfdestVertex);
                // Add edge to the MST result
                mstEdges[mstEdgeCount] = edge;
                mstEdgeCount++;
            }
        }
        LOGGER.info("Kruskal MST Edges :- ");
        for (Edge edge : mstEdges) {
            LOGGER.info("{} -> {} : {}", edge.getSrc(), edge.getDest(), edge.getWeight());
        }

    }

    // Union-find utility - union function: Connect two subsets in the parent array.
    // Connects two subsets into a single subset by updating their parent
    private static void union(int[] parent, int subSetOfSrcVertex, int subSetOfdestVertex) {
    // the subset with the smaller representative gets "attached" to the subset with the larger
    // representative. i.e.  parent[smaller value] = larger value
    // Example :
    // subSetOfSrcVertex = 2
    // subSetOfdestVertex = 5
    // parent[2] = 5;
    parent[Math.min(subSetOfSrcVertex, subSetOfdestVertex)] =
        Math.max(subSetOfSrcVertex, subSetOfdestVertex);
    }

    // Union-find utility - find function : recursively finds representative of the subset for a vertex.
    // Recursively finds the root representative for a given vertex's subset
    private static int find(int[] parent, int vertex) {
        if (parent[vertex] == -1) {
            return vertex; // If parent is -1, this is representative/root of the subset
        }
        return find(parent, parent[vertex]); // Recursively find the root
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
