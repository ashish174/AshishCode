package algo.practice.tree.binarytree;

public class FindParentOfLastNode {
    public static Node findParentOfLastNode(Node node){
        Node parent = null;
        while(node != null){
            if(node.right!=null){
                parent = node;
                node = node.right;
            } else if(node.left!=null) {
                parent = node;
                node = node.left;
            } else{
                //Reached Last Node
                System.out.println("Reached last node : "+node.key);
                return parent;
            }
        }
        return parent;
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.right = new Node(40);
        Node parentOfLastNode = findParentOfLastNode(root);
        System.out.println("Parent of last node : "+parentOfLastNode.key);

    }
}
