package algo.practice.tree.binarytree.Heap;

import algo.practice.tree.binarytree.NodeWithParent;

public class MinHeapUsingNode {
    NodeWithParent root;
    int size;

    int getMin() {
        return root != null ? root.key : -1;
    }

/*

    int extractMin(){
        return -1;
    }


    void insertKey(int key){

    }

    void deleteKey(NodeWithParent node){

    }
*/

    void decreaseKey(NodeWithParent node, int newVal){
        node.key = newVal;
        while(node.parent!=null && newVal < node.parent.key){
            int tmp = node.parent.key;
            node.parent.key = node.key;
            node.key = tmp;
            node = node.parent;
        }
    }

    void minHeapify(NodeWithParent node){
        if(node==null){
            return;
        }
        NodeWithParent lNode = node.left;
        NodeWithParent rNode = node.right;
        NodeWithParent minNode = node;
        if(lNode!=null && lNode.key < minNode.key){
            minNode = lNode;
        }
        if(rNode!=null && rNode.key < minNode.key){
            minNode = rNode;
        }
        minHeapify(minNode);
    }
}
