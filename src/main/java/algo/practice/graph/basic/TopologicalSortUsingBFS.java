package algo.practice.graph.basic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Uses Kahn's Algo
 * 1. We first compute the in-degree of every vertex — representing how many incoming edges each vertex has.
 * 2. Then, all vertices with an in-degree of 0 are added to a queue, as they can appear first in the ordering.
 * 3. Loop:
 *      - We repeatedly remove a vertex from the queue, add it to our result list, and reduce the in-degree of all its adjacent vertices.
 *      - If any of those vertices now have an in-degree of 0, they are added to the queue.
 *      - This process continues until the queue is empty,
 * 4. and the resulting order represents one valid topological sort of the graph
 *
 *
 * Topological Sort using Kahn's Algorithm (BFS-based).
 * Works on a directed acyclic graph (DAG).
 *
 * Steps:
 * 1. Compute in-degree of every vertex.
 * 2. Add all vertices with in-degree 0 to a queue.
 * 3. Repeatedly remove a vertex from the queue, add it to the result,
 *    and decrease the in-degree of all its neighbors. If any neighbor's
 *    in-degree becomes 0, add it to the queue.
 * 4. If we processed exactly n vertices (where n is number of vertices),
 *    the result is a valid topological order; otherwise, there was a cycle.
 */
public class TopologicalSortUsingBFS {

    /**
     * Performs topological sort on a directed graph using Kahn's algorithm.
     *
     * @param numVertices number of vertices, labeled 0..numVertices-1
     * @param edges       list of directed edges [u, v] meaning u -> v
     * @return a list representing one valid topological ordering
     * @throws IllegalArgumentException if the graph contains a cycle (not a DAG)
     */
    public List<Integer> topologicalSort(int numVertices, int[][] edges) {
        // Adjacency list: for each vertex u, store all vertices v such that u -> v
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adj.add(new ArrayList<>());
        }

        // inDegree[v] = number of incoming edges into vertex v
        int[] inDegree = new int[numVertices];

        // Build the graph and compute in-degree for each vertex
        for (int[] edge : edges) {
            int u = edge[0]; // from
            int v = edge[1]; // to
            adj.get(u).add(v);
            inDegree[v]++; // v has one more incoming edge
        }

        // Queue for vertices that currently have in-degree 0
        // These have no prerequisites, so they can appear first in the ordering
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numVertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // This will store the topological ordering
        List<Integer> topoOrder = new ArrayList<>();

        // Standard BFS-like processing, but driven by in-degree becoming 0
        while (!queue.isEmpty()) {
            // Take a vertex with no remaining incoming edges
            int u = queue.poll();
            topoOrder.add(u);

            // "Remove" all outgoing edges from u by decrementing neighbors' in-degree
            for (int v : adj.get(u)) {
                inDegree[v]--;

                // If in-degree becomes 0, v has no more prerequisites and can be processed
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        // If we didn't process all vertices, there is a cycle.
        // In a valid DAG, we must be able to order all numVertices nodes.
        if (topoOrder.size() != numVertices) {
            throw new IllegalArgumentException(
                    "Graph is not a DAG; topological sort not possible (cycle detected).");
        }

        return topoOrder;
    }

    // Simple demo
    public static void main(String[] args) {
        TopologicalSortUsingBFS solver = new TopologicalSortUsingBFS();

        int numVertices = 6;
        int[][] edges = {
                {5, 2},
                {5, 0},
                {4, 0},
                {4, 1},
                {2, 3},
                {3, 1}
        };

        List<Integer> order = solver.topologicalSort(numVertices, edges);
        System.out.println("Topological order: " + order);
    }
}
