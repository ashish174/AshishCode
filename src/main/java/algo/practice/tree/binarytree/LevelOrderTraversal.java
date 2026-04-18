package algo.practice.tree.binarytree;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
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
    Queue<Node> queue = new LinkedList<>();
    if(root==null){
      return;
    }
    int level = 0;
    queue.add(root);
    while(!queue.isEmpty()){
      int levelWidth = queue.size();
      log.info("level : {}, levelwidth : {}", level, levelWidth);
      for(int i=0; i <levelWidth; i++) {
        Node node = queue.remove();
        if(node.left!=null){
          queue.add(node.left);
        }
        if(node.right!=null){
          queue.add(node.right);
        }
        System.out.print(node.key+ " : ");
      }
      System.out.println(" ");
      level++;
    }
  }
}
