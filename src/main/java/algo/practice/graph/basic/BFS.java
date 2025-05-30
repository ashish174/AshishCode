package algo.practice.graph.basic;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Total 2 diff from Graph to tree:
 * - Graph can have cycle
 * - Graph can be disconnected
 *
 * Like BFS in tree, we begin traverse vertices level by level using a queue data structure.
 * The only catch here is that, unlike  trees,  graphs  may contain cycles, so we may come to the same node again
 *
 * BFS earlier we do in a tree. Tree don't have cycle.
 * Now we are doing it on a graph. The only diff is that unlike tree, graph can have cycle (back-edge).
 * So we have to neglect the already visited node.
 * For this, we can use a visited array to mark if a node has been visited at least once. It can be a boolean array.
 * Unvisited Node = white (false)
 * Visited Node = grey (true)
 * Also we have to handle disconnected graph i.e. All the vertices may not be reachable from a given vertex. And, we have to cover all vertex at least once.
 *
 * Uses FIFO. more suitable for searching vertices closer to the given source.
 *
 *
 *  Appln:
 *  1. Finding Shortest Path(esp. when weight of every edge is same) and MST for undirected graph
 *  2. MST for weighted graph
 *  3. P2P Network
 *  4. Crawlers in Search engines
 *  5. Social network - to find all people upto K distance
 *  6. GPS : to find neighboring locations
 *  7. Broadcasting
 *  8. To traversing a game tree to find the best move.
 *  9. Topological Sorting in DAG
 *  10. Path Finding
 */
public class BFS {
    public static final Logger LOGGER = LoggerFactory.getLogger(BFS.class);

    public static void doBFS(DirectedGraph directedGraph, int st_vertex) {
        LOGGER.info("BFS for directed Graph is :-");
        boolean[] visited = new boolean[directedGraph.V];
        bfsUtil(directedGraph, st_vertex, visited);
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                bfsUtil(directedGraph, u, visited);
            }
        }
    }

    public static void bfsUtil(DirectedGraph directedGraph, int st_vertex, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(st_vertex);
        visited[st_vertex] = true;
        while (!queue.isEmpty()) {
            Integer u = queue.poll();
            LOGGER.info("Node visited {}", u);
            List<Integer> adjListOfU = directedGraph.adjList.get(u);
            for (Integer v : adjListOfU) {
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
        new GraphVisualizer(directedGraph).draw();
        doBFS(directedGraph, 1);
    }
}
