package algo.practice.graph;

import java.util.List;
import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * use 3 colours - white(0), grey(1), black(2)
 * If there is a vertex that is referencing to some vertex in recursion stack, then this will be a back edge that forms a cycle.
 */
public class CheckCycle {
    public static final Logger LOGGER = LoggerFactory.getLogger(CheckCycle.class);

    public static void doDFSToFindCycle(DirectedGraph directedGraph, int st_vertex) {
        LOGGER.info("DFS for directed Graph to find cycle :-");
        int[] visited = new int[directedGraph.V];
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

    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(4);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(2, 0);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 3);
        doDFSToFindCycle(directedGraph, 3);
    }
}
