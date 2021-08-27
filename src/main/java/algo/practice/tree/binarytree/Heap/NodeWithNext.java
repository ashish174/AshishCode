package algo.practice.tree.binarytree.Heap;

public class NodeWithNext {
     public int key;
     public NodeWithNext left;
     public NodeWithNext right;
     public NodeWithNext next;

    public NodeWithNext() {
    }

    public NodeWithNext(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Node:" + key +"";
    }
}

