package algo.practice.tree.binarytree;

/**
 * How many different Unlabelled Binary Trees can be there with n nodes?
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
