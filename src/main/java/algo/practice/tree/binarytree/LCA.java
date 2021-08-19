package algo.practice.tree.binarytree;

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



    Node findLCA(Node root, int m, int n){
        if(root==null){
            return root;
        }
        if((root.key==m)||(root.key==n)){
            return root;
        }
        Node matchedLNode = findLCA(root.left, m, n);
        Node matchedRNode = findLCA(root.right, m, n);
        if(matchedLNode!=null && matchedRNode!=null){
            return root;
        }
        return matchedLNode!=null ? matchedLNode : matchedRNode;
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
        Node myLca = lca.findLCA(root1, 13, 11);
        System.out.println("MyLCA :" +myLca);
    }
}
