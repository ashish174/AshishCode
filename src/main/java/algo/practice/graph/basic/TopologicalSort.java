package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Stack;

/**
 * Topological sorting is only for Directed Acyclic Graph (DAG)
 * It is a linear ordering of vertices such that for every directed edge u v, vertex u comes before v in the ordering.
 * Note that a vertex is pushed to stack only when all of its adjacent vertices (and their adjacent vertices and so on) are already in the stack.
 * Appln :
 * 1. scheduling jobs from the given dependencies among jobs.
 * 2.
 */
public class TopologicalSort {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopologicalSort.class);


    public static void doTopologicalSort(DirectedGraph directedGraph) {
        boolean[] visited = new boolean[directedGraph.V];
        Stack<Integer> stack = new Stack<>();
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                topologicalSortUtil(u, directedGraph, visited, stack);
            }
        }
        DirectedGraph.printGraph(directedGraph.adjList);
        // print stack in top down to get topological sorting
        System.out.println("Topological Sort for graph is : ");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " -> ");
        }
    }

    /**
     * Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
     * adjacent vertices and so on) are already in the stack.
     */
    private static void topologicalSortUtil(int u, DirectedGraph directedGraph, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (int v : adjListOfU) {
            if (!visited[v]) {
                topologicalSortUtil(v, directedGraph, visited, stack);
            }
        }
        // Thus we have pushed all dependencies on u i.e. adjListOfU in Stack, and then finally we push u
        stack.push(u);
    }

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(6);

        // Adding edges one by one
        directedGraph.addEdge(5, 2);
        directedGraph.addEdge(5, 0);
        directedGraph.addEdge(4, 0);
        directedGraph.addEdge(4, 1);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(2, 1);
        new GraphVisualizer(directedGraph).draw();

        doTopologicalSort(directedGraph);
    }
}
