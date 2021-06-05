package algo.practice.graph;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Graph Usage :- Represent any network like City Roads, telephone network, circuit network,
 * social network like facebook friend network, or linkedin network.
 *
 * V - num of vertices
 * 2 Representations:-
 *    1. Adjacency Matrix :- adj[V][V] - Pro : lookup/searching if edge exist from u-v is easier. Con: Adding new vertex is costly.
 *    2. Adjacency List :-  adj<List>[V] - Pro : less space, adding a new vertex is easier. Con : searching take V time.
 */
public class DirectedGraph {
    public static Logger logger = LoggerFactory.getLogger(DirectedGraph.class);

    public int V;
    public List<List<Integer>> adjList;
    //private List<Integer>[] adjMatrix1;

    public DirectedGraph(int v) {
        V = v;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }

    static void printGraph(List<List<Integer>> adjList) {
        StringBuilder edgeListbuilder = new StringBuilder();
        for (int i = 0; i < adjList.size(); i++) {
            edgeListbuilder.append("head");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                edgeListbuilder.append(" -> " + adjList.get(i).get(j));
            }
            logger.info("Adjacency list of vertex {} : {}", i, edgeListbuilder.toString());
            // reset stringbuilder
            edgeListbuilder.setLength(0);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        DirectedGraph directedGraph = new DirectedGraph(V);

        // Adding edges one by one
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 4);
        directedGraph.addEdge(1, 2);
        directedGraph.addEdge(1, 3);
        directedGraph.addEdge(1, 4);
        directedGraph.addEdge(2, 3);
        directedGraph.addEdge(3, 4);

        printGraph(directedGraph.adjList);

    }
}
