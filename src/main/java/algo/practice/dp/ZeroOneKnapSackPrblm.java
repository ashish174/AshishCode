package algo.practice.dp;

import algo.practice.arrays.PrintArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * items of cost/value val[0..n-1] and weight wt[0..n-1],  we can carry total weight = W
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

    public static int findMaxValueByMemoization(int[] itemValue, int[] itemWeight, int n, int w) {
        if(w <= 0 || n <= 0){
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
        int[] itemVal = new int[] { 60, 100, 120 };
        int[] itemWeight = new int[] { 10, 20, 30 };
        int weight = 50;
        int n = itemVal.length;
        int maxValueByRecursion = findMaxValueByRecursion(itemVal, itemWeight, n, weight);
        LOGGER.info("Max Knapsack Value for itemValue {} itemWeight {} on weight <= {} is : {}", itemVal, itemWeight, weight, maxValueByRecursion);

        memoization = new int[n+1][weight+1];
        int maxValueByMemoization = findMaxValueByMemoization(itemVal, itemWeight, n, weight);
        LOGGER.info("Max Knapsack Value by memoization for itemValue {} itemWeight {} on weight <= {} is : {}", itemVal, itemWeight, weight, maxValueByMemoization);
        PrintArray.print2DSquareMatrix(memoization);


    }
}
