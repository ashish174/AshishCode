package algo.practice.tree.binarytree;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * The LCA class provides methods for finding the Lowest Common Ancestor (LCA) of two nodes in a binary tree.
 *
 * @author [Your Name]
 */
@Slf4j
class LCA {

    static int count = 0;

    /*
    * Sol2:
    * Return element when a match is found
    * At any node, if 2 matches come, that node is LCA,
    * However in case of they having in same path, we will never get 2 matches on any node till root.
    * Hence in that case, the resulted returned match is the LCA.
    *
    *
    * Sol1: This works when one node is not LCA of other node
    * When you traverse a tree, Keep pushing node in a stack,
    * and pop the item from stack when either it is a leaf node or when its both child is processed
    * When you found both element, the item on top of stack will have your LCS and
    * all items in stack represent all common ancestors
    * */


    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a binary search tree.
     * This code has a flaw: If both nodes are on same path, it just check top node, and not check bottom node if it exist at all.
     * i.e. if firstNode is an ancestor of secondNode, the method will return firstNode as soon as it encounters it, without checking if secondNode exists in the subtree rooted at firstNode.
     *
     * @param root the root node of the binary search tree
     * @param firstNode the first node whose lowest common ancestor needs to be found
     * @param secondNode the second node whose lowest common ancestor needs to be found
     * @return the lowest common ancestor node of the two input nodes
     */
    Node findLCA(Node root, int firstNode, int secondNode){
        if(root==null){
            return root;
        }
        if((root.key==firstNode)||(root.key==secondNode)){
            return root;
        }
        Node matchedLNode = findLCA(root.left, firstNode, secondNode);
        Node matchedRNode = findLCA(root.right, firstNode, secondNode);
        // If one node is in left subtree, other in right subtree
        if(matchedLNode!=null && matchedRNode!=null){
            return root;
        }
        // If node are both in either left or right subtree
        return matchedLNode!=null ? matchedLNode : matchedRNode;
    }

    /**
     * Finds the Lowest Common Ancestor (LCA) of two nodes in a binary tree using
     * traversal paths. This method finds the paths from the root to each of the two
     * nodes, then compares these paths to determine the last common node, which is
     * the LCA.
     *
     */
    Node findLCAV2(Node root, int firstNode, int secondNode) {
        Stack<Node> pathToFirstNode = new Stack<>();
        FindTraversalPath.findTraversalPath(root, firstNode, pathToFirstNode);
        Stack<Node> pathToSecondNode = new Stack<>();
        FindTraversalPath.findTraversalPath(root, secondNode, pathToSecondNode);
        log.info("pathToFirstNode {}", pathToFirstNode);
        log.info("pathToSecondNode {}", pathToSecondNode);
        if(pathToFirstNode.size() > pathToSecondNode.size()) {
            while(pathToFirstNode.size() > pathToSecondNode.size()) {
                pathToFirstNode.pop();
            }
        } else if (pathToFirstNode.size() < pathToSecondNode.size()) {
            while(pathToFirstNode.size() < pathToSecondNode.size()) {
                pathToSecondNode.pop();
            }
        }
        // both stack size is now equal
        while(!pathToFirstNode.isEmpty()) {
            if(pathToFirstNode.peek()==pathToSecondNode.peek()){
                return pathToFirstNode.peek();
            } else {
                pathToFirstNode.pop();
                pathToSecondNode.pop();
            }
        }
        return null;
    }



    public static void main(String[] args) {
        LCA lca = new LCA();
        Node root1 = new Node(3);
        root1.left = new Node(5);
        root1.left.left = new Node(18);
        root1.left.right = new Node(17);
        root1.left.right.left = new Node(11);
        root1.right = new Node(4);
        root1.right.left = new Node(13);
        PrintTree.printBinaryTree2(root1);
        Node myLca = lca.findLCA(root1, 13, 11);
        System.out.println("MyLCA :" +myLca);
        log.info("LCA is {}", lca.findLCAV2(root1, 5, 11));
    }
}
