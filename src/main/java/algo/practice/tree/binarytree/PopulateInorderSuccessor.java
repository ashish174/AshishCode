package algo.practice.tree.binarytree;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Populate next pointer of each node with its successor
 */
public class PopulateInorderSuccessor {
    private static Logger logger = LoggerFactory.getLogger(PopulateInorderSuccessor.class);
    private static NodeWithNext prev = null;

    public static void main(String[] args) {
        NodeWithNext root = new NodeWithNext(1);/*         1        */
        root.left = new NodeWithNext(2);        /*       /   \      */
        root.right = new NodeWithNext(3);       /*     2      3     */
        root.left.left = new NodeWithNext(4);  /*    /  \  /   \   */
        root.left.right = new NodeWithNext(5); /*   4   5  6   7   */
        root.right.left = new NodeWithNext(6);
        root.right.right = new NodeWithNext(7);
        //Below both code works. You can use either
        populateInorderSuccessor(root);
        //populateInorderSuccessorV2(root, new NodeWithNextWrapper());


        NodeWithNext firstInorderNode = root;
        while(firstInorderNode.left!=null) {
            firstInorderNode = firstInorderNode.left;
        }
        printUsingNextPointer(firstInorderNode);
    }


    public static void populateInorderSuccessor(NodeWithNext root) {
        if (root == null) {
            return;
        }
        populateInorderSuccessor(root.left);
        if (prev != null) {
            logger.info("Key {} Inorder Successor is :- {}", prev.key, root.key);
            prev.next = root;
        }
        prev = root;
        populateInorderSuccessor(root.right);
    }

    /**
     * We are using wrapper so that, when we are reassigning, we do reassignment on inner objects,
     * and hence outer object reference don't change.
     * Additionally you can also use NodeWithNext[] nodeArray;
     */
    public static void populateInorderSuccessorV2(NodeWithNext root) {
        NodeWithNextWrapper wrapper = new NodeWithNextWrapper();
        populateInorderSuccessorV2Util(root, wrapper);
    }

    public static void populateInorderSuccessorV2Util(NodeWithNext root, NodeWithNextWrapper wrapper) {
        if(root == null) {
            return;
        }
        populateInorderSuccessorV2Util(root.left, wrapper);
        if(wrapper.prev!=null) {
            logger.info("V2: Key {} Inorder Successor is :- {}", wrapper.prev.key, root.key);
            wrapper.prev.next = root;
        }
        wrapper.prev = root;
        populateInorderSuccessorV2Util(root.right, wrapper);
    }


    static class NodeWithNextWrapper {
        NodeWithNext prev;

    }

    /**
     * Hard to figure out
     * @param root
     * @param prev
     * @return
     */
    private static NodeWithNext populateInorderSuccessorV3(NodeWithNext root, NodeWithNext prev) {
        if (root == null) return prev;

        prev = populateInorderSuccessorV3(root.left, prev);

        if (prev != null) {
            prev.next = root;
        }

        prev = root;

        return populateInorderSuccessorV3(root.right, prev);
    }



    public static void printUsingNextPointer(NodeWithNext root) {
        while(root!=null) {
            System.out.print(root.key+ "    ");
            root = root.next;
        }
        System.out.println(" ");
    }

}
