package company.amazon;

public class FindDistanceBetweenNodes {
/*
    int findLeastCommonAncestor(Node root, int key1, int key2){
        if(root==null){
            return -1;
        }
        if(root.getData()==key1 || root.getData()==key2){
            return root.getData;
        }
        int leftSubtreeData = findLeastCommonAncestor(root.getLeft(), key1,key2);
        int rightSubtreeData = findLeastCommonAncestor(root.getRight(), key1,key2);
        if(leftSubtree!=-1 && rightSubtreeData!=-1){
            return root;
        }
        if(leftSubtreeData==-1){
            return rightSubtreeData;
        } else {
            return leftSubtreeData;
        }

    }



    int findPath(Node root, int k1, int, k2){
        int lca = findLeastCommonAncestor(root, k1, k2);
        if(lca==k1){
            return findElemInTree(root, k2, 0)
        } else if (lca==k2){
            return findElemInTree(root, k1, 0)
        } else {
            return findElemInTree(root, k1, 0) + findElemInTree(root, k2, 0) ;
        }
    }

    int findElemInTree(Node root, int key, int level){
        if(root==null){
            return -1;
        }
        if(root.data==key){
            return level;
        }
        int lSubtreeData = findElemInTree(root.getLeft(), key, level+1)
        int rSubtreeData = findElemInTree(root.getRight(), key, level+1)
        if(rsubtreeData==-1){
            return lSubtreeData;
        } else {
            return rsubtreeData;
        }


    }*/

}
