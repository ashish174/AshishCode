package algo.practice.dp;

/**
 * There are n stairs to climb.
 * The person can climb either 1 stair or 2 stairs at a time.
 * How many ways to climb :-
 * climbStairs(n) = climbStairs(n-1) + climbStairs(n-2)
 *
 * This soln is similar to fibonacci series *
 */
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
        System.out.println(new CountWaysToClimb().climbStairs(1));
    }
}