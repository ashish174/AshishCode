package algo.practice.tree.binarytree.BST;

import algo.practice.tree.binarytree.Node;

public class BinarySearchTree {
    public static Node searchBST(Node root, int key) {
        if (root == null) {
            return null;
        }
        System.out.print(root.key + "  ");
        if (key == root.key) {
            return root;
        } else if (key < root.key) {
            return searchBST(root.left, key);
        } else {
            return searchBST(root.right, key);
        }
    }

    public static Node insertKey(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key == root.key) {
            return null;
        } else if (key < root.key) {
            root.left = insertKey(root.left, key);
        } else {
            root.right = insertKey(root.right, key);
        }
        return root;
    }

    public static Node deleteNode(Node root, int key) {
        //Case 1 : if it s leaf Node
        //Case 2 : if it s node with one child null
        //Case 3 : if it s node with both child
        Node parent = null;
        Node tmp = root;
        if (root == null) {
            return null;
        }
        while (tmp != null) {
            if (key < tmp.key) {
                parent = tmp;
                tmp = tmp.left;
            } else if (key > tmp.key) {
                parent = tmp;
                tmp = tmp.right;
            } else {
                //tmp.key == key
                //Case 1:
                if (tmp.left == null & tmp.right == null) {
                    if (parent.left == tmp) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (tmp.left == null || tmp.right == null) {
                    //Case 2:
                    Node child = tmp.left != null ? tmp.left : tmp.right;
                    if (parent.left == tmp) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                } else {
                    //Case 3
                    Node parent2 = tmp;
                    Node tmp2 = tmp.right;
                    if (tmp2.left == null) {
                        tmp.key = tmp.right.key;
                        tmp.right = tmp.right.right;
                        tmp2.key = key;
                        return tmp2;
                    } else {
                        while (tmp2.left != null) {
                            parent2 = tmp2;
                            tmp2 = tmp2.left;
                        }
                        if (parent2.left == tmp2) {
                            parent2.left = null;
                        } else {
                            parent2.right = null;
                        }
                        tmp.key = tmp2.key;
                        tmp2.key = key;
                        return tmp2;
                    }
                }
                return tmp;
            }
        }
        return null;
    }

    public static Node deleteNodeRecursively(Node root, int key){
        if(root==null){
            return null;
        }
        if(key<root.key){
            root.left = deleteNodeRecursively(root.left, key);
        } else if(key>root.key){
            root.right = deleteNodeRecursively(root.right, key);
        } else {
            //Case 1: if left /right child is null
            if(root.left==null){
                return root.right;
            } else if (root.right==null){
                return root.left;
            } else{
                //Both child not null
                Node successorNode = findMinFromLeftSubtree(root.right);
                root.key = successorNode.key;
                successorNode.key = key;
                root.right = deleteNodeRecursively(root.right, key);
            }
        }
        return root;
    }

    public static Node findMinFromLeftSubtree(Node node){
        while(node.left!=null){
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        Node root = new Node(8);
        root.left = new Node(3);
        root.left.left = new Node(1);
        root.left.right = new Node(6);
        root.right = new Node(10);
        //root.right.left = new Node(9);
        root.right.right = new Node(14);
        root.right.right.left = new Node(13);
        root.right.right.right = new Node(16);
        /*int key = 6;
        System.out.print("Path : ");
        Node searchedNode = searchBST(root, key);
        System.out.println("\nSearching for Key " +key+ " : "+searchedNode);*/
        //System.out.println("Inserted Key : "+insertKey(root, 11));
        PrintTree.printBinaryTree2(root);

        //System.out.println("Deleted key " + deleteNode(root, 8));
        System.out.println("Deleted key " + deleteNodeRecursively(root, 8));
        //PrintTree.printBinaryTree1(root);
        PrintTree.printBinaryTree2(root);
    }
}
