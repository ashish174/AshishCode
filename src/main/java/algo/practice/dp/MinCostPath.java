package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * write a function that returns cost of minimum cost path to reach (m, n) from (0, 0)
 * We have to reach from (0,0) to (m,n). We can either go right or go down or go diagonally.
 * There is cost associated with each step mentioned by cost[m][n]
 *  Sample Cost[][]:
 *
 *              0   1   2   3
 *          --------------------------
 *          0 | 9   4   9   9
 *          1 | 6   7   6   4
 *          2 | 8   3   3   7
 *          3 | 7   4   9   10
 *
 * minCostPath[m][n] = min cost to reach to [m,n] from [0,0]
 *
 * minCostPath[m][n]    = cost[m][n] if m=0 && n==0
 *                      = cost[m-1][n-1] + min {minCostPath[m-1][n], minCostPath[m][n-1], minCostPath[m-1][n-1]}
 *

 *
 */
public class MinCostPath {
  public static final Logger LOGGER = LoggerFactory.getLogger(MinCostPath.class);

    public static int[][] tabulation;
    public static int[][] memoization;
    public static int[][] memoizationV2;

    public static int findMinCostByTabulation(int[][] cost, int m, int n) {
        tabulation = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    tabulation[m][n] = Integer.MAX_VALUE;
                } else {
                    tabulation[m][n] = cost[m - 1][n - 1]
                            + findMin(tabulation[m - 1][n], tabulation[m][n - 1], tabulation[m - 1][n - 1]);

                }
            }
        }
        return tabulation[m][n];
    }

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

    public static int findMinCostByMemoizationV2(int[][] cost, int m, int n) {
        if(memoizationV2[m][n] == 0) {
            if(m==0 || n == 0) {
                memoizationV2[m][n] = Integer.MAX_VALUE;
            } else if(m==1 && n==1) {
                memoizationV2[m][n] = cost[m][n];
            } else {
                memoizationV2[m][n] = cost[m-1][n-1] + findMin(memoizationV2[m-1][n], memoizationV2[m][n-1], memoizationV2[m-1][n-1]);
            }
        }
        return memoizationV2[m][n];
    }

    public static void printMemoizationPath(int[][] memoization, int m, int n){
        int i = m;
        int j = n;
        int min_i = --i;
        int min_j = --j;
        LOGGER.info("({},{})", min_i, min_j);
        while(i>0&&j>0){




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
        LOGGER.info("Cost matrix of [{}X{}] :", m,n);
        PrintArray.print2DSquareMatrix(cost);

        int minCostByRecursion = findMinCostByRecursion(cost, m - 1, n - 1);
        LOGGER.info("Minimum Cost by recursion to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByRecursion);

        int minCostByTabulation = findMinCostByTabulation(cost, m - 1, n - 1);
        LOGGER.info("Minimum Cost by tabulation to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByTabulation);
        PrintArray.print2DSquareMatrix(tabulation);

        memoization = new int[m + 1][n + 1];
        memoizationV2 = new int[m + 1][n + 1];
        // initialize the first row so as to not pick when i-1
        initializeFirstRowAndColumn(memoization, m, n);
        int minCostByMemoization = findMinCostByMemoization(cost, m - 1, n - 1);
        LOGGER.info("Minimum Cost by memoization to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByMemoization);
        PrintArray.print2DSquareMatrix(memoization);
        int minCostByMemoizationV2 = findMinCostByMemoizationV2(cost, m , n );
        LOGGER.info("Minimum Cost by memoization Version 2 to go from (0,0) to ({},{}) : {}", m - 1, n - 1, minCostByMemoizationV2);
        PrintArray.print2DSquareMatrix(memoizationV2);

    }



}
