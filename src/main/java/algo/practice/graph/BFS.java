package algo.practice.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BFS earlier we do in a tree. Tree don't have cycle.
 * Now we are doing it on a graph. The only diff is that unlike tree, graph can have cycle (back-edge).
 * So we have to neglect the already visited node.
 * Unvisited Node = white
 * Visited Node = grey
 */
public class BFS {
    public static final Logger LOGGER = LoggerFactory.getLogger(BFS.class);

    public static void doBFS(DirectedGraph directedGraph, int st_vertex) {
        boolean[] visited = new boolean[directedGraph.V];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(st_vertex);
        visited[st_vertex] = true;
        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            LOGGER.info("Node visited {}", u);
            List<Integer> adjListoFU = directedGraph.adjList.get(u);
            for (Integer v : adjListoFU) {
                if (!visited[v]) {
                    queue.add(v);
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
        doBFS(directedGraph, 0);
    }
}
