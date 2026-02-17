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
 * 2 → visited (fully processed, & out of stack)
 *
 * boolean can be used only when it is a Connected + Undirected Graph
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
 *
 *
 *
 * 3 state:
 * In directed graphs, being “visited” is not enough information. Need to know whether a node is currently in recursion stack
 * When DFS finishes processing a node, it is still marked visited. So you cannot distinguish between:
 *      - A node that is completely done
 *      - A node that is currently in recursion stack
 * But detecting a cycle requires knowing:
 *      Is this node still active in current DFS path?
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
