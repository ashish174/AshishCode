package algo.practice.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PrintLevelWiseZigZag {
    /**
     * Prints the nodes of a binary tree level-wise in a zig-zag pattern.
     *
     * The zig-zag pattern means that the nodes at even levels are printed from left to right,
     * and the nodes at odd levels are printed from right to left.
     *
     * @param root the root node of the binary tree
     */
    public static void printlevelWiseZigZag(Node root){
        Queue<Node> queue = new LinkedList<>();
        int maxWidth = 0;
        int level = 0;
        queue.add(root);
        while(!queue.isEmpty()){
            level++;
            int levelWidth = queue.size();
            if(level%2==0){
                printLevelElem(queue);
            } else {
                printLevelElemInReverse(queue);
            }
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
    }

    private static void printLevelElemInReverse(Queue<Node> queue) {
        Stack<Node> stack = new Stack<>();
        for(Node node : queue){
            stack.push(node);
        }
        while(!stack.empty()){
            Node poppedNode = stack.pop();
            System.out.print(poppedNode.key +" ");
        }
        System.out.println();
    }

    private static void printLevelElem(Queue<Node> queue) {
        for(Node node : queue){
            System.out.print(node.key +" ");
        }
        System.out.println();
    }


    public static void printLevelWiseZigZagV2(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Collect current level nodes
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                currentLevel.add(node.key);

                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            // Print based on direction
            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }

            for (int val : currentLevel) {
                System.out.print(val + " ");
            }

            System.out.println();

            leftToRight = !leftToRight;
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
        printlevelWiseZigZag(root);
    }

}
