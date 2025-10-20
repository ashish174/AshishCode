package algo.practice.tree.binarytree;

public class TreeCloning {

    /**
     * Creates a deep copy of a binary tree rooted at the specified node.
     * <p>
     * The method recursively clones each node in the tree, ensuring that a
     * completely new tree structure is created such that no references in the
     * new tree point to nodes in the original tree.
     *
     * @param node the root node of the binary tree to clone; may be {@code null}
     * @return the root node of the cloned binary tree, or {@code null} if the input node is {@code null}
     */
    public static Node cloneTree(Node node){
        if(node == null){
            return null;
        }
        Node clonedNode = clone(node);
        clonedNode.left = cloneTree(node.left);
        clonedNode.right = cloneTree(node.right);
        return clonedNode;
    }

    private static Node clone(Node node) {
        return new Node(node.key);
    }

    public static void main(String[] args){
        Node root = new Node(10);
        root.left = new Node(-2);
        root.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(-4);
        root.right.left = new Node(7);
        root.right.right = new Node(5);
        PrintTree.printBinaryTree2(root);
        Node clonedRoot = cloneTree(root);
        PrintTree.printBinaryTree2(clonedRoot);
    }
}
