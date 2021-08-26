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

    public static int convertTreeToSumTree(Node node){
        if(node==null){
            return 0;
        }
        int lSubtreeSum = convertTreeToSumTree(node.left);
        int rSumtreeSum = convertTreeToSumTree(node.right);
        int leftAndRightSubtreeSum = lSubtreeSum + rSumtreeSum;
        int nodeKey = node.key;
        node.key = leftAndRightSubtreeSum;
        return nodeKey+leftAndRightSubtreeSum;
    }
}
