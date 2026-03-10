package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;

import java.util.List;
import java.util.Stack;

/**
 * Use DFS.
 *
 * Ordering of tasks: if there is a edge u→v, vertex u comes before v in the ordering
 * i.e. u is pre-requisite task for v. u must be completed first before v.
 *
 * For finding dependencies & scheduling jobs accordingly.
 * Topological sorting is only for Directed Acyclic Graph (DAG). So we check for cycle as well.
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
public class TopologicalSortWithCycleCheck {

    /**
     * Graph should be DAG(acyclic) for solution. If there is a cycle, then we just return empty.
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
        boolean[] onStack = new boolean[directedGraph.V];   // NEW: track recursion stack
        Stack<Integer> stack = new Stack<>();

        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                if (!topologicalSortUtil(u, directedGraph, visited, onStack, stack)) {
                    System.out.println("Graph contains a cycle; topological sort not possible.");
                    return;
                }
            }
        }

        DirectedGraph.printGraph(directedGraph.adjList);
        System.out.println("Topological Sort for graph is : ");
        while (!stack.empty()) {
            System.out.print(stack.pop() + " -> ");
        }
    }

    /**
     * @return true if no cycle detected in this DFS subtree, false if a cycle is found
     *   /**
     *    * Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
     *    * adjacent vertices and so on) are already in the stack.
     *    *
     *    * The key insight here is that we push a vertex onto the stack only after we've visited all its dependencies.
     *    * This ensures that the vertices are in topological order when we pop them off the stack.
     *    *
     *    *
     *    */
    private static boolean topologicalSortUtil(
            int u,
            DirectedGraph directedGraph,
            boolean[] visited,
            boolean[] onStack,          // NEW
            Stack<Integer> stack) {

        visited[u] = true;
        onStack[u] = true;              // mark vertex as being in recursion stack

        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (int v : adjListOfU) {
            if (!visited[v]) {
                if (!topologicalSortUtil(v, directedGraph, visited, onStack, stack)) {
                    return false;       // propagate cycle detection upward
                }
            } else if (onStack[v]) {
                // back edge found: u -> v where v is on current DFS path ⇒ cycle
                return false;
            }
        }

        onStack[u] = false;             // remove from recursion stack
        stack.push(u);
        return true;
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
