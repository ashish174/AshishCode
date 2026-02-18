package algo.practice.a_interviewpractice.neetcode150.twopointers;

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
 *
 * Approach:
 * Try finding a better buying price, and if you discover a lower price, update buying price to the lower price
 *
 * Use two-pointer or linear scan to track the minimum price seen so far and at each step compute the profit
 * by selling at the current price. Update the maximum profit whenever a larger profit is found.
 * - For each day, either update the minimum buying price, or calculate potential profit and update max profit.
 * - Only one buy and one sell are allowed (must buy before sell).
 * - Time complexity is O(n); optimal one-pass solution.
 */
@Slf4j
public class StockBuySell {

  public int maxProfitV2(int[] prices) {
    int buyDay = 0;
    int saleDay = 1;
    int maxProfit = 0;
    while (saleDay < prices.length) {
      // If the price at r is higher than at l, we can make a profit — so we update the maximum.
      // If the price at r is lower, then r becomes the new l because a cheaper buying price is
      // always better.
      if (prices[buyDay] > prices[saleDay]) {
        buyDay = saleDay;
      } else {
        int profit = prices[saleDay] - prices[buyDay];
        maxProfit = Math.max(maxProfit, profit);
      }
      saleDay++;
    }
    return maxProfit;
  }

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
        log.info("MaxProfit V2 from a stock with given prices {}  is  : {} ", prices, stockBuySell.maxProfitV2(prices));
    }
}
