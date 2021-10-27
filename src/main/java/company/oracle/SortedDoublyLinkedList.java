package company.oracle;

/*
*
* Write a Sorted Doubly Linked List with insert and print operations. Insertion should happen in o(logn)
* Use a doubly linklist + a BST for Sorting
*
* List<Node> = DLL
* TreeNode = BST
*
* TreeNode predecessorTreeNode = findPredecessorTreeNodeInBST(int data);
* insertIntoBST(predecessorTreeNode, newNode);
* insertIntoDLL(predecessorTreeNode.getNode(), newNode);
*
*
* */
public class SortedDoublyLinkedList {

    class Node{
        private int key;
        private Node next;
        private Node prev;
    }

    class TreeNode{
        private Node node;
        private TreeNode left;
        private TreeNode right;
    }
}
