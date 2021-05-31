package algo.practice.dp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Let m be number of coins & n be the sum for which change is sought
 * Count(m ,n) ??
 *
 * Count(m,n) = 1 if n=0  (when complete coin change is possible)
 *              = Count(m, n-m) + Count(m-1, n)
 *
 *
 */
public class CoinChange {
    public static final Logger LOGGER = LoggerFactory.getLogger(CoinChange.class);
    public static int[][] memoization;
    public static int[][] tabulation;

    /**
     * Here memoization looks better than tabulation as we need want to calculate every dp[][] value
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

    public static void main(String[] args) {
        //int[] coin = {1, 2, 3};
        //int m = coin.length;
        //int n = 4;
        int[] coin = {2, 5, 3, 6};
        int m = coin.length;
        int n = 10;
        int numOfWaysForCoinChangeRecursively = findNumOfWaysForCoinChangeRecursively(coin, m, n);
        LOGGER.info("Num of ways recursively to make the coin change using coin denomiantions {} for amount {} is : {}", coin, n, numOfWaysForCoinChangeRecursively);

        //Here memoization looks better than tabulation as we need want to calculate every dp[][] value
        memoization = new int[m + 1][n + 1];
        int numOfWaysForCoinChangeByMemoization = findNumOfWaysForCoinChangeByMemoization(coin, m, n);
        LOGGER.info("Num of ways by memoization to make the coin change using coin denomiantions {} for amount {} is : {}", coin, n, numOfWaysForCoinChangeByMemoization);

    }
}
