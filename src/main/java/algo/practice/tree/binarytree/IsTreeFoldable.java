package algo.practice.tree.binarytree;

public class IsTreeFoldable {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(7);
        root.left.right = new Node(20);
        root.right = new Node(15);
        root.right.left = new Node(40);
        System.out.println("Check if tree is foldable :" + checkIfTreeFoldable(root));
    }

    /**
     * Checks whether a binary tree is foldable or not. A binary tree is considered
     * foldable if its left subtree is a mirror image of its right subtree.
     *
     * @param node the root node of the binary tree
     * @return true if the tree is foldable, otherwise false
     *
     * To check if a tree is foldable, we can check if its left subtree is a mirror image of right subtree
     */
    public static boolean checkIfTreeFoldable(Node node) {
        if (node == null) {
            return true;
        }
        return checkIfSubtreeAreMirrorImage(node.left, node.right);
    }

    public static boolean checkIfSubtreeAreMirrorImage(Node fNode, Node sNode) {
        if (fNode == null && sNode == null) {
            return true;
        }
        if (fNode == null || sNode == null) {
            return false;
        }
        return checkIfSubtreeAreMirrorImage(fNode.left, sNode.right) && checkIfSubtreeAreMirrorImage(fNode.right, sNode.left);
    }
}
