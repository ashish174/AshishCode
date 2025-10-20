package algo.practice.tree.binarytree;

// Not Working
public class TreeSuccessor {
    static Node tmp = null;
    static Node parent = null;
    public static Node getPredecessor(Node node){
        if(node==null){
            return null;
        }
        //Find in left subtree
        if(node.left!=null){
            node = node.left;
            while(node.right!=null){
                node = node.right;
            }
            return node;
        }else {
            //find first parent for which it is right child
            return null;

        }

    }

    public static Node getSuccessor(Node root, Node node){
        if(node == null){
            return null;
        }
        //Find in right subtree
        if(node.right!=null){
            node = node.right;
            while(node.left!=null){
                node = node.left;
            }
            return node;
        } else {
            //find first parent for which it is left child
            if(root!=node){
                return findSuccessorFromParent(root, node);
            }
        }
        return null;
    }

    public static Node findSuccessorFromParent(Node root, Node node) {
        while (root != null) {
            if (root.key != node.key) {
                if (root.left != null) {
                    parent = root;
                    root = root.left;
                } else if (root.right != null) {
                    root = root.right;
                }
            } else {
                return parent;
            }
        }
        return null;
    }

    public static Node findInorderSuccessor(Node root, Node prev, Node node){
        Node lNode = findInorderSuccessor(root.right, root, node);
        if(root.key==node.key){
            return prev;
        }
        Node rNode = findInorderSuccessor(root.left, root, node);
        return null;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(6);
        System.out.println("Inorder Successor for 5 :- " +getSuccessor(root, root.left.right));
    }
}
