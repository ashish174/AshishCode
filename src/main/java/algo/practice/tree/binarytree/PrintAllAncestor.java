package algo.practice.tree.binarytree;

public class PrintAllAncestor {
    public static boolean printAllAncestor(Node root, int key){
        if(root==null){
            return false;
        }
        if(root.key==key){
            return true;
        }
        boolean lSubTree = printAllAncestor(root.left, key);
        boolean rSubTree = printAllAncestor(root.right, key);
        if(lSubTree || rSubTree){
            System.out.println(root.key);
            return true;
        } else {
            return false;
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
        int key = 8;
        System.out.println("All Ancestor for Key " +key+ " : ");
        printAllAncestor(root, key);
    }
}
