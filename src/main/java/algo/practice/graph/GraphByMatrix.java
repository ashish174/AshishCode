package algo.practice.graph;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphByMatrix {
    public static Logger logger = LoggerFactory.getLogger(Graph.class);

    private int V;
    private List<List<Integer>> adjMatrix;

    public GraphByMatrix(int v) {
        V = v;
        adjMatrix = new ArrayList<>(V);
        List<Integer> vertexEdgeList = initVertexEdgeList(V);
        for (int i = 0; i < V; i++) {
            adjMatrix.add(i, new ArrayList<>(vertexEdgeList));
        }
    }

    List<Integer> initVertexEdgeList(int V){
        List<Integer> vertexEdgeList = new ArrayList<>(V);
        for(int j = 0; j < V; j++){
            vertexEdgeList.add(0);
        }
        return vertexEdgeList;
    }

    public void addEdge(int u, int v, int weight) {
        adjMatrix.get(u).set(v, 1);
        adjMatrix.get(v).set(u, 1);
    }

    static void printGraph(List<List<Integer>> adjMatrix) {
        StringBuilder edgeListbuilder = new StringBuilder();
        for (int i = 0; i < adjMatrix.size(); i++) {
            edgeListbuilder.append("head");
            for (int j = 0; j < adjMatrix.get(i).size(); j++) {
                edgeListbuilder.append(" -> (" +j+" , "+ adjMatrix.get(i).get(j)+")");
            }
            logger.info("Adjacency Matrix Row for vertex {} : {}", i, edgeListbuilder.toString());
            // reset stringbuilder
            edgeListbuilder.setLength(0);
        }
    }

    static void printGraphMatrix(List<List<Integer>> adjMatrix) {
        StringBuilder edgeListbuilder = new StringBuilder();
        for (int i = 0; i < adjMatrix.size(); i++) {
            edgeListbuilder.append("head");
            for (int j = 0; j < adjMatrix.get(i).size(); j++) {
                edgeListbuilder.append(" -> (" +j+" , "+ adjMatrix.get(i).get(j)+")");
            }
            logger.info("Adjacency Matrix Row for vertex {} : {}", i, edgeListbuilder.toString());
            // reset stringbuilder
            edgeListbuilder.setLength(0);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        GraphByMatrix undirectedGraph = new GraphByMatrix(V);

        // Adding edges one by one
        undirectedGraph.addEdge(0, 1, 1);
        undirectedGraph.addEdge(0, 4, 1);
        undirectedGraph.addEdge(1, 2, 1);
        undirectedGraph.addEdge(1, 3, 1);
        undirectedGraph.addEdge(1, 4, 1);
        undirectedGraph.addEdge(2, 3, 1);
        undirectedGraph.addEdge(3, 4, 1);

        printGraph(undirectedGraph.adjMatrix);

    }
}
