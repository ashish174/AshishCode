package algo.practice.tree.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.commons.collections4.CollectionUtils;

/**
 * A Complete tree is one where all internal nodes are complete. Leaves are at last level only.
 * Use a queue to store each node in fifo and then process its each next level
 * lchild = 2i+1
 * rchild = 2i+2
 */
public class LinkListToCompleteBinaryTree {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(10, 12, 15, 25, 30, 36);
        Node root = convertListtoCompleteBinaryTree(integerList);
        PrintTree.printBinaryTree2(root);

    }


    /**
     * Converts a given list of integers into a complete binary tree structure using a queue-based approach.
     * Each element from the list becomes a node in the tree, and the relationship between nodes is determined
     * based on their index position in the list. For example, the left child of node at index 'i' will have an
     * index of '2*i+1', while the right child will have an index of '2*i+2'.
     *
     * @param integerList An ArrayList containing Integer values representing the nodes of the binary tree.
     * @return The root Node of the constructed complete binary tree.
     */
    private static Node convertListtoCompleteBinaryTree(List<Integer> integerList) {
        if(CollectionUtils.isEmpty(integerList)){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        int i = 0;
        int size = integerList.size();
        Integer elem = integerList.get(i);
        Node root = new Node(elem);
        queue.add(root);
        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            Node leftNode = null;
            Node rightNode = null;
            if(2 * i + 1 < size){
                leftNode = new Node(integerList.get(2 * i + 1));
                currNode.left = leftNode;
                queue.add(leftNode);
            }
            if(2 * i + 2 < size){
                rightNode = new Node(integerList.get(2 * i + 2));
                currNode.right = rightNode;
                queue.add(rightNode);
            }
            i++;
        }
        return root;
    }
}
