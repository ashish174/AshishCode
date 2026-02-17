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
 * Also we have to handle disconnected graph i.e. All the vertices may not be reachable from a given vertex.
 * And, we have to cover all vertex at least once.
 *
 * 1. Track all visited node, to exclude traversing back edges/cycle.
 * 2. loop through all vertex at least once, so as to cover disconnected graph
 *
 * BFS Uses FIFO(Queue). more suitable for searching solution or vertices closer to the given source.
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
 *
 *  2 state visited flag vs 3 state visited/colour array.
 *  -If graph is undirected (2 states usually enough.)
 *  - If graph is directed and cycles matter (Use 3 states)
 *
 * 2 state:
 * In undirected graph, every edge goes both way.
 * A — B means
 * From A → B, and then B sees A again.
 * That does NOT mean cycle.
 * So we use:
 *      1. visited[]
 *      2. and a parent variable
 * Undirected Cycle Detection Logic:
 *      When exploring: If neighbor is visited AND neighbor ≠ parent → cycle.
 * Also 2 state is sufficient for directed graphs, as long as you do not need to:
 *      - Detect cycles in a directed graph
 *      - Perform topological sorting
 *      - Distinguish between forward edges and back edges (as needed in some advanced algorithms)
 *
 *
 * 3 state:
 * In directed graphs, being “visited” is not enough information. Need to know whether a node is currently in recursion stack
 * When DFS finishes processing a node, it is still marked visited. So you cannot distinguish between:
 *      - A node that is completely done
 *      - A node that is currently in recursion stack
 * But detecting a cycle requires knowing: Is this node still active in current DFS path?
 * To detect a back edge, we need to keep track of the visited nodes that are in the current recursion stack.
 *      - bcoz if visited node are in recursion stack, then backedge will form a cycle
 *      - bcoz if visited node are outside recursion stack, then backedge dont form cycle.
 *
 *      A → B → C
 *          ↑   ↓
 *          ←----
 *
 *     A → B   C
 *         ↑   ↓
 *         ←----
 *
 *      When doing DFS: A → B → C
 *              From C we try to go to B
 *                  If B is:
 *                      Already fully processed → OK
 *                      Still being processed (in recursion stack) → cycle
 *                  So we must distinguish between:
 *                      “Visited long ago”
 *                      “Currently in path”
 *
 *
 * 2 state usecases
 * 1. Standard DFS / BFS Traversal
 * Finding connected components
 * Counting islands
 * Checking if a path exists
 * Shortest path in unweighted graph (BFS)
 * 2. Avoiding Infinite Loops in Cyclic Graphs : In an undirected graph, once you visit a node, you don't need to visit it again.
 * 3. Tree Traversal : Trees don’t have cycles (if properly formed), so simple visited flag is enough.
 *
 * 3 state usecases
 * 1. Cycle Detection in Directed Graph.
 *      as simply knowing visited is not enough. what if there is a back edge to a node being processed,
 *      which means a cycle.
 * 2. Topological Sorting (DFS based)
 *      Topological sorting only works for DAG (Directed Acyclic Graph).
 *      If cycle found → no topo sort possible
 *  3. Checking If Graph is Bipartite (Alternative Interpretation)
 *
 *
 *
 *
 */
public class BFS {
    public static final Logger LOGGER = LoggerFactory.getLogger(BFS.class);

    public static void doBFS(DirectedGraph directedGraph, int st_vertex) {
        LOGGER.info("BFS for directed Graph is :-");
        boolean[] visited = new boolean[directedGraph.V];
        //This line is required bcoz start vertex is given, otherwise we can remove this line
        bfsUtil(directedGraph, st_vertex, visited);
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                bfsUtil(directedGraph, u, visited);
            }
        }
    }

    // Not uses recursion like DFS, rather uses a Queue
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
