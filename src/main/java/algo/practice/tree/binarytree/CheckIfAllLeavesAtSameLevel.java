package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckIfAllLeavesAtSameLevel {
    private static Logger logger = LoggerFactory.getLogger(CheckIfAllLeavesAtSameLevel.class);

    private static int prev_height = -1;

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(7);
        //root.right.right = new Node(1);
        PrintTree.printBinaryTree2(root);
        logger.info("CheckIfAllLeavesAtSameLevel : {}", checkIFAllLeavesAtSameLevel(root, 0));
    }

    public static boolean checkIFAllLeavesAtSameLevel(Node root, int height) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            if(prev_height==-1) {
                prev_height = height;
                return true;
            } else {
                return prev_height==height;
            }
        }
        return checkIFAllLeavesAtSameLevel(root.left, height+1) && checkIFAllLeavesAtSameLevel(root.right, height+1);
    }
}
