package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Stack;

/**
 * Ordering of tasks: if there is a edge u→v, vertex u comes before v in the ordering
 * i.e. u is pre-requisite task for v. u must be completed first before v.
 *
 * For finding dependencies & scheduling jobs accordingly.
 * Topological sorting is only for Directed Acyclic Graph (DAG)
 *
 * It is a linear ordering of vertices such that for every directed edge u v, vertex u comes before v in the ordering.
 * Note that a vertex is pushed to stack only when all of its adjacent vertices
 * (and their adjacent vertices and so on) are already in the stack.
 *
 * Appln :
 * 1. scheduling jobs from the given dependencies among jobs.
 * 2. Course Scheduling satisfying all pre-requisite criteria
 *
 * Appln:
 * 1. Build Project - build library first & then project
 * 2. Task scheduling -interdependent tasks
 * 3. Pre-requisite problems - University course structure - do basic then advance
 *
 * How: Run a DFS on all vertices, and once a vertice V is complete(all its adjacency list is pushed in stack),
 * push the vertice  into the stack last. This ensure all dependency of V is already in stack and then only V is pushed.
 *
 */
public class TopologicalSort {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopologicalSort.class);


    /**
     * Assumption: Graph is DAG(acyclic). If there is a cycle, then we have to modify code.
     *
     * Performs a topological sort using DFS on a given Directed Acyclic Graph (DAG).
     *
     * A topological sort is a linear ordering of vertices such that for every directed edge u -> v,
     * vertex u comes before v in the ordering.
     *
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     * Space Complexity: O(V), for storing visited vertices and the stack.
     *
     * @param directedGraph the input DAG
     */
    public static void doTopologicalSort(DirectedGraph directedGraph) {
        boolean[] visited = new boolean[directedGraph.V];
        Stack<Integer> stack = new Stack<>();

        // Note: No starting vertices given.
        // Perform DFS traversal on all unvisited vertices, so as to account for disconnected graph
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                // Recursive DFS helper function
                // This will visit all vertices reachable from u and push them onto the stack
                topologicalSortUtil(u, directedGraph, visited, stack);
            }
        }
        DirectedGraph.printGraph(directedGraph.adjList);
        // Print vertices in topological order (stack in top-down order)
        // Tasks which are pre-requisites for others, or have no dependency are on top, hence should be printed/executed first
        // The stack contains the vertices in reverse topological order, so we pop them off one by one
        // print stack in top down to get topological sorting
        System.out.println("Topological Sort for graph is : ");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " -> ");
        }
    }

  /**
   * Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
   * adjacent vertices and so on) are already in the stack.
   *
   * The key insight here is that we push a vertex onto the stack only after we've visited all its dependencies.
   * This ensures that the vertices are in topological order when we pop them off the stack.
   *
   *
   *  Recursive DFS helper function to perform topological sort.
   *
   *  @param u the current vertex
   *  @param directedGraph the input DAG
   *  @param visited array to keep track of visited vertices
   *  @param stack to store vertices in topological order
   *
   */
  private static void topologicalSortUtil(
      int u, DirectedGraph directedGraph, boolean[] visited, Stack<Integer> stack) {
        visited[u] = true;
      // Recursively visit all unvisited adjacent vertices
      // These are the dependencies of the current vertex
        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (int v : adjListOfU) {
            if (!visited[v]) {
                topologicalSortUtil(v, directedGraph, visited, stack);
            }
        }
        // Thus we have pushed all dependencies on u i.e. adjListOfU in Stack, and then finally we push u
        // Push current vertex onto stack after visiting all its dependencies
        // This ensures that the current vertex comes after its dependencies in the topological order
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
