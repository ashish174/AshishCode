package algo.practice.interviewpractice.mar2025.arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 *
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
@Slf4j
public class StockBuySell {
    public int maxProfit(int[] prices) {
        if(prices.length <=1) {
            return 0;
        }

        int min = prices[0];
        int max = prices[0];
        int localMaxProfit = max-min;
        int globalMaxProfit = localMaxProfit;
        for(int i=1; i < prices.length; i++) {
            if(prices[i] <= min) {
                //Reset min & max
                min = prices[i];
                max = prices[i];
                localMaxProfit = max-min;
            } else if (prices[i] >= max) {
                // Reset max
                max = prices[i];
                localMaxProfit = max - min;
            }
            // Calculate globalMaxProfit
            globalMaxProfit = Math.max(localMaxProfit, globalMaxProfit);
        }
        return globalMaxProfit;
    }

    public static void main(String[] args){
        int[] prices = {7,1,5,3,6,4};
        StockBuySell stockBuySell = new StockBuySell();
        log.info("MaxProfit from a stock with given prices {}  is  : {} ", prices, stockBuySell.maxProfit(prices));
    }
}
