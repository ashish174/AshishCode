package algo.practice.dp;

import lombok.extern.slf4j.Slf4j;

/**
 * There are n stairs to climb.
 * The person can climb either 1 stair or 2 stairs at a time.
 * How many ways to climb :-
 * climbStairs(n) = climbStairs(n-1) + climbStairs(n-2)
 *
 * This soln is similar to fibonacci series *
 */
@Slf4j
public class CountWaysToClimb {
    public int climbStairs(int numOfStairs) {
        int[] dp = new int[numOfStairs + 1];
        return climb(numOfStairs, dp);
    }

    int climb(int stairNum, int[] dp) {
        if (dp[stairNum] == 0) {
            if (stairNum == 1) {
                dp[stairNum] = 1;
            } else if (stairNum == 2) {
                dp[stairNum] = 2;
            } else {
                dp[stairNum] = climbStairs(stairNum - 1) + climbStairs(stairNum - 2);
            }
        }
        return dp[stairNum];
    }

    public static void main(String[] args) {
        CountWaysToClimb countWaysToClimb = new CountWaysToClimb();
        log.info("Tabulation : {}", countWaysToClimb.climbStairs(2));
        log.info("Memoization : {}", countWaysToClimb.climbStairsByMemoization(2));

    }

    public int climbStairsByMemoization(int n) {
        int[] memoization = new int[n+1];
        return climbByMemoization(n, memoization);
    }

    public int climbByMemoization(int n, int[] memoization) {
        if(n==0 || n==1 || n==2) {
            return 1;
        }
        if(memoization[n]==0) {
            memoization[n] =  climbByMemoization(n-1, memoization) + climbByMemoization(n-2, memoization);
        }
        return memoization[n];
    }

}