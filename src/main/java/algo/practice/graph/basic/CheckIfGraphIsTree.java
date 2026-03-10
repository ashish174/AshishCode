package algo.practice.graph.basic;

/**
 *
 * Check: CheckIfValidTree class, which has more simpler soln
 * Two properties:-
 * 1. No Cycle
 * 2. No disconnected component
 * 3. V-1 edge count
 *
 * Do a BFS, and check all are marked visited. And check no cycles
 *
 */
import algo.practice.graph.coregraphclasses.UndirectedGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckIfGraphIsTree {

    /**
     * Checks if the given undirected graph is a tree.
     *
     * A graph is considered a tree if it satisfies 3 properties:
     * 1. V-1 edge count
     * 2. It does not contain any cycles.
     * 3. It is connected (i.e., there is a path between every pair of vertices).
     *
     * @param graph the undirected graph to be checked
     * @return true if the graph is a tree, false otherwise
     */
    public static boolean isTree(UndirectedGraph graph) {
        boolean[] visited = new boolean[graph.V];
        //check for cycle
        if (hasCycle(graph)) {
            return false;
        }
        //check for disconnected
        bfs(graph, 0, visited);
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given undirected graph contains any cycles.
     *
     * A cycle is a path that starts and ends at the same vertex, and passes through at least one other vertex.
     *
     * @param graph the undirected graph to be checked
     * @return true if the graph contains a cycle, false otherwise
     */
    private static boolean hasCycle(UndirectedGraph graph) {
        boolean[] visited = new boolean[graph.V];
        for (int i = 0; i < graph.V; i++) {
            if (!visited[i] && hasCycleUtil(graph, i, visited, -1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasCycleUtil(UndirectedGraph graph, int u, boolean[] visited, int parent) {
        visited[u] = true;
        List<Integer> adjListOfU = graph.adjList.get(u);
        for (Integer v : graph.adjList.get(u)) {
            //This checks if the visited vertex v is not the parent of the current vertex u.
            // In other words, it checks if v is not the vertex that we came from to reach u.
            if (visited[v] && v != parent) {
                return true;
            }
            if (!visited[v] && hasCycleUtil(graph, v, visited, u)) {
                return true;
            }
        }
        return false;
    }

    private static void bfs(UndirectedGraph graph, int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            List<Integer> adjListOfU = graph.adjList.get(u);
            for (Integer v : adjListOfU) {
                if (!visited[v]) {
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }
    }



    public static boolean isTreeV2(UndirectedGraph graph) {
        boolean[] visited = new boolean[graph.V];
        if (!dfs(graph, 0, visited, -1)) {
            return false;
        }
        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

    private static boolean dfs(UndirectedGraph graph, int u, boolean[] visited, int parent) {
        visited[u] = true;
        for (Integer v : graph.adjList.get(u)) {
            if (!visited[v]) {
                if (!dfs(graph, v, visited, u)) {
                    return false;
                }
            } else if (v != parent) { // visited[v] = true && v != parent
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Example usage:
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        System.out.println(isTree(graph));  // prints: true

        UndirectedGraph graphWithCycle = new UndirectedGraph(5);
        graphWithCycle.addEdge(0, 1);
        graphWithCycle.addEdge(1, 2);
        graphWithCycle.addEdge(2, 3);
        graphWithCycle.addEdge(3, 4);
        graphWithCycle.addEdge(4, 0);
        System.out.println(isTree(graphWithCycle));  // prints: false

        UndirectedGraph disconnectedGraph = new UndirectedGraph(5);
        disconnectedGraph.addEdge(0, 1);
        disconnectedGraph.addEdge(1, 2);
        System.out.println(isTree(disconnectedGraph));  // prints: false
    }
}

