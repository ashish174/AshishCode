package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.Edge;
import algo.practice.graph.coregraphclasses.GraphVisualizer;
import algo.practice.graph.coregraphclasses.UndirectedGraph;
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
 *
 * Union Find Algo
 * - Each Set represent one connected component
 * - Root is the representation element of  given connected component
 * - If two nodes have the same root → they’re connected
 *
 *
 */
public class DetectCycleInUndirectedGraphUsingDisjointSets {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectCycleInUndirectedGraphUsingDisjointSets.class);

    /**
     * Detects whether an undirected graph contains a cycle using disjoint sets.
     *
     * This method iterates over each edge in the graph, checks if the source and destination vertices belong to the same set,
     * and performs a union operation if they do not.
     * If it finds two vertices that belong to the same set, it indicates the presence of a cycle.
     *
     */
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

    /**
     * Performs a union operation on two subsets represented by their respective roots.
     *
     * This method merges the two subsets into a single subset by making the smaller root point to the larger root.
     *
     * @param parent an array representing the parent-child relationships between vertices
     * @param subSetOfSrcVertex the root of the first subset
     * @param subSetOfdestVertex the root of the second subset
     */
    private static void union(int[] parent, int subSetOfSrcVertex, int subSetOfdestVertex) {
        parent[Math.min(subSetOfSrcVertex, subSetOfdestVertex)] = Math.max(subSetOfSrcVertex, subSetOfdestVertex);
    }

    /**
     * Finds the representative element (or root) of the set that contains the given vertex.
     *
     * This method uses path compression to optimize the search process. If the vertex is not the root of its set,
     * it recursively calls itself with the parent of the current vertex until it reaches the root.
     *
     * @param parent an array representing the parent-child relationships between vertices
     * @param vertex the vertex whose set representative is to be found
     * @return the representative element (root) of the set containing the given vertex
     */
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
        new GraphVisualizer(undirectedGraph).draw();

        detectCycleUsingDisjointSets(undirectedGraph);

    }
}
