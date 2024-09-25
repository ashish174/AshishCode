package algo.practice.tree.binarytree;

import java.util.ArrayList;
import java.util.List;

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

    static List<Integer> nodesValueAtKDistance = new ArrayList<>();

    public static void findNodesAtKDistanceFromTarget(Node node, int distance){
        findNodesAtKDistanceInParentSubtreeFromNode(node, distance);
        findNodesAtKDistanceInSubtreeWithNodeAsRoot(node, distance);

    }

    public static void findNodesAtKDistanceInParentSubtreeFromNode(Node node, int distance){
          // int h = findDistanceOfNode()

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
        findNodesAtKDistanceFromTarget(root, 3);
        System.out.println("nodesValueAtKDistance : "+nodesValueAtKDistance.toString());
    }
}
