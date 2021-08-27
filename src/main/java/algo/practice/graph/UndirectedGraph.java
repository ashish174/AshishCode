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
public class UndirectedGraph {
    public static Logger logger = LoggerFactory.getLogger(UndirectedGraph.class);

    public int V;
    public List<List<Integer>> adjList;
    //private List<Integer>[] adjMatrix1;

    public UndirectedGraph(int v) {
        V = v;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
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
        UndirectedGraph undirectedGraph = new UndirectedGraph(V);

        // Adding edges one by one
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 4);
        undirectedGraph.addEdge(1, 2);
        undirectedGraph.addEdge(1, 3);
        undirectedGraph.addEdge(1, 4);
        undirectedGraph.addEdge(2, 3);
        undirectedGraph.addEdge(3, 4);

        printGraph(undirectedGraph.adjList);

    }
}
