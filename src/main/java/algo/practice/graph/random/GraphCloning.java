package algo.practice.graph.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Given a node in a connected undirected graph, return a deep copy of the graph.
 *
 * Each node in the graph contains an integer value and a list of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 * For simplicity, nodes values are numbered from 1 to n, where n is the total number of nodes in the graph.
 * The index of each node within the adjacency list is the same as the node's value (1-indexed).
 * The input node will always be the first node in the graph and have 1 as the value.
 *
 * Approach:
 * - Use BFS (Breadth-First Search) traversal to traverse the graph starting from the given node.
 * - Maintain a map from original nodes to their clones to avoid duplicating nodes and to preserve neighbor relationships.
 * - For each node, clone it and its neighbors if not already cloned, and add appropriate neighbors to the cloned node.
 * - Ensures the deep copy has the same structure as the original, with all nodes and edges copied.
 * - Handles cycles and connected components correctly.
 */
public class GraphCloning {

    public Node cloneGraph(Node node) {
        if(node==null){
            return null;
        }

        // Map to track original node -> cloned node
        Map<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // Create clone of the starting node and add to map
        Node clonedRoot = new Node(node.val);
        cloneMap.put(node, clonedRoot);
        queue.add(node);

        // BFS traversal
        while (!queue.isEmpty()) {
            Node current = queue.remove();

            // Process all neighbors
            for (Node neighbor : current.neighbors) {
                // If neighbor hasn't been cloned yet, create it
                if (!cloneMap.containsKey(neighbor)) {
                    cloneMap.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }

                // Add the cloned neighbor to the cloned node's neighbor list
                cloneMap.get(current).neighbors.add(cloneMap.get(neighbor));
            }
        }

        return clonedRoot;
    }

    private class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
