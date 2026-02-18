package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

/**
 * Tell us best day to buy and sell where profit is maximized.
 * Return the max profit.
 *
 * You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
 * You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
 * Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.
 *
 * Input: prices = [10,1,5,6,7,1]
 * Output: 6
 *
 * This can be done with other approach as well.
 * Like find suffixArray with MaxValue, and then start checking buyday from 0 to n-1
 *
 *
 */
public class StockBuyAndSell {
    public int maxProfit(int[] prices) {
        // Initialize two pointers, l and r, to represent the day to buy and sell the stock respectively.
        int l = 0;
        int r = 1;
        // Initialize a variable to store the maximum profit seen so far.
        int maxProfit = 0;
        while(r < prices.length) {
            // If the price at l is greater than the price at r, it means we've found a lower price at r to buy the stock.
            // So, we update l to be r.
            if(prices[l] > prices[r]) {
                l = r;
            } else {
                // Calculate the profit if we sell the stock at r.
                int profit = prices[r] - prices[l];
                // Update maxProfit if the current profit is greater.
                maxProfit = Math.max(maxProfit, profit);
            }
            // Move the sell pointer to the next day.
            r++;
        }
        return maxProfit;

    }
}
