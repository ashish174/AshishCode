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
        if (CollectionUtils.isEmpty(integerList)) {
            return null;
        }

        Node root = new Node(integerList.get(0));
        Queue<IndexedNode> queue = new LinkedList<>();
        queue.add(new IndexedNode(root, 0));

        int size = integerList.size();

        while (!queue.isEmpty()) {
            IndexedNode current = queue.remove();
            Node currNode = current.node;
            int i = current.index;

            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            if (leftIndex < size) {
                Node left = new Node(integerList.get(leftIndex));
                currNode.left = left;
                queue.add(new IndexedNode(left, leftIndex));
            }

            if (rightIndex < size) {
                Node right = new Node(integerList.get(rightIndex));
                currNode.right = right;
                queue.add(new IndexedNode(right, rightIndex));
            }
        }

        return root;
    }

    private static class IndexedNode {
        Node node;
        int index;

        IndexedNode(Node node, int index) {
            this.node = node;
            this.index = index;
        }
    }


    private static Node convertListtoCompleteBinaryTreeV2(List<Integer> integerList) {
        if (CollectionUtils.isEmpty(integerList)) {
            return null;
        }
        return buildTree(integerList, 0);
    }

    private static Node buildTree(List<Integer> list, int index) {
        if (index >= list.size()) {
            return null;
        }
        Node node = new Node(list.get(index));
        node.left = buildTree(list, 2 * index + 1);
        node.right = buildTree(list, 2 * index + 2);
        return node;
    }
}
