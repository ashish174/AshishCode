package algo.practice.tree.binarytree;

public class TreeCloning {
    public static Node cloneTree(Node node){
        Node clonedNode = clone(node);
        clonedNode.left = cloneTree(node.left);
        clonedNode.right = cloneTree(node.right);
        return clonedNode;
    }

    private static Node clone(Node node) {
        return new Node(node.key);
    }
}
