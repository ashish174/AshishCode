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

    /**
     * Greedy approach: Scan each index reachable with the current number of
     * jumps and remember the farthest index any of them can reach. When the
     * scan reaches the current range's end, take one jump and use the farthest
     * reach as the next range's end. Runs in O(n) time and O(1) extra space.
     */
    public int jumpUsingGreedy(int[] nums) {
        int jumps = 0;
        // Indices through currentRangeEnd are reachable using 'jumps' jumps.
        int currentRangeEnd = 0;
        // Best next boundary found while scanning the current range.
        int farthestReachable = 0;

        // The last index needs no further scan: reaching it completes the answer.
        for (int i = 0; i < nums.length - 1; i++) {
            // From i, one jump can reach any index through i + nums[i].
            farthestReachable = Math.max(farthestReachable, i + nums[i]);

            // The current range is exhausted, so commit one jump and advance a layer.
            if (i == currentRangeEnd) {
                jumps++;
                currentRangeEnd = farthestReachable;
            }
        }

        return jumps;
    }

    /**
     * BFS approach: Treat each index as a node and each legal jump as an edge.
     * BFS visits positions in increasing number of jumps; parent links let us
     * count the jumps when the last index is first discovered. O(n^2) worst-case
     * time and O(n) extra space.
     */
    public int jumpUsingBFS(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        int[] parentList = new int[nums.length];
        Queue<Integer> idxQueue = new LinkedList<>();
        idxQueue.add(0);
        visited[0] = true;
        parentList[0] = -1;

        while (!idxQueue.isEmpty()) {
            // All positions in the queue are explored in nondecreasing jump count.
            int currNodeIdx = idxQueue.remove();
            int lastReachable = Math.min(currNodeIdx + nums[currNodeIdx], nums.length - 1);

            // Every unvisited index in this range is one jump away from currNodeIdx.
            for (int nextIdx = currNodeIdx + 1; nextIdx <= lastReachable; nextIdx++) {
                if (!visited[nextIdx]) {
                    visited[nextIdx] = true; // Mark on enqueue so it is processed only once.
                    parentList[nextIdx] = currNodeIdx;

                    if (nextIdx == nums.length - 1) {
                        return findNumOfJumps(nextIdx, parentList);
                    }

                    idxQueue.add(nextIdx);
                }
            }
        }
        return 0;
    }

    // Follow the recorded BFS parents back to index 0; each parent link is one jump.
    int findNumOfJumps(int nodeIdx, int[] parentList) {
        int jump = 0;
        while(parentList[nodeIdx]!=-1){
            jump++;
            nodeIdx = parentList[nodeIdx];
        }
        return jump;
    }
}
