package algo.practice.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given an array of integers cost where cost[i] is the cost of taking a step from the ith floor of a staircase.
 * After paying the cost, you can step to either the (i + 1)th floor or the (i + 2)th floor.
 *
 * You may choose to start at the index 0 or the index 1 floor.
 *
 * Return the minimum cost to reach the top of the staircase, i.e. just past the last index in cost.
 *
 * Example 1:
 *
 * Input: cost = [1,2,3]
 *
 * Output: 2
 * Explanation: We can start at index = 1 and pay the cost of cost[1] = 2 and take two steps to reach the top. The total cost is 2.
 *
 * Example 2:
 *
 * Input: cost = [1,2,1,2,1,1,1]
 *
 * Output: 4
 *
 *
 */
@Slf4j
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] memoization = new int[n+1];
        return Math.min(minCost(cost, n, memoization), minCost(cost, n-1, memoization));
    }

    int minCost(int[] cost, int n, int[] memoization){
        if(n==1 || n==2) {
            return cost[n-1];
        }
        if(memoization[n]==0) {
            memoization[n] = cost[n-1] + Math.min(minCost(cost, n-1, memoization), minCost(cost, n-2, memoization));
        }
        return memoization[n];
    }

    public static void main(String[] args){
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        log.info("MinCostClimbingStairs : {}", minCostClimbingStairs.minCostClimbingStairs(new int[] {1,2,3}));
        log.info("MinCostClimbingStairs : {}", minCostClimbingStairs.minCostClimbingStairs(new int[] {1,2,1,2,1,1,1}));
    }
}
