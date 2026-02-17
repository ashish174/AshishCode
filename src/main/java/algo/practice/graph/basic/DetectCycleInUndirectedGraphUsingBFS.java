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
     * Note:
     * THis is wrong as we are not using parent pointer.
     * Also, we do not need a 3-state visited flag for cycle detection in an undirected graph using BFS.
     * For correct code, check detectCycleUsingBFSV2
     *
     * BFS is useful for cycle detection in an undirected graph because it explores level by level,
     * ensuring that each node is visited in the shortest possible way. It is more memory-efficient than DFS for large graphs.
     *
     * A cycle in undirected graph is detected if we encounter a node that has already been visited before being dequeued,
     * meaning it has been reached through a different path.
     *
     * @param undirectedGraph
     * @return
     */
    public static void detectCycleUsingBFSV1(UndirectedGraph undirectedGraph) {
        int[] visited = new int[undirectedGraph.V];
        bfsUtilV1(undirectedGraph, 0, visited);
        for(int u = 0; u < undirectedGraph.V; u++) {
            if(visited[u]!=1) {
                bfsUtilV1(undirectedGraph, u, visited);
            }
        }
    }

    public static void bfsUtilV1(UndirectedGraph undirectedGraph, int st_vertex, int[] visited) {
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


    /**
     * Returns true if a cycle exists in the given UndirectedGraph using BFS.
     *
     * @param undirectedGraph the graph to check for cycles
     * @return true if a cycle exists, false otherwise
     */
    public static boolean detectCycleUsingBFSV2(UndirectedGraph undirectedGraph) {
        int V = undirectedGraph.V;
        int[] visited = new int[V]; // 0 = unvisited, 1 = visited

        // Traverse every component
        for (int start = 0; start < V; start++) {
            if (visited[start] == 0) {
                if (bfsUtilV2(undirectedGraph, start, visited)) {
                    return true; // Cycle detected
                }
            }
        }
        return false;
    }

    /**
     * Performs BFS from a start vertex, checking for a cycle.
     * Tracks parent for each node in the queue.
     *
     * @param undirectedGraph the undirected graph
     * @param start starting vertex for this component
     * @param visited visited[] array
     * @return true if a cycle is found from this component
     */
    private static boolean bfsUtilV2(UndirectedGraph undirectedGraph, int start, int[] visited) {
        //Hold node and its parent
        Queue<NodeParent> queue = new LinkedList<>();
        visited[start] = 1;
        queue.add(new NodeParent(start, -1)); // Start node has no parent

        while (!queue.isEmpty()) {
            NodeParent current = queue.poll();
            int u = current.node;
            int parent = current.parent;

            for (int v : undirectedGraph.adjList.get(u)) {
                if (visited[v] == 0) {
                    // If not visited, visit and add to queue with current node as parent
                    visited[v] = 1;
                    queue.add(new NodeParent(v, u));
                } else if (v != parent) {
                    // If visited and not parent, found a back edge = cycle
                    return true;
                }
            }
        }
        return false;
    }

    // Helper class for queue entries: node and its parent
    static class NodeParent {
        int node;
        int parent;
        NodeParent(int node, int parent) {
            this.node = node;
            this.parent = parent;
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
        detectCycleUsingBFSV1(undirectedGraph);
    }







}
