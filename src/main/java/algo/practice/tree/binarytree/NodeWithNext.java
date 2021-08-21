package algo.practice.tree.binarytree;

public class NodeWithNext {
    public int key;
    public NodeWithNext left;
    public NodeWithNext right;
    public NodeWithNext next;

    public NodeWithNext(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node:" + key + "";
    }
}

