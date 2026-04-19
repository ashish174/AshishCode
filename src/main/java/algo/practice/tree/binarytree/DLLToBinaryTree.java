package algo.practice.tree.binarytree;

/**
 * Utility class to convert a Doubly Linked List (DLL) into a Binary Tree.
 * <p>
 * This implementation assumes that the DLL represents the level-order (breadth-first)
 * traversal of a complete or almost-complete binary tree. That is,
 * each node in the list corresponds to a tree node in level-order:
 * <ul>
 *   <li>List index {@code i} → Tree node at index {@code i}</li>
 *   <li>Left child index = {@code 2 * i + 1}</li>
 *   <li>Right child index = {@code 2 * i + 2}</li>
 * </ul>
 * <p>
 * Example DLL (values): {@code 1 <-> 2 <-> 3 <-> 4 <-> 5} will be
 *         1
 *       /   \ converted into:
 *  * <pre>
 *      2     3
 *     / \
 *    4   5
 * </pre>
 * where the binary tree is represented using the existing {@link Node} class
 * (with {@code left} and {@code right} references).
 */
public class DLLToBinaryTree {

    /**
     * Doubly Linked List node representation.
     * <p>
     * This node is used only as an intermediate structure. The final binary tree
     * will be built using the {@link Node} class already used in this package.
     */
    public static class DLLNode {
        int data;
        DLLNode prev;
        DLLNode next;

        public DLLNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    /**
     * Converts a doubly linked list, whose nodes represent a level-order traversal,
     * into a binary tree built with {@link Node} objects.
     * <p>
     * The conversion is index-based:
     * <ul>
     *   <li>The head of the DLL is treated as index {@code 0} (root of the tree).</li>
     *   <li>For a node at conceptual index {@code i}, its left child (if present)
     *       is the node at index {@code 2 * i + 1}, and its right child at
     *       {@code 2 * i + 2}.</li>
     * </ul>
     * <p>
     * Time Complexity: O(n) – we walk the list once to collect nodes, then once
     * to wire up the tree.
     * <br>
     * Space Complexity: O(n) – for the array used to hold the DLL nodes by index.
     *
     * @param head the head of the doubly linked list representing the tree in level order;
     *             may be {@code null}
     * @return the root of the constructed binary tree, or {@code null} if the list is empty
     */
    public static Node convertDLLToBinaryTree(DLLNode head) {
        if (head == null) {
            return null;
        }

        // First, collect DLL nodes in an indexed structure so we can apply
        // the complete-binary-tree index relationships (2*i+1, 2*i+2).
        java.util.List<DLLNode> dllNodes = new java.util.ArrayList<>();
        DLLNode current = head;
        while (current != null) {
            dllNodes.add(current);
            current = current.next;
        }

        int n = dllNodes.size();
        if (n == 0) {
            return null;
        }

        // Create an array of tree Nodes corresponding to the DLL nodes.
        Node[] treeNodes = new Node[n];
        for (int i = 0; i < n; i++) {
            treeNodes[i] = new Node(dllNodes.get(i).data);
        }

        // Wire up left and right children following complete binary tree rules:
        // left child index = 2*i + 1, right child index = 2*i + 2
        for (int i = 0; i < n; i++) {
            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            if (leftIndex < n) {
                treeNodes[i].left = treeNodes[leftIndex];
            }
            if (rightIndex < n) {
                treeNodes[i].right = treeNodes[rightIndex];
            }
        }

        // The first element becomes the root of the binary tree.
        return treeNodes[0];
    }

    /**
     * Example usage and simple demonstration.
     * <p>
     * Builds a DLL: 1 &lt;-&gt; 2 &lt;-&gt; 3 &lt;-&gt; 4 &lt;-&gt; 5 &lt;-&gt; 6,
     * converts it to a binary tree and prints it using {@link PrintTree#printBinaryTree2(Node)}.
     */
    public static void main(String[] args) {
        // Build a sample doubly linked list: 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6
        DLLNode head = new DLLNode(1);
        head.next = new DLLNode(2);
        head.next.prev = head;

        head.next.next = new DLLNode(3);
        head.next.next.prev = head.next;

        head.next.next.next = new DLLNode(4);
        head.next.next.next.prev = head.next.next;

        head.next.next.next.next = new DLLNode(5);
        head.next.next.next.next.prev = head.next.next.next;

        head.next.next.next.next.next = new DLLNode(6);
        head.next.next.next.next.next.prev = head.next.next.next.next;

        // Convert DLL to binary tree
        Node root = convertDLLToBinaryTree(head);

        // Print the constructed tree (assuming PrintTree is available in your project)
        PrintTree.printBinaryTree2(root);
    }
}

