package algo.practice.tree.binarytree;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

// Incomplete
@Slf4j
public class NextRightPointer {
    Queue<Node> queue = new LinkedList<>();
    Node parent = null;

    public static void main(String[] args) {
        NextRightPointer nextRightPointer = new NextRightPointer();
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);
        root.right.right.right = new Node(7);
        nextRightPointer.populateNextRightPointer(root);
        nextRightPointer.printUsingNextPointer(root);
    }


    public void populateNextRightPointer(Node root) {
        if(root==null){
            return;
        }
        int levelSize = 0;
        Node prevElement = null;
        queue.add(root);
        while(!queue.isEmpty()) {
            levelSize = queue.size();
            for(int i=0; i<levelSize; i++) {
                Node currElement = queue.remove();
                if(prevElement !=null) {
                    prevElement.next = currElement;
                }
                if(currElement.left!=null) {
                    queue.add(currElement.left);
                }
                if(currElement.right!=null) {
                    queue.add(currElement.right);
                }
                prevElement = currElement;
            }
        }

    }

    public void printUsingNextPointer(Node root) {
        while(root!=null) {
            log.info("{}",root.val);
            root = root.next;
        }
    }


    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
