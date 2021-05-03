package algo.practice.tree.binarytree;

public class TreeHeight {
    public static int findTreeHeight(Node node){
        if(node==null){
            return 0;
        }
        return 1 + Math.max(findTreeHeight(node.left), findTreeHeight(node.right));
    }
}
