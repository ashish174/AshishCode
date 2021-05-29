package algo.practice.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * We have to reach from (0,0) to (m,n). We can either go right or go down or go diagonally.
 * There is cost associated with each step mentioned by cost[m][n]
 * minCostPath[m][n] = ??
 *
 * minCostPath[m][n]    = cost[m][n] if m=0 && n==0
 *                      = cost[m-1][n-1] + min {minCostPath[m-1][n], minCostPath[m][n-1], minCostPath[m-1][n-1]}
 *
 */
public class MinCostPath {
    public static final Logger LOGGER = LoggerFactory.getLogger(MinCostPath.class);

    public static int[][] tabulation;
    public static int[][] memoization;

    public static int findMinCostByMemoization(int[][] cost, int m, int n) {
        if (memoization[m + 1][n + 1] == 0) {
            if (m == 0 && n == 0) {
                memoization[m + 1][n + 1] = cost[m][n];
            } else {
                memoization[m + 1][n + 1] = cost[m][n]
                        + findMin(findMinCostByMemoization(cost, m - 1, n)
                        , findMinCostByMemoization(cost, m, n - 1),
                        findMinCostByMemoization(cost, m - 1, n - 1));
            }
        }
        return memoization[m + 1][n + 1];
    }

    public static int findMinCostByRecursion(int[][] cost, int m, int n) {
        if (m < 0 || n < 0) {
            return Integer.MAX_VALUE;
        } else if (m == 0 && n == 0) {
            return cost[m][n];
        } else {
            return cost[m][n]
                    + findMin(findMinCostByRecursion(cost, m - 1, n)
                    , findMinCostByRecursion(cost, m, n - 1),
                    findMinCostByRecursion(cost, m - 1, n - 1));
        }
    }


    public static int findMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static void initializeFirstRowAndColumn(int[][] memoization, int m, int n) {
        for (int i = 0; i < m + 1; i++) {
            memoization[i][0] = Integer.MAX_VALUE;
        }
        for (int j = 0; j < n + 1; j++) {
            memoization[0][j] = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        int[][] cost = {{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}};
        int m = cost.length;
        int n = cost[0].length;

        int minCostByRecursion = findMinCostByRecursion(cost, m - 1, n - 1);
        LOGGER.info("Minimum Cost by recursion to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByRecursion);


        memoization = new int[m + 1][n + 1];
        // initialize the first row so as to not pick when i-1
        initializeFirstRowAndColumn(memoization, m, n);
        int minCostByMemoization = findMinCostByMemoization(cost, m - 1, n - 1);
        LOGGER.info("Minimum Cost by memoization to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByMemoization);

    }



}
