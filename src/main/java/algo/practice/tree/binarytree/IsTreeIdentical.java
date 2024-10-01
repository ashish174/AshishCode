package algo.practice.tree.binarytree;

public class IsTreeIdentical {

    /**
     * Determines whether two binary trees rooted at nodes 'root1' and 'root2' are identical.
     * An identical tree has the same structure and node values where each node matches
     * its counterpart in the other tree.
     *
     * @param root1 the root node of the first binary tree
     * @param root2 the root node of the second binary tree
     * @return true if both trees are identical, false otherwise
     */
    public static boolean isIdentical(Node root1, Node root2){
        if(root1==null && root2==null){
            return true;
        }
        if(root1==null || root2==null){
            return false;
        }
        return root1.key==root2.key && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.right.left = new Node(6);

        System.out.println("Is Two trees identical : " +isIdentical(root1, root2));
    }
}
