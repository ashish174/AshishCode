package algo.practice.tree.binarytree;

public class Node {
     public int key;
     public Node left;
     public Node right;

    public Node() {
    }

    public Node(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node:" + key +"";
    }
}

