package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CheckIfAllLeavesAtSameLevel {
    private static Logger logger = LoggerFactory.getLogger(CheckIfAllLeavesAtSameLevel.class);

    // Not a good approach. This can be replaced with a AtomicInteger in recursive function
    private static int prev_height = -1;

    public static void main(String[] args) {
        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(7);
        //root.right.right = new Node(1);
        PrintTree.printBinaryTree2(root);
        logger.info("CheckIfAllLeavesAtSameLevel : {}", checkIFAllLeavesAtSameLevel(root, 0));
    logger.info(
        "CheckIfAllLeavesAtSameLevel_V2 : {}",
        checkIFAllLeavesAtSameLevelV2(root, 0, new AtomicInteger(0)));
    }

    /**
     * Checks whether all leaves of a binary tree are at the same level.
     *
     * This method traverses the binary tree recursively and keeps track of the height of each leaf node.
     * If it encounters a leaf node, it checks if its height matches the previously recorded height.
     * If not, it immediately returns false. Otherwise, it continues checking other nodes.
     *
     * @param root the root node of the binary tree
     * @param height the current depth or level of the node being checked
     * @return true if all leaves are at the same level, false otherwise
     *
     * Other approach
     * 1. find max leaf distance, min leaf distance. Both must be equal
     * 2. Do level order traversal. Once you encountered first leaf, ensure all leaf must be in same level
     *
     */
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

  /**
   * Checks whether all leaves of a binary tree are at the same level using recursion.
   *
   * This method uses a recursive approach to traverse the binary tree and verify if all leaves are at the same level.
   * It utilizes an {@link AtomicInteger} object to keep track of the level of the first leaf node encountered.
   * You need to pass a wrapper object with int/Integer leaflevel to keep track of the level of the first leaf node encountered.
   * Don't use Integer leaflevel directly, as Integer(& String) is immutable, and changing the value will reassign to a new address.
   *
   * @param root the root node of the binary tree
   * @param nodeHeight the current depth or level of the node being checked
   * @param leafLevel an {@link AtomicInteger} object used to store the level of the first leaf node encountered
   * @return true if all leaves are at the same level, false otherwise
   */
  public static boolean checkIFAllLeavesAtSameLevelV2(
      Node root, int nodeHeight, AtomicInteger leafLevel) {
        if(root == null) {
            return true;
        }
        if(root.left == null && root.right == null) {
            if(leafLevel.get() != 0) {
                return leafLevel.get() == nodeHeight;
            } else {
                leafLevel.set(nodeHeight);
                return true;
            }
        }
        return checkIFAllLeavesAtSameLevelV2(root.left, nodeHeight+1, leafLevel)
                && checkIFAllLeavesAtSameLevelV2(root.right, nodeHeight + 1, leafLevel);
    }
}
