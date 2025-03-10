package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Find num of ways to do Coin change from m number of coins, where each type has infinite quantity
 * Let m be number of coins & n be the sum/amount for which change is sought
 *
 * For example, for N = 4 and S = {1,2,3},
 * there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
 * So output should be 4.
 *
 * Count(m ,n) ??
 *
 * Count(m,n) = 1 if n=0  (when complete coin change is possible)
 *              = Count(m, n-m) + Count(m-1, n)  {2 cases :- including m & not including m}
 *
 *
 */
public class CoinChange {
    public static final Logger LOGGER = LoggerFactory.getLogger(CoinChange.class);
    public static int[][] memoization;
    public static int[][] tabulation;


    /**
     * This has worst performance, as it uses recursion without any optimization.
     * This method calculates the number of ways to make change for a given amount 'n' using 'm' denominations of coins.
     * It uses recursion to explore all possible combinations of coins that sum up to 'n'.
     *
     */
    public static int findNumOfWaysForCoinChangeRecursively(int[] coins, int m, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0 || m <= 0) {
            return 0;
        }
        return findNumOfWaysForCoinChangeRecursively(coins, m, n - coins[m - 1])
                + findNumOfWaysForCoinChangeRecursively(coins, m - 1, n);
    }

  /**
   * Here memoization looks better than tabulation as we don't want to calculate every dp[][] value
   *
   *  * This method uses memoization to optimize performance by storing intermediate results in a cache,
   *  * avoiding redundant calculations.
   *
   * @param coins
   * @param m
   * @param n
   * @return
   */
  public static int findNumOfWaysForCoinChangeByMemoization(int[] coins, int m, int n) {
        if (n < 0 || m <= 0) {
            return 0;
        }
        if (memoization[m][n] == 0) {
            if (n == 0) {
                memoization[m][n] = 1;
            } else {
                memoization[m][n] = findNumOfWaysForCoinChangeByMemoization(coins, m, n - coins[m - 1])
                        + findNumOfWaysForCoinChangeByMemoization(coins, m - 1, n);
            }
        }
        return memoization[m][n];
    }

    /**
     * This method calculates the number of ways to make change for a given amount 'n' using 'm' denominations of coins.
     * It uses dynamic programming with tabulation to efficiently compute the solution.
     */
    public static int findNumOfWaysForCoinChangeByTabulation(int[] coins, int m, int n) {
        tabulation = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    tabulation[i][j] = 1;
                } else if (i <= 0) {
                    tabulation[i][j] = 0;
                } else {
                    tabulation[i][j] =
                            (j - coins[i - 1] < 0 ? 0 : tabulation[i][j - coins[i - 1]])
                            + tabulation[i - 1][j];
                }
            }
        }
        return tabulation[m][n];
    }

    public static void main(String[] args) {
        int[] coin = {1, 2, 3};
        int m = coin.length;
        int n = 4;
        //int[] coin = {2, 5, 3, 6};
        //int m = coin.length;
        //int n = 10;
        int numOfWaysForCoinChangeRecursively = findNumOfWaysForCoinChangeRecursively(coin, m, n);
        LOGGER.info("Num of ways recursively to make the coin change using coin denomiantions {} for amount {} is : {}", coin, n, numOfWaysForCoinChangeRecursively);

        //Here memoization looks better than tabulation as we need want to calculate every dp[][] value
        memoization = new int[m + 1][n + 1];
        int numOfWaysForCoinChangeByMemoization = findNumOfWaysForCoinChangeByMemoization(coin, m, n);
        LOGGER.info("Num of ways by memoization to make the coin change using coin denomiantions {} for amount {} is : {}", coin, n, numOfWaysForCoinChangeByMemoization);
        PrintArray.print2DSquareMatrix(memoization);

        int numOfWaysForCoinChangeByTabulation = findNumOfWaysForCoinChangeByTabulation(coin, m, n);
        LOGGER.info("Num of ways by tabulation to make the coin change using coin denomiantions {} for amount {} is : {}", coin, n, numOfWaysForCoinChangeByTabulation);
        PrintArray.print2DSquareMatrix(tabulation);
    }
}
