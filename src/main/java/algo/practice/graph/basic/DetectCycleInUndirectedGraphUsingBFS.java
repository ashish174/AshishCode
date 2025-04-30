package algo.practice.graph.basic;

import algo.practice.graph.coregraphclasses.GraphVisualizer;
import algo.practice.graph.coregraphclasses.UndirectedGraph;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class DetectCycleInUndirectedGraphUsingBFS {
    /**
     *
     * BFS is useful for cycle detection in an undirected graph because it explores level by level,
     * ensuring that each node is visited in the shortest possible way. It is more memory-efficient than DFS for large graphs.
     *
     * A cycle is detected if we encounter a node that has already been visited before being dequeued,
     * meaning it has been reached through a different path.
     *
     * @param undirectedGraph
     * @return
     */
    public static void detectCycleUsingBFS(UndirectedGraph undirectedGraph) {
        int[] visited = new int[undirectedGraph.V];
        bfsUtil(undirectedGraph, 0, visited);
        for(int u = 0; u < undirectedGraph.V; u++) {
            if(visited[u]!=1) {
                bfsUtil(undirectedGraph, u, visited);
            }
        }
    }

    public static void bfsUtil(UndirectedGraph undirectedGraph, int st_vertex, int[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[st_vertex] = 1;
        queue.add(st_vertex);
        while(!queue.isEmpty()) {
            Integer u = queue.poll();
            for(int v : undirectedGraph.adjList.get(u)) {
                if(visited[v]==1) {
                    log.info("Found a cycle using backedge from {} to {}", v, u);
                } else if (visited[v]==0) { // dont want to add processed node again i.e. visited[u] = 2
                    queue.add(v);
                    visited[v] = 1;
                }
            }
            visited[u] = 2;
        }
    }

    public static void main(String[] args){
        UndirectedGraph undirectedGraph = new UndirectedGraph(5);
        undirectedGraph.addEdge(1, 0);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(2, 1);
        undirectedGraph.addEdge(0, 3);
        undirectedGraph.addEdge(3, 4);
        undirectedGraph.addEdge(2, 3);
        log.info("#############CHECK Cycle for Undirected Graph#############");
        new GraphVisualizer(undirectedGraph).draw();
        detectCycleUsingBFS(undirectedGraph);
    }







}
