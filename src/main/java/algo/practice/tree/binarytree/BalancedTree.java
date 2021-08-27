package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancedTree {
    private static Logger logger = LoggerFactory.getLogger(BalancedTree.class);


    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        //root.right = new Node(7);
        //root.right.right = new Node(1);
        PrintTree.printBinaryTree2(root);
        logger.info("CheckIfAllLeavesAtSameLevel : {}", checkIfTreeIsBalanced(root));
    }


    public static boolean checkIfTreeIsBalancedCopy(Node root, int height) {
        if(root==null){
            return true;
        }

        return true;
    }

    /**
     * 0(n^2) complexity
     * @param root
     * @return
     */
    public static boolean checkIfTreeIsBalanced(Node root){
        if(root==null){
            return true;
        }
        int lTreeHeight = getTreeHeight(root.left);
        int rTreeHeight = getTreeHeight(root.right);
        return Math.abs(lTreeHeight-rTreeHeight)<=1 && checkIfTreeIsBalanced(root.left) && checkIfTreeIsBalanced(root.right);
    }

    private static int getTreeHeight(Node node) {
        if(node==null){
            return 0;
        }
        return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
    }





    }
