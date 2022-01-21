package algo.practice.tree.binarytree;

import java.util.List;

/**
 * Search first the target Node.
 * Complete the procedure in 2 steps:
 *      1. Find all node at k distance in subtree with target node as root
 *      2. Find all node at k distance in all parent subtree of target node
 *              We Can use a parent pointer in this case.
 *              Try traversing up and subtracting that many steps from distance (distance - steps)
 *              Then for each parent, if (distance - steps) > 0, call utiltiy from Step 1 : Find all node at k distance in subtree with parent -> other child as root
 *          if (distance - steps) ==0, include that parent directly.
 *
 *
 */
public class FindNodesAtKDistanceFromTarget {
    public static List<Integer> findNodesAtKDistanceFromTarget(){
        return null;
    }
}
