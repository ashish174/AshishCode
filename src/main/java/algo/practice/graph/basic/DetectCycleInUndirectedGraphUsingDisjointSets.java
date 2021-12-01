package algo.practice.graph.basic;

import algo.practice.graph.UndirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DetectCycleInUndirectedGraphUsingDisjointSets {
    public static boolean detectCycleUsingDisjointSets(UndirectedGraph undirectedGraph){
        List<Set<Integer>> disjointSets = new ArrayList<>();
        return false;
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

    }
}
