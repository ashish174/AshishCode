package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Queue;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
 * write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
 *
 * Output:
 * true
 * Example 2:
 *
 * Input:
 * n = 5
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 *
 * Output:
 * false
 * Note:
 * You can assume that no duplicate edges will appear in edges.
 * Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
 *
 * Approach:
 * - For a graph to be a valid tree:
 *   1. It must be fully connected (all nodes reachable).
 *   2. It must contain no cycles.
 *   3. It must have exactly n-1 edges for n nodes.
 * - First, check if the number of edges is n-1.
 * - Build the adjacency list to represent the undirected graph.
 * - Use BFS to traverse from node 0:
 *     - Track visited nodes and parent to distinguish a back edge (cycle).
 *     - If visiting a node already seen (other than from its parent), there is a cycle, so return false.
 * - After traversal, the graph is a tree only if all nodes have been visited (connected).
 */
public class CheckIfValidTree {
    public boolean validTree(int n, int[][] edges) {
        if(n==0 || edges.length != n-1){
            return false;
        }
        Map<Integer, List<Integer>> adjListMap = new HashMap<>();
        //Build adjacency list
        for(int[] edge : edges){
            //add edge to adj list of u
            if(!adjListMap.containsKey(edge[0])){
                adjListMap.put(edge[0], new ArrayList<>());
            }
            adjListMap.get(edge[0]).add(edge[1]);

            //add edge to adj list of v
            if(!adjListMap.containsKey(edge[1])){
                adjListMap.put(edge[1], new ArrayList<>());
            }
            adjListMap.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        //Queue for <Node, Parent>
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, -1});
        visited.add(0);
        while(!queue.isEmpty()){
            int[] pair = queue.remove();
            int u = pair[0];
            int p = pair[1];
            if(adjListMap.containsKey(u)){
                for(int v : adjListMap.get(u)) {
                    //ignore Same undirected edge
                    if(v==p){
                        continue;
                    }
                    //cycle
                    if(visited.contains(v)){
                        return false;
                    }
                    queue.add(new int[] {v, u});
                    visited.add(v);
                }
            }
        }
        return visited.size()==n;

    }
}
