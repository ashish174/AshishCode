package algo.practice.tree.binarytree;

public class NodeWithParent {
    public int key;
    public NodeWithParent left;
    public NodeWithParent right;
    public NodeWithParent parent;

    public NodeWithParent(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node:" + key + "";
    }
}

