package dataStructure.tree.BinaryTree;

class LCA {

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root==null){
            return null;
        }
        Node lca = new Node(-1);
        int count = findLCACount(root, p, q, 0, lca);
        return lca.left;


    }

    int findLCACount(Node root, Node p, Node q, int foundCount, Node lca){
        if(root==null){
            return 0;
        }
        if(root.key==p.key || root.key==q.key){
            foundCount++;
        }

        foundCount = foundCount + findLCACount(root.left, p, q, 0, lca) + findLCACount (root.right, p, q, 0, lca);
        if(foundCount==2 && lca.left ==null){
            lca.left = root;
            System.out.println("LCA : "+lca.left);
        }
        return foundCount;

    }

    public static void main(String[] args) {
        Node root = new Node(3);
        Node lNode = new Node(5);
        Node rNode = new Node(1);
        root.left=lNode;
        root.right=rNode;

        LCA lca = new LCA();
        Node node = lca.lowestCommonAncestor(root, lNode, rNode);
        System.out.println("Key : "+ node.key);
    }
}
