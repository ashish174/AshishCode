package algo.practice.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for converting a binary tree into a Binary Search Tree (BST)
 * without changing the structure (shape) of the original tree.
 * <p>
 * The algorithm works in three main steps:
 * <ol>
 *   <li>Traverse the original tree (in-order) and collect all node keys into a list.</li>
 *   <li>Sort that list so the keys are in ascending order.</li>
 *   <li>Traverse the tree again (in-order) and overwrite each node's key with the
 *       next value from the sorted list.</li>
 * </ol>
 * <p>
 * Because an in-order traversal of a BST yields sorted keys, reassigning keys in
 * in-order with the sorted values ensures the resulting tree satisfies the BST property
 * while preserving the original tree structure.
 * <p>
 * Time Complexity: O(n log n) due to sorting of n keys.<br>
 * Space Complexity: O(n) for storing the keys in a list.
 */
public class convertTreeToBST {

    /**
     * Converts the given binary tree into a Binary Search Tree (BST) in-place,
     * preserving the original structure (shape) of the tree.
     * <p>
     * After this operation:
     * <ul>
     *   <li>The set of keys in the tree remains the same.</li>
     *   <li>The arrangement of nodes (left/right pointers) is unchanged.</li>
     *   <li>The keys are rearranged such that the tree satisfies the BST property:
     *       for any node, all keys in the left subtree are smaller, and all keys
     *       in the right subtree are greater or equal (depending on your convention).</li>
     * </ul>
     *
     * @param root the root of the binary tree to convert; may be {@code null}
     */
    public static void convertToBST(Node root) {
        if (root == null) {
            return;
        }

        // Step 1: Collect all keys via in-order traversal.
        List<Integer> values = new ArrayList<>();
        storeInOrder(root, values);

        // Step 2: Sort the collected keys.
        Collections.sort(values);

        // Step 3: Reassign keys back to the tree in in-order.
        int[] index = {0}; // use single-element array as mutable integer
        fillInOrder(root, values, index);
    }

    /**
     * Performs an in-order traversal of the tree and stores node keys into the given list.
     * <p>
     * In-order: left subtree → current node → right subtree.
     *
     * @param node   current node in the traversal
     * @param values list where keys are collected; must not be {@code null}
     */
    private static void storeInOrder(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        storeInOrder(node.left, values);
        values.add(node.key);
        storeInOrder(node.right, values);
    }

    /**
     * Performs an in-order traversal of the tree and overwrites each node's key
     * with the next value from the provided sorted list.
     *
     * @param node   current node in the traversal
     * @param values sorted list of keys to assign
     * @param index  single-element array used as a mutable index into {@code values}
     */
    private static void fillInOrder(Node node, List<Integer> values, int[] index) {
        if (node == null) {
            return;
        }

        fillInOrder(node.left, values, index);

        // Assign the next sorted value to this node.
        node.key = values.get(index[0]);
        index[0]++;

        fillInOrder(node.right, values, index);
    }

    /**
     * Demonstration of converting an arbitrary binary tree into a BST.
     * <p>
     * Builds a sample tree, prints it, converts to BST in-place, then prints again.
     */
    public static void main(String[] args) {
        /*
         * Example tree (not a BST):
         *
         *         10
         *        /  \
         *      30    15
         *     /       \
         *    20        5
         */

        Node root = new Node(10);
        root.left = new Node(30);
        root.right = new Node(15);
        root.left.left = new Node(20);
        root.right.right = new Node(5);

        System.out.println("Original tree:");
        PrintTree.printBinaryTree2(root);

        convertToBST(root);

        System.out.println("Tree after converting to BST (structure preserved):");
        PrintTree.printBinaryTree2(root);
    }
}

