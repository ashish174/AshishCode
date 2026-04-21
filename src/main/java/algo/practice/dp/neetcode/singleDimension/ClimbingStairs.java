package algo.practice.dp.neetcode.singleDimension;

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
public class ClimbingStairs {
    public int climbStairs(int numOfStairs) {
        int[] dp = new int[numOfStairs + 1];
        return climb(numOfStairs, dp);
    }

    int climb(int stairNum, int[] dp) {
        //if dp[stairNum] is not initialized
        if (dp[stairNum] == 0) {
            if (stairNum == 1) {
                dp[stairNum] = 1;
            } else if (stairNum == 2) {
                dp[stairNum] = 2;
            } else {
                dp[stairNum] = climb(stairNum - 1, dp) + climb(stairNum - 2, dp);
            }
        }
        return dp[stairNum];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        log.info("Tabulation : {}", climbingStairs.climbStairs(2));
        log.info("Memoization : {}", climbingStairs.climbStairsByMemoization(2));

    }

    /**
     * Bottom-up tabulation approach.
     *
     * @param n number of stairs (n >= 0)
     * @return number of distinct ways to climb to the top
     */
    public int climbStairsByTabulation(int n) {
        if (n == 1 || n == 2) {
            return n;
        }

        int[] tabulation = new int[n + 1];
        tabulation[0] = 1; // 1 way to reach step 0
        tabulation[1] = 1; // 1 way to reach step 1

        for (int i = 2; i <= n; i++) {
            tabulation[i] = tabulation[i - 1] + tabulation[i - 2];
        }

        return tabulation[n];
    }



    public int climbStairsByMemoization(int n) {
        int[] memoization = new int[n+1];
        return climbByMemoization(n, memoization);
    }

    public int climbByMemoization(int n, int[] memoization) {
        if(n==0 || n==1 || n==2) {
            return n;
        }
        //if memoization[n] is not initialized
        if(memoization[n]==0) {
            memoization[n] =  climbByMemoization(n-1, memoization) + climbByMemoization(n-2, memoization);
        }
        return memoization[n];
    }

}