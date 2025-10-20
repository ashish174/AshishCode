package algo.practice.tree.binarytree;

public class SumTree {
    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(-2);
        root.right = new Node(6);
        root.left.left = new Node(8);
        root.left.right = new Node(-4);
        root.right.left = new Node(7);
        root.right.right = new Node(5);
        PrintTree.printBinaryTree2(root);
        convertTreeToSumTree(root);
        PrintTree.printBinaryTree2(root);
    }

    /**
     * Converts a given binary tree to a Sum Tree. A Sum Tree is a binary tree
     * where the value of each node is the sum of the values of its left and right
     * subtrees(don't include the node value). This method modifies the input tree in-place.
     *
     * Node key :
     * The conversion is done recursively by calculating the sum of the left and
     * right subtrees for each node, and then updating the node's key with this sum.
     *
     * SubTree Sum :
     * The original key value is used to calculate the sum of the subtree rooted at
     * this node, which is then returned.
     *
     * @param node the root node of the binary tree to be converted
     * @return the sum of the subtree rooted at the given node
     */
    public static int convertTreeToSumTree(Node node){
        if(node==null){
            return 0;
        }
        int lSubtreeSum = convertTreeToSumTree(node.left);
        int rSumtreeSum = convertTreeToSumTree(node.right);
        int leftAndRightSubtreeSum = lSubtreeSum + rSumtreeSum;
        int nodeKey = node.key;
        //update node value as sum of left and right subtree
        node.key = leftAndRightSubtreeSum;
        // return subtree sum rooted at the node.
        return nodeKey+leftAndRightSubtreeSum;
    }
}
