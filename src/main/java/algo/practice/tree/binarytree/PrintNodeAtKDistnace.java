package algo.practice.tree.binarytree;

public class PrintNodeAtKDistnace {
    public static void printAtKDistanceFromRoot(Node root, int distance){
        if(root==null){
            return;
        }
        if(distance==0){
            System.out.print(root.key+ " ");
        }
        printAtKDistanceFromRoot(root.left, distance-1);
        printAtKDistanceFromRoot(root.right, distance-1);
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
        int dist = 2;
        System.out.print("Node at distance " +dist+ " : ");
        printAtKDistanceFromRoot(root, dist);
    }
}
