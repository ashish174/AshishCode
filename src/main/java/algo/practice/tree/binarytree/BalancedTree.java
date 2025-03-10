package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancedTree {
  private static Logger logger = LoggerFactory.getLogger(BalancedTree.class);

  public static void main(String[] args) {
    Node root = new Node(12);
    root.left = new Node(5);
    root.left.left = new Node(3);
    root.right = new Node(7);
    root.right.right = new Node(1);
    PrintTree.printBinaryTree2(root);
    logger.info("CheckIfAllLeavesAtSameLevel : {}", checkIfTreeIsBalanced(root));
  }

  /**
   * Checks whether a binary tree is balanced or not. A binary tree is considered balanced if the
   * absolute difference between the heights of its left and right subtrees does not exceed 1 at any
   * node.
   *
   * <p>This method uses a recursive approach to traverse the tree and calculate the height of each
   * subtree. It has a time complexity of O(n^2) due to the repeated calculation of subtree heights.
   *
   * @param root the root node of the binary tree
   * @return true if the tree is balanced, false otherwise
   */
  public static boolean checkIfTreeIsBalanced(Node root) {
    if (root == null) {
      return true;
    }
    int lTreeHeight = getTreeHeight(root.left);
    int rTreeHeight = getTreeHeight(root.right);
    return Math.abs(lTreeHeight - rTreeHeight) <= 1
        && checkIfTreeIsBalanced(root.left)
        && checkIfTreeIsBalanced(root.right);
  }

  private static int getTreeHeight(Node node) {
    if (node == null) {
      return 0;
    }
    return 1 + Math.max(getTreeHeight(node.left), getTreeHeight(node.right));
  }
}
