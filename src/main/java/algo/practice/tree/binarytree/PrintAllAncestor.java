package algo.practice.tree.binarytree;

public class PrintAllAncestor {
    /**
     * Prints all the ancestors of a node with the given key in a binary tree.
     * The ancestors are printed to the console in the order they are encountered
     * during the traversal from the node to the root.
     *
     * @param root the root node of the binary tree
     * @param key the key of the node whose ancestors are to be printed
     * @return true if the node with the given key is found in the tree, false otherwise
     */
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

    public static boolean printAllAncestorV2(Node root, int key) {
        if (root == null) {
            return false;
        }

        if (root.key == key) {
            return true;
        }

        // Search left first
        if (printAllAncestorV2(root.left, key)) {
            System.out.println(root.key);
            return true;
        }

        // Only search right if not found in left
        if (printAllAncestorV2(root.right, key)) {
            System.out.println(root.key);
            return true;
        }

        return false;
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
