package algo.practice.tree.binarytree;


import java.util.LinkedList;
import java.util.Queue;

public class FindMaxWidth {

    /**
     * Calculates the maximum width of a binary tree using breadth-first search (BFS).
     * In a binary tree, the maximum width is defined as the maximum number of nodes
     * present in any single level of the tree.
     *
     * @param root the root node of the binary tree
     * @return the maximum width of the binary tree
     */
    public static int findWidth(Node root){
        Queue<Node> queue = new LinkedList<>();
        int maxWidth = 0;
        int level = 0;
        queue.add(root);
        while(!queue.isEmpty()){
            level++;
            int levelWidth = queue.size();
            System.out.println("TreeWidth at level "+level+" : "+levelWidth);
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

    public static int findWidthRecursively(Node root, int level){
        return 0;
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
        System.out.println("Tree Width : "+findWidth(root));
    }
}
