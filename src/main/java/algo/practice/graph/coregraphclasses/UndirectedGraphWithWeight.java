package algo.practice.graph.coregraphclasses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph Usage :- Represent any network like City Roads, telephone network, circuit network,
 * social network like facebook friend network, or linkedin network.
 * <p>
 * V - num of vertices
 * 2 Representations:-
 * 1. Adjacency Matrix :- adj[V][V] - Pro : lookup/searching if edge exist from u-v is easier. Con: Adding new vertex is costly.
 * 2. Adjacency List :-  adj<List>[V] - Pro : less space, adding a new vertex is easier. Con : searching take V time.
 */
public class UndirectedGraphWithWeight {
    public static Logger logger = LoggerFactory.getLogger(UndirectedGraphWithWeight.class);

    public int V;
    public List<List<int[]>> adjList;
    //private List<Integer>[] adjMatrix1;
    public List<Edge> edges;

    public UndirectedGraphWithWeight(int v) {
        V = v;
        adjList = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        edges = new ArrayList<>();
    }

    public void addEdge(int u, int v, int weight) {
        adjList.get(u).add(new int[]{v, weight});
        adjList.get(v).add(new int[]{u, weight});
        edges.add(new Edge(u, v, weight));
    }

    static void printGraph(List<List<int[]>> adjList) {
        StringBuilder edgeListbuilder = new StringBuilder();
        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {
                edgeListbuilder.append(" -> (" + adjList.get(i).get(j)[0] + " , " + adjList.get(i).get(j)[1] + ")");
            }
            logger.info("Adjacency list of vertex {}  {}", i, edgeListbuilder.toString());
            // reset stringbuilder
            edgeListbuilder.setLength(0);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        UndirectedGraphWithWeight undirectedGraphWithWeight = new UndirectedGraphWithWeight(V);

        // Adding edges one by one
        undirectedGraphWithWeight.addEdge(0, 1, 5);
        undirectedGraphWithWeight.addEdge(0, 4, 2);
        undirectedGraphWithWeight.addEdge(1, 2, 3);
        undirectedGraphWithWeight.addEdge(1, 3, 5);
        undirectedGraphWithWeight.addEdge(1, 4, 6);
        undirectedGraphWithWeight.addEdge(2, 3, 4);
        undirectedGraphWithWeight.addEdge(3, 4, 3);

        printGraph(undirectedGraphWithWeight.adjList);

    }
}
