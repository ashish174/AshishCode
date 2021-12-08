package algo.practice.graph.basic;

import algo.practice.graph.Edge;
import algo.practice.graph.UndirectedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Create Single Element Set for all vertices. Alternatively we can use vertices array and parent array to perform similar operation
 * process all edges one by one
 * <p>
 * Find : Check if both vertices are in same set.
 * If Yes, then we found a cycle
 * If No, then perform a Union
 * Union : If both vertices are not in same set, then perform a union of both Set
 */
public class DetectCycleInUndirectedGraphUsingDisjointSets {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectCycleInUndirectedGraphUsingDisjointSets.class);

    public static boolean detectCycleUsingDisjointSets(UndirectedGraph undirectedGraph) {
        int[] parent = new int[undirectedGraph.V];
        Arrays.fill(parent, -1);
        for (Edge edge : undirectedGraph.edges) {
            int subsetOfSrcVertex = find(parent, edge.getSrc());
            int subsetOfDestVertex = find(parent, edge.getDest());
            if (subsetOfSrcVertex != subsetOfDestVertex) {
                union(parent, subsetOfSrcVertex, subsetOfDestVertex);
            } else {
                LOGGER.error("Cycle detected with edge {} - {}", edge.getSrc(), edge.getDest());
                return true;
            }
        }
        LOGGER.info("No Cycle detected for the graph");
        return false;
    }

    private static void union(int[] parent, int src, int dest) {
        parent[Math.min(src, dest)] = Math.max(src, dest);
    }

    private static int find(int[] parent, int vertex) {
        if (parent[vertex] == -1) {
            return vertex;
        }
        /*
        while(parent[vertex]!=-1) {
            vertex = parent[vertex];
        }
        return vertex;
        */
        return find(parent, parent[vertex]);

    }

    public static void main(String[] args) {
        UndirectedGraph undirectedGraph = new UndirectedGraph(5);

        // Adding edges one by one
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 4);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(1, 3);
        undirectedGraph.addEdge(1, 4);
        undirectedGraph.addEdge(2, 3);
        undirectedGraph.addEdge(3, 4);

        detectCycleUsingDisjointSets(undirectedGraph);

    }
}
