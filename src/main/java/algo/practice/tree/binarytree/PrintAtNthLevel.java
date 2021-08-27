package algo.practice.tree.binarytree;

public class PrintAtNthLevel {

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(7);
        root.left.right = new Node(20);
        root.right = new Node(15);
        root.right.left = new Node(40);
        printAtNthLevel(root, 3);
    }

    public static void printAtNthLevel(Node node, int level){
        if(node == null || level < 0){
            return;
        }
        if(level == 0){
            System.out.print(node.key +" ");
        }
        printAtNthLevel(node.left, level-1);
        printAtNthLevel(node.right, level-1);
    }
}
