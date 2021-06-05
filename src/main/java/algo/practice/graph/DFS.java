package algo.practice.graph;

import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DFS earlier we do in a tree. Tree don't have cycle.
 * Now we are doing it on a graph. The only diff is that unlike tree, graph can have cycle (back-edge).
 * So we have to neglect the already visited node.
 * For this, we can use a visited array to mark if a node has been visited at least once.
 * Unvisited Node = white
 * Visited Node = grey
 * Also we have to handle disconnected graph i.e. All the vertices may not be reachable from a given vertex. And, we have to cover all vertex at least once
 */
public class DFS {
    public static final Logger LOGGER = LoggerFactory.getLogger(DFS.class);

    public static void doDFS(DirectedGraph directedGraph, int st_vertex) {
        LOGGER.info("DFS for directed Graph is :-");
        boolean[] visited = new boolean[directedGraph.V];
        dfsUtil(directedGraph, st_vertex, visited);
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                dfsUtil(directedGraph, u, visited);
            }
        }

    }

    public static void dfsUtil(DirectedGraph directedGraph, int st_vertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(st_vertex);
        visited[st_vertex] = true;
        while (!stack.isEmpty()) {
            Integer u = stack.pop();
            LOGGER.info("Node visited {}", u);
            List<Integer> adjListOfU = directedGraph.adjList.get(u);
            for (Integer v : adjListOfU) {
                if (!visited[v]) {
                    stack.push(v);
                    visited[v] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(4);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 0);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 3);
        doDFS(directedGraph, 3);
    }

}
