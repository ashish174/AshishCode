package algo.practice.graph.basic;

import java.util.List;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *  * Total 2 diff from Graph to tree:
 *  * - Graph can have cycle
 *  * - Graph can be disconnected
 *
 * DFS earlier we do in a tree. Tree don't have cycle.
 * Now we are doing it on a graph. The only diff is that unlike tree, graph can have cycle (back-edge).
 * So we have to neglect the already visited node.
 * For this, we can use a visited array to mark if a node has been visited at least once.
 * Unvisited Node = white (false)
 * Visited Node = grey (true)
 * Also we have to handle disconnected graph i.e. All the vertices may not be reachable from a given vertex. And, we have to cover all vertex at least once
 * <p>
 *
 *  * 1. Track all visited node, to exclude traversing back edges/cycle.
 *  * 2. loop through all vertex at least once, so as to cover disconnected graph
 *  DFS Uses LIFO(Stack/Recursion). more suitable when there are solutions away from source.
 *
 *
 * Appln:
 * 1. To Check if graph has cycle/back edges
 * 2. Path finding in a maze from some starting point u to end point z.
 *          The current stack will hold all path from u to z. As soon as z is found, return the content of stack.
 * 3. Topological sorting for scheduling jobs
 * 4. Find Strongly connected component
 * 5. Solving puzzles that has only one soln, Ex: Maze,
 *
 *
 * Note: boolean visited is not enough, we need 3 states to track*
 * 0 → unvisited
 * 1 → visiting (in current recursion stack)*
 * 2 → visited (fully processed)
 *
 * boolean can be used only when it is a Connected + Undirected Graph
 *
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

    // Not uses Queue like BFS, rather uses recursion or stack.
    public static void dfsUtil(DirectedGraph directedGraph, int u, boolean[] visited) {
        visited[u] = true;
        LOGGER.info("Node visited {}", u);
        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (Integer v : adjListOfU) {
            if (!visited[v]) {
                dfsUtil(directedGraph, v, visited);
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
        doDFS(directedGraph, 3);
    }

}
