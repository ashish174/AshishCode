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
    public int climbStairs(int A) {
        int[] dp = new int[A + 1];
        return climb(A, dp);
    }

    int climb(int A, int[] dp) {
        if (dp[A] == 0) {
            if (A == 1) {
                dp[A] = 1;
            } else if (A == 2) {
                dp[A] = 2;
            } else {
                dp[A] = climbStairs(A - 1) + climbStairs(A - 2);
            }
        }
        return dp[A];
    }

    public static void main(String[] args) {
        System.out.println(new CountWaysToClimb().climbStairs(1));
    }
}