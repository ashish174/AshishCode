package algo.practice.tree.binarytree;

public class FindParentOfLastNode {

  /**
   * Finds the parent of the last node in a binary tree structure represented by nodes.
   * Traverses down the right subtree first, then the left subtree, and returns the parent
   * when reaching a leaf node (a node without children).
   *
   * @param node the current node being traversed
   * @return the parent of the last node found during traversal
   *
   * First traverse to node right and whereever node right is null, traverse to node left
   * and whereever both node right and node left is null (i.e. leaf node), then return that result.
   *
   */
  public static Node findParentOfLastNode(Node node) {
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
