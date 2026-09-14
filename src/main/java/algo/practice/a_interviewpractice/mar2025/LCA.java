package algo.practice.a_interviewpractice.mar2025;

public class LCA {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //assumption is both node exists, else you need to put check
        //exist(p) && exist(q)
        if(root==null){
            return null;
        }
        if(root==p || root==q){
            return root;
        }
        TreeNode lAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rAncestor = lowestCommonAncestor(root.right, p, q);
        //keys are in left and right
        if(lAncestor!=null && rAncestor!=null){
            return root;
        }
        //keys are in same subtree.
        if(lAncestor!=null || rAncestor!=null){
            return lAncestor!=null? lAncestor: rAncestor;
        }
        return null;
    }


    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
