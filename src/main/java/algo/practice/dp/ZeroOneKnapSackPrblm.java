package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 0/1 Knapsack problem :- Given a set of items, each with a value and a weight, determine the subset of these items to include
 * in a collection so that the total value is as large as possible without exceeding a given weight limit.
 *
 * items of cost/value val[0..n-1] and weight wt[0..n-1],
 * we can carry total weight = W
 * find the max value items that can be adjusted in <= W.
 * 0-1 property :- You cannot break the item - either you pick it or you don't
 *
 * cost[v[i], W]    = 0                                             if W <=0 || i <=0
 *                  = cost[v[i-1], W]                               if W < weight[i]
 *                  = Max {                                         if W > weight[i]
 *                          val[i]+cost[v[i-1], W - weight[i]],
 *                          cost[v[i-1], W]
 *                          }
 *
 */
public class ZeroOneKnapSackPrblm {
    public static final Logger LOGGER = LoggerFactory.getLogger(ZeroOneKnapSackPrblm.class);
    public static int[][] memoization;
    public static int[][] tabulation;

    public static int findMaxValueByTabulation(int[] itemValue, int[] itemWeight, int n, int w) {
        tabulation = new int[n + 1][w + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= w; j++) {
                if (j <= 0 || i <= 0) {
                    tabulation[i][j] = 0;
                } else if (itemWeight[i - 1] > j) {
                    tabulation[i][j] = tabulation[i - 1][j];
                } else {
                    tabulation[i][j] = Math.max(
                            itemValue[i - 1] + tabulation[i - 1][j - itemWeight[i - 1]],
                            tabulation[i - 1][j]
                    );
                }
            }
        }
        return tabulation[n][w];
    }

    public static int findMaxValueByMemoization(int[] itemValue, int[] itemWeight, int n, int w) {
        if (w <= 0 || n <= 0) {
            return 0;
        }
        if (memoization[n][w] == 0) {
            if (itemWeight[n - 1] > w) {
                memoization[n][w] = findMaxValueByMemoization(itemValue, itemWeight, n - 1, w);
            } else {
                memoization[n][w] = Math.max(
                        (itemValue[n - 1] + findMaxValueByRecursion(itemValue, itemWeight, n - 1, w - itemWeight[n - 1]))
                        , findMaxValueByRecursion(itemValue, itemWeight, n - 1, w)
                );
            }
        }
        return memoization[n][w];
    }

    public static int findMaxValueByRecursion(int[] itemValue, int[] itemWeight, int n, int weight) {
        if (weight <= 0 || n <= 0) {
            return 0;
        }
        if (itemWeight[n - 1] > weight) {
            return findMaxValueByRecursion(itemValue, itemWeight, n - 1, weight);
        } else {
            return Math.max(
                    (itemValue[n - 1] + findMaxValueByRecursion(itemValue, itemWeight, n - 1, weight - itemWeight[n - 1]))
                    , findMaxValueByRecursion(itemValue, itemWeight, n - 1, weight)
            );
        }
    }

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        int[] itemVal = new int[] { 60, 100, 120 };
        int[] itemWeight = new int[] { 10, 20, 30 };
        int weight = 50;
        int n = itemVal.length;
        stopwatch.start();
        int maxValueByRecursion = findMaxValueByRecursion(itemVal, itemWeight, n, weight);
        LOGGER.info("Max Knapsack Value for itemValue {} itemWeight {} on weight <= {} is : {}, Time Elapsed : {}", itemVal, itemWeight, weight, maxValueByRecursion, stopwatch.elapsed());
        stopwatch.reset();


        memoization = new int[n+1][weight+1];
        stopwatch.start();
        int maxValueByMemoization = findMaxValueByMemoization(itemVal, itemWeight, n, weight);
        LOGGER.info("Max Knapsack Value by memoization for itemValue {} itemWeight {} on weight <= {} is : {}, Time Elapsed : {}", itemVal, itemWeight, weight, maxValueByMemoization, stopwatch.elapsed());
        stopwatch.reset();
        PrintArray.print2DSquareMatrix(memoization);

        stopwatch.start();
        int maxValueByTabulation = findMaxValueByTabulation(itemVal, itemWeight, n, weight);
        LOGGER.info("Max Knapsack Value by tabulation for itemValue {} itemWeight {} on weight <= {} is : {}, Time Elapsed : {}", itemVal, itemWeight, weight, maxValueByTabulation, stopwatch.elapsed());
        stopwatch.reset();



    }
}
