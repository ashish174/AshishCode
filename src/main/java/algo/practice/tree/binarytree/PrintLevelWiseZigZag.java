package algo.practice.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PrintLevelWiseZigZag {
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
