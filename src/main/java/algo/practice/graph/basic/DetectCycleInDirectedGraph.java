package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.DirectedGraph;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A cycle is there if there is a backedge.
 * A backedge is an edge from a node to itself (self-loop) or one of its ancestors(parents in the current stack) in the same tree produced by DFS.
 */
public class DetectCycleInDirectedGraph {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectCycleInDirectedGraph.class);

    public static boolean detectCycle(DirectedGraph directedGraph) {
        boolean[] recursionStack = new boolean[directedGraph.V];
        boolean[] visited = new boolean[directedGraph.V];
        AtomicBoolean isCycleDetected = new AtomicBoolean(false);
        for (int u = 0; u < directedGraph.V; u++) {
            if (!visited[u]) {
                dfsUtil(u, directedGraph, visited, recursionStack, isCycleDetected);
            }
        }
        return isCycleDetected.get();
    }

    private static void dfsUtil(int u, DirectedGraph directedGraph, boolean[] visited, boolean[] recursionStack, AtomicBoolean isCycleDetected) {
        visited[u] = true;
        recursionStack[u] = true;
        List<Integer> adjListOfU = directedGraph.adjList.get(u);
        for (Integer v : adjListOfU) {
            if (recursionStack[v]) {
                LOGGER.info("Found back edge from {} -> {}", u, v);
                isCycleDetected.set(true);
            } else {
                if (!visited[v]) {
                    dfsUtil(v, directedGraph, visited, recursionStack, isCycleDetected);
                }
            }
        }
        recursionStack[u] = false;
    }

    public static boolean detectCycleV2(DirectedGraph directedGraph, int u, boolean[] visited, boolean[] recStack) {
        // If the current node is already in the recursion
        // stack, a cycle is detected
        if(recStack[u]) {
            return true;
        }
        // If already visited and not in recStack, it's not
        // part of a cycle
        if(visited[u]) {
            return false;
        }
        // Mark the current node as visited and add it to
        // the recursion stack
        visited[u] = true;
        recStack[u] = true;
        // Recur for all adjacent vertices
        for(int v: directedGraph.adjList.get(u)) {
            if(detectCycleV2(directedGraph, v, visited, recStack)) {
                return true;
            }
        }
        // Backtrack: remove the vertex from recursion stack
        recStack[u] = false;
        return false;
    }


        public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph(5);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(1, 4);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 4);
        directedGraph.addEdge(4, 2);
        new GraphVisualizer(directedGraph).draw();
        boolean isCycleDetected = detectCycle(directedGraph);
        LOGGER.info("Is Cycle Detected ?? {}", isCycleDetected);
    }
}
