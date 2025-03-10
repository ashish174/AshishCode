package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {
  public static void main(String[] args) {
    Node root = A_SampleTrees.getTree1();
    //Do using queue as done in findMaxWidth Algo
    doLevelOrderTraversal(root);
  }

  /**
   * Performs level-order traversal on a binary tree rooted at the specified node.
   * Level-order traversal visits nodes level by level starting from the root.
   *
   * @param root the root node of the binary tree to traverse
   */
  public static void doLevelOrderTraversal(Node root){
    Queue<Node> nodeQueue = new LinkedList<>();
    int levelWidth = -1;
    if(root==null){
      return;
    }
    nodeQueue.add(root);
    while(!nodeQueue.isEmpty()){
      levelWidth = nodeQueue.size();
      for(int i=0; i <levelWidth; i++) {
        Node node = nodeQueue.remove();
        if(node.left!=null){
          nodeQueue.add(node.left);
        }
        if(node.right!=null){
          nodeQueue.add(node.right);
        }
        System.out.print(node.key+ " : ");
      }
      System.out.println(" ");
    }
  }
}
