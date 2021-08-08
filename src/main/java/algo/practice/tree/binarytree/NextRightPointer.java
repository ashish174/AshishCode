package algo.practice.tree.binarytree;

import java.util.LinkedList;
import java.util.Queue;

// Incomplete
public class NextRightPointer {
    Queue<Node> queue = new LinkedList<>();
    Node parent = null;

    public static void main(String[] args) {
    }

    public Node connect(Node root) {
        if(root==null){
            return root;
        }
        queue.add(root);
        connectNextPointer(root, 0);
        return null;

    }

    void connectNextPointer(Node node, int height){
        while(!queue.isEmpty()){
            Node currnode = queue.remove();
            if(parent!=null){
                parent.next = currnode;
            }
            parent = currnode;
            Node left = currnode.left;
            Node right = currnode.right;
            queue.add(left);
            queue.add(right);
        }


    }


    class Node {
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
