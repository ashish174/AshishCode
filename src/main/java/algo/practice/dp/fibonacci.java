package algo.practice.dp;

public class fibonacci {
    public int climbStairs(int A) {
        int[] dp = new int[A + 1];
        return climb(A, dp);
    }

    int climb(int A, int[] dp) {
        if (dp[A] != 0) {
            return dp[A];
        }
        if (A == 1) {
            dp[A] = 1;
        } else if (A == 2) {
            dp[A] = 2;
        } else {
            dp[A] = climbStairs(A - 1) + climbStairs(A - 2);
        }
        return dp[A];
    }

    public static void main(String[] args) {
        System.out.println(new fibonacci().climbStairs(1));
    }
}