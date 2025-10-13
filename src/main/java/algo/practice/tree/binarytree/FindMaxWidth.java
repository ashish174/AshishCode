package algo.practice.tree.binarytree;


import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class FindMaxWidth {

    /**
     * Calculates the maximum width of a binary tree using breadth-first search (BFS).
     * In a binary tree, the maximum width is defined as the maximum number of nodes
     * present in any single level of the tree.
     *
     * @param root the root node of the binary tree
     * @return the maximum width of the binary tree
     */
    public static int findMaxWidth(Node root){
        Queue<Node> queue = new LinkedList<>();
        int maxWidth = 0;
        int level = 0;
        queue.add(root);
        while(!queue.isEmpty()){
            level++;
            int levelWidth = queue.size();
            log.info("TreeWidth at level "+level+" : "+levelWidth);
            maxWidth = Math.max(maxWidth, levelWidth);
            for(int i=0; i < levelWidth; i++){
                Node node = queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        return maxWidth;
    }

    /**
     * Calculates the maximum width of the binary tree.
     *
     * @param root the root node of the binary tree
     * @return the maximum width among all levels
     */
    public static int getMaxWidthRecursively(Node root) {
        int maxWidth = 0;
        int height = getHeight(root);
        for (int i = 1; i <= height; i++) {
            int width = findWidthRecursively(root, i);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    /**
     * Returns the height of the binary tree.
     *
     * @param node the root node
     * @return the height (root is at height 1)
     */
    public static int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }


    /**
     * Returns the number of nodes at a given level (recursively).
     * (Same as previously described)
     */
    public static int findWidthRecursively(Node root, int level) {
        if (root == null) {
            return 0;
        }

        if (level == 1) { // reached to desired level, so increment the count
            return 1;
        } else {
            return findWidthRecursively(root.left, level - 1)
                    + findWidthRecursively(root.right, level - 1);
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);
        root.right.right.right = new Node(7);
        PrintTree.printBinaryTree2(root);
        log.info("Tree Width : "+ findMaxWidth(root));
        log.info("Tree Width Recursively : "+getMaxWidthRecursively(root));
    }
}
