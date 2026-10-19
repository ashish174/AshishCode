package algo.practice.greedy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index i. For example, if you are at nums[i], you can jump to any index i + j where:
 *
 * j <= nums[i]
 * i + j < nums.length
 * You are initially positioned at nums[0].
 *
 * Return the minimum number of jumps to reach the last position in the array (index nums.length - 1). You may assume there is always a valid answer.
 *
 * Example 1:
 *
 * Input: nums = [2,4,1,1,1,1]
 *
 * Output: 2
 * Explanation: Jump from index 0 to index 1, then jump from index 1 to the last index.
 *
 * Example 2:
 *
 * Input: nums = [2,1,2,1,0]
 *
 * Output: 2
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 100
 *
 * Approach (BFS): Treat each array index as a node, with edges to every index
 * reachable in one jump. Process nodes level by level; each level represents
 * one jump. Mark indices when enqueuing them so each is visited once, and store
 * each index's parent to count the jumps when the last index is first reached.
 *
 * Improvement: The same minimum can be found in O(n) time and O(1) space by
 * tracking the end of the current jump range and the farthest index reachable
 * from that range.
 *
 *
 */
public class JumpGame2 {

    public int jumpUsingGreedy(int[] nums) {
        return -1;

    }

    // Using BFS in O(n)
    public int jumpUsingBFS(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int[] parentList = new int[nums.length];
        Queue<Integer> idxQueue = new LinkedList<>();
        idxQueue.add(0);
        visited[0] = true;
        parentList[0] = -1;
        for(int i=1; i < nums.length; i++){
            int currNodeIdx = idxQueue.remove();
            int stepsAllowed = nums[currNodeIdx];
            int parent = currNodeIdx;
            for(int j=1; j <=stepsAllowed; j++){
                int finalIdx = (i-1)+j;
                if(!visited[finalIdx] && finalIdx < nums.length) {
                    visited[finalIdx] = true;
                    idxQueue.add(finalIdx);
                    parentList[finalIdx] = parent;
                    if(finalIdx==nums.length-1){
                        //output result found
                        return findNumOfJumps(finalIdx, parentList);
                    }

                }
            }
        }
        return 0;
    }

    int findNumOfJumps(int nodeIdx, int[] parentList){
        int jump = 0;
        while(parentList[nodeIdx]!=-1){
            jump++;
            nodeIdx = parentList[nodeIdx];
        }
        return jump;
    }
}
