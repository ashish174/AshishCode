package algo.practice.tree.binarytree;

// Not Working
public class TreeSuccessor {
    static Node tmp = null;
    static Node parent = null;

  /**
   * Finds the inorder predecessor of a given node in BST.
   * If the node has a left child, predecessor is the rightmost node in left subtree.
   * If not, the predecessor is one of its ancestors: the last ancestor whose right child is also in the path from root to the node.
   */
  public Node findInorderPredecessor(Node root, Node node) {
      Node[] prev = new Node[2]; // prev[0] = previous node; prev[1] = result (predecessor)
      findInorderPredecessorHelper(root, node, prev);
      return prev[1];

    }

    private void findInorderPredecessorHelper(Node root, Node node, Node[] prev) {
        if(root==null || node == null) {
            return ;
        }
        findInorderPredecessorHelper(root.left, node, prev);
        if (prev[1] != null) return; // Early return if found
        if(root==node){
            // Found the node: prev[0] is its inorder predecessor
            prev[1] = prev[0];
            return; // Optional: further early return
        }
        // Update prev[0] to current node for the next call
        prev[0] = root;
        findInorderPredecessorHelper(root.right, node, prev);
    }

    /**
   * If the node has a right child, successor is the leftmost node in right subtree.
   * If not, the successor is one of its ancestors: the last ancestor whose left child is also in the path from root to the node.
   */
  public static Node getSuccessor(Node root, Node node) {
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
