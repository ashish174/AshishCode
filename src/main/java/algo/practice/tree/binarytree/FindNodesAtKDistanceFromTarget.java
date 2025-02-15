package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 * Search first the target Node.
 * Complete the procedure in 2 steps:
 *      1. Find all node at k distance in subtree with target node as root
 *      2. Find all node at k distance in all parent subtree of target node
 *              We Can use a parent pointer in this case.
 *              Try traversing up and subtracting that many steps from distance (distance - steps)
 *              Then for each parent, if (distance - steps) > 0, call utiltiy from Step 1 : Find all node at k distance in subtree with parent -> other child as root
 *          if (distance - steps) ==0, include that parent directly.
 *
 *
 */
public class FindNodesAtKDistanceFromTarget {

    private static Logger logger = LoggerFactory.getLogger(FindNodesAtKDistanceFromTarget.class);

    static List<Integer> nodesValueAtKDistance = new ArrayList<>();
    static Queue<Node> ancestorNodes = new LinkedList<>();

    static Map<Node, NodeMetaData> parentNodeMap = new HashMap<>();
    static Queue<Node> bfsQueue = new LinkedList<>();




    /**
     * Finds all nodes at a given distance from a target node in both the subtree rooted at the target node
     * and its parent subtrees.
     *
     * @param node The target node whose neighbors need to be found.
     * @param distance The number of edges between the target node and the desired neighbor nodes.
     */
    public static void findNodesAtKDistanceFromTarget(Node node, int distance){
        //findNodesAtKDistanceInParentSubtreeFromNode(node, distance);
        findNodesAtKDistanceInSubtreeWithNodeAsRoot(node, distance);

    }

    public static void findNodesAtKDistanceInParentSubtreeFromNode(Node root, Node node, int distance){
          // int h = findDistanceOfNode()

    }

    private static boolean findAncestor(Node root, Node node) {
        if(root==null) {
            return false;
        }
        if(root == node) {
            return true;
        }
        if(findAncestor(root.left, node)){
            ancestorNodes.add(root.left);
        }
        if(findAncestor(root.right, node)){
            ancestorNodes.add(root.right);
        }
        return false;
    }

    public static void findNodesAtKDistanceInSubtreeWithNodeAsRoot(Node node, int distance){
        if(node == null){
            return;
        }
        if(distance==0) {
            nodesValueAtKDistance.add(node.key);
            return;
        }
        findNodesAtKDistanceFromTarget(node.left, distance-1);
        findNodesAtKDistanceFromTarget(node.right, distance-1);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);
        root.right.right.right = new Node(7);

        new FindNodesAtKDistanceFromTarget().findNodesAtKDistanceFromTargetSecondApproach(root, root.right, 2);
        logger.info(bfsQueue.toString());
    }

    /**
     *
     * Approach consider traversing from node as root, and going outward 1 distance in all direction at a time
     * just like BFS. Now left you can traverse, right you can traverse,
     * but for up/parent, you have to find all parents first
     *
     * Finds all nodes at a specified distance 'k' from a given node 'node'.
     * It uses breadth-first search (BFS) algorithm to traverse through the tree structure,
     * starting from the given node and moving outwards until reaching the desired depth 'k'.
     * It is keeping distance from Node into account, and marking visited nodes to avoid revisiting them
     *
     * @param root The root node of the binary tree.
     * @param node The target node from where we want to find nodes at distance 'k'.
     * @param k The number of edges between the target node and the desired neighbor nodes.
     */
    public void findNodesAtKDistanceFromTargetSecondApproach(Node root, Node node, int k){
        populateParents(root, parentNodeMap);
        parentNodeMap.put(root, new NodeMetaData(null, false));

        bfsQueue.add(node);
        //upadted visited flag
        parentNodeMap.get(node).visited=true;
        int levelDistance = 0;
        int levelSize = 0;

        while(!bfsQueue.isEmpty()) {
            levelSize = bfsQueue.size();
            for(int i=0; i <levelSize; i++) {
                Node currNode = bfsQueue.remove();
                enqueueImmediateNextNodesIfNotVisited(currNode, bfsQueue, parentNodeMap);
            }
            levelDistance++;
            if(levelDistance==k){
                return;
            }
        }

    }

    private void enqueueImmediateNextNodesIfNotVisited(Node node, Queue<Node> bfsQueue, Map<Node, NodeMetaData> parentNodeMap) {
        if(node.left!=null && !parentNodeMap.get(node.left).visited) {
            bfsQueue.add(node.left);
            parentNodeMap.get(node.left).visited=true;
        }
        if(node.right!=null && !parentNodeMap.get(node.right).visited) {
            bfsQueue.add(node.right);
            parentNodeMap.get(node.right).visited=true;
        }
        Node parent = parentNodeMap.get(node).parent;
        if(parent!=null && !parentNodeMap.get(parent).visited) {
            bfsQueue.add(parent);
            parentNodeMap.get(parent).visited=true;
        }
    }


    private void populateParents(Node root, Map<Node, NodeMetaData> parentNodeMap) {
        if(root==null) {
            return;
        }
        if(root.left!=null) {
            parentNodeMap.put(root.left, new NodeMetaData(root, false));
            populateParents(root.left, parentNodeMap);
        }
        if(root.right!=null) {
            parentNodeMap.put(root.right, new NodeMetaData(root, false));
            populateParents(root.right, parentNodeMap);
        }
    }

    class NodeMetaData {
        Node parent;
        boolean visited;

        public NodeMetaData(Node parent, boolean visited) {
            this.parent = parent;
            this.visited = visited;
        }
    }

}
