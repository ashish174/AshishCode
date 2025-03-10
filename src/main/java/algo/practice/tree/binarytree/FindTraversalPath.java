package algo.practice.tree.binarytree;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Slf4j
public class FindTraversalPath {

    /**
     * Prints the traversal path from the root node to the target node with the specified key.
     *
     * This method uses a recursive approach to traverse the binary tree and push each visited node's key onto the provided stack.
     * If the target key is found, the method returns true and the stack contains the traversal path.
     * If the target key is not found, the method returns false and the stack remains unchanged.
     *
     * @param root the root node of the binary tree
     * @param key the target key to search for
     * @param path the stack to store the traversal path
     * @return true if the target key is found, false otherwise
     */
    public static boolean findTraversalPath(Node root, int key, Stack path) {
        if (root == null ){
            return false;
        }
        path.push(root);
        if(root.key==key) {
            return true;
        }
        if(findTraversalPath(root.left, key, path) || findTraversalPath(root.right, key, path)){
            return true;
        }
        path.pop();
        return false;
    }
    public static void main(String[] args){
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(8);
        root.right.right.left = new Node(6);
        root.right.right.right = new Node(7);
        PrintTree.printBinaryTree2(root);
        Stack<Node> pathStack = new Stack();
        int targetNodeKey = 4;
        findTraversalPath(root, targetNodeKey, pathStack);
        log.info("findTraversalPath to Key targetNodeKey {} : {}", targetNodeKey, pathStack);

    }
}
