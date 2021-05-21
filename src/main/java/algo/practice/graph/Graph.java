package algo.practice.graph;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Graph Usage :- Represent any network like City Roads, telephone network, circuit network, friend network
 * V - num of vertices
 * 2 Representations:-
 *    1. Adjacency Matrix :- adj[V][V]
 *    2. Adjacency List :-  adj<List>[V]
 */
public class Graph {
    public static Logger logger = LoggerFactory.getLogger(Graph.class);

    private int V;
    private List<List<Integer>> adjList;

    public Graph(int v) {
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
        Graph undirectedGraph = new Graph(V);

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
