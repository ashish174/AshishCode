package algo.practice.graph.basic;

import java.util.List;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import algo.practice.graph.coregraphclasses.UndirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckCycleUsingDFS {
    public static final Logger LOGGER = LoggerFactory.getLogger(CheckCycleUsingDFS.class);

    /**
     * Check cycle in a directed graph.
     * Use DFS. There must be a back edge ([i.e., a node points to one of its ancestors in a DFS tree] )
     * To detect a back edge, we need to keep track of the visited nodes that are in the current recursion stack.
     *      - bcoz if visited node are in recursion stack, then backedge will form a cycle
     *      - bcoz if visited node are outside recursion stack, then backedge dont form cycle.
     * use 3 colours - white(0), grey(1), black(2). (Alternatively, you can use two different flags : isVisited(), isInRecursionStack() )
     * - grey means being visited and part of the recursion stack.
     * - black means completed(visited and removed from recursion stack)
     * If there is a vertex that is referencing to some vertex in recursion stack, then this will be a back edge that forms a cycle.
     * all ancestors of a node are present in recursion call stack during DFS. So if there is an edge to an ancestor in DFS, then this is a back edge.
     */
    public static void doDFSToFindCycle(DirectedGraph directedGraph, int st_vertex) {
        LOGGER.info("DFS for directed Graph to find cycle :-");
        int[] visited = new int[directedGraph.V];
        //This line is required bcoz start vertex is given, otherwise we can remove this line
        dfsUtil(directedGraph, st_vertex, visited);
        for (int u = 0; u < directedGraph.V; u++) {
            if (visited[u]==0) {
                dfsUtil(directedGraph, u, visited);
            }
        }
    }

    public static void dfsUtil(DirectedGraph directedGraph, int u, int[] visited) {
        visited[u] = 1;
        LOGGER.info("Node visited {}", u);
        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (Integer v : adjListOfU) {
            if (visited[v]==1) {
                LOGGER.info("Found a cycle using backedge from {} to {}", v, u);
            } else if (visited[v] == 0){
                dfsUtil(directedGraph, v, visited);
            }
        }
        visited[u] = 2;
    }

    /**
     * Check cycle in a undirected graph. We dont need graph colouring.
     * we just need 2 state visited flag and parent pointer.
     */
    public static void doDFSToFindCycle(UndirectedGraph undirectedGraph, int st_vertex) {
        LOGGER.info("DFS for undirected Graph to find cycle :-");
        int[] visited = new int[undirectedGraph.V];
        //This line is required bcoz start vertex is given, otherwise we can remove this line
        dfsUtil(undirectedGraph, st_vertex, visited, -1);
        for (int u = 0; u < undirectedGraph.V; u++) {
            if (visited[u]==0) {
                dfsUtil(undirectedGraph, u, visited, -1);
            }
        }
    }

    /**
     * Not same as directed graph because in an undirected graph, an edge from 1 to 2 also means an edge from 2 to 1.
     * To handle this, we keep track of the parent node (the node from which we came to the current node) in the DFS traversal
     * and ignore the parent node from the visited array.
     *
     * @param undirectedGraph
     * @param u
     * @param visited
     */
    public static void dfsUtil(UndirectedGraph undirectedGraph, int u, int[] visited, int parent) {
        visited[u] = 1;
        LOGGER.info("Node visited {}", u);
        List<Integer> adjListOfU = undirectedGraph.adjList.get(u);
        for (Integer v : adjListOfU) {
            // If an adjacent vertex is visited and
            // is not parent of current vertex,
            // then there exists a cycle in the graph.
            if (visited[v]==1 && v != parent) {
                LOGGER.info("Found a cycle using backedge from {} to {}", v, u);
            } else if (visited[v] == 0){
                //pass parent = u
                dfsUtil(undirectedGraph, v, visited, u);
            }
        }
        visited[u] = 2;
    }

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(4);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 0);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 3);
        LOGGER.info("#############CHECK Cycle for Directed Graph#############");
        //new GraphVisualizer(directedGraph).draw();
        //doDFSToFindCycle(directedGraph, 3);

        UndirectedGraph undirectedGraph = new UndirectedGraph(5);
        undirectedGraph.addEdge(1, 0);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(2, 1);
        undirectedGraph.addEdge(0, 3);
        undirectedGraph.addEdge(3, 4);
        undirectedGraph.addEdge(2, 3);
        LOGGER.info("#############CHECK Cycle for Undirected Graph#############");
        new GraphVisualizer(undirectedGraph).draw();
        doDFSToFindCycle(undirectedGraph, 0);

    }
}
