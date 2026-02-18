package algo.practice.graph.basic;

/**
 * Very useful for:
 * 1. Counting connected components
 * 2. Cycle detection in undirected graphs
 * 3. Kruskal’s MST
 * 4. Dynamic connectivity
 *
 * Working:
 * When we process an edge (u, v):
 * If u and v are already in the same set, nothing changes
 * If they are in different sets, we merge them and the number of components decreases by 1
 *
 *
 */
public class UnionFindAlgo {

    int countConnectedComponent(int n, int[][] edges) {
        int[] parent = new int[n];
        //Init parent
        for(int i=0; i<n; i++){
            // init parent as self. You can also init parent as -1, if needed.
            parent[i] = i;
        }
        //Start by assuming each node is its own component.
        //init numOfComponent as number of vertex as each vertex is a component to start with.
        int numOfComponent = n;
        for(int[] edge: edges){
            if(union(edge[0], edge[1], parent)){
                //merge happened
                numOfComponent--;
            }
        }
        return numOfComponent;
    }


    // Union two nodes
    // Returns true if a merge happened
    // Returns false if both nodes are already in the same component
    boolean union(int u, int v, int[] parent){
        int rootU = find(u, parent);
        int rootV = find(v, parent);
        // Both belong to same cpnnected component
        //We can also find whether cycle exist if income edge [u, v] connects the already connected component
        if(rootU==rootV){
            return false;
        }
        // Merge the two components by attaching one root to the other
        parent[rootV] = rootU;
        // to notify merge happens
        return true;
    }


    /**
   * Find the representative (root) of the set containing x
   *
   * Ex:
   * Parent[] Before:  3 → 2 → 1 → 0            i.e. [0, 0, 1, 2]
   * Parent[] After path compression:           i.e. [0, 0, 0, 0]
   *          3 → 0
   *          2 → 0
   *          1 → 0

   */
  int find(int x, int[] parent) {
        //You climb up the tree until you reach the root.
        if(parent[x] != x){
            // path compression: This flatten the tree, avoiding recalculation
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }

    /**
     * Slightly expensive approach
     *
     * Ex:
     * 3 → 2 → 1 → 0
     */
    /*int find(int x, int[] parent) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }*/
}
