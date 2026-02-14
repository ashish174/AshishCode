package algo.practice.tree.binarytree;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

    public static void findParentOfLastNodeV2(Node root, ParentNodeHolder parentNodeHolder) {
      if(root == null){
          return;
      }
      if(root.right!=null) {
          parentNodeHolder.setParent(root);
          findParentOfLastNodeV2(root.right, parentNodeHolder);
      } else if(root.left != null) {
          parentNodeHolder.setParent(root);
          findParentOfLastNodeV2(root.left, parentNodeHolder);
      }
    }

    @Setter
    @Getter
    @ToString
    // Note this is a holder object for parent Node field.
    static class ParentNodeHolder{
      Node parent;
    }

    public static void findParentOfLastNodeV3(Node root, Node[] parent) {
        if(root == null){
            return;
        }
        if(root.right!=null) {
            parent[0] = root;
            findParentOfLastNodeV3(root.right, parent);
        } else if(root.left != null) {
            parent[0] = root;
            findParentOfLastNodeV3(root.left, parent);
        }
    }


    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.right.right = new Node(40);
        PrintTree.printBinaryTree2(root);
        Node parentOfLastNode = findParentOfLastNode(root);
        System.out.println("Parent of last node : "+parentOfLastNode.key);
        ParentNodeHolder parentNodeHolder = new ParentNodeHolder();
        findParentOfLastNodeV2(root, parentNodeHolder);
        log.info("Parent of last node : {}", parentNodeHolder );
        Node[] parent = new Node[1];
        findParentOfLastNodeV3(root, parent);
        log.info("Parent of last node : {}", parent[0] );


    }
}
