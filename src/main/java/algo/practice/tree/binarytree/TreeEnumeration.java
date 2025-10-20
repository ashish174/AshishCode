package algo.practice.tree.binarytree;

// Wrong Implementation
/**
 * How many different Unlabelled Binary Trees can be there with n nodes?
 * Utility class for enumerating unlabelled binary trees.
 * <p>
 * Provides a method to calculate the number of different unlabelled binary trees
 * that can be formed with a specified number of nodes.
 * </p>
 * <p>
 * An unlabelled binary tree is defined as a binary tree structure in which nodes are indistinguishable,
 * i.e., node labels/names are not considered, only the structure matters.
 * </p>
 */
public class TreeEnumeration {
    public static int countUnlabelledBinaryTree(int numOfNode){
        if(numOfNode==0 || numOfNode==1){
            return numOfNode;
        }
        return 1 + countUnlabelledBinaryTree(numOfNode-1)*countUnlabelledBinaryTree(numOfNode-1);
    }

    public static void main(String[] args) {
        System.out.println(countUnlabelledBinaryTree(3));
    }
}
