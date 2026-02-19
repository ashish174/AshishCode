package algo.practice.Stack;


import java.util.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * we have a series of n daily price quotes for a stock and
 * we need to calculate span of stock’s price for all n days.
 * i.e. Number of consecutive days till current when prices were moving up
 * <p>
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85},
 * then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 *
 * Stock Span Problem
 * Problem Statement:
 * You are given a list of daily prices for a stock over n days. For each day, the stock span is defined as the maximum number of consecutive days (up to and including today) the price of the stock has been less than or equal to its price on the current day.
 * Write a function to calculate the span for each day
 * Example
 * Input:  prices = [100, 80, 60, 70, 60, 75, 85]
 * Output: [1, 1, 1, 2, 1, 4, 6]
 * Explanation:
 * - Day 1 (price 100): Only one day (itself), so span is 1.
 * - Day 2 (price 80): Price less than previous, so span is 1.
 * - Day 3 (price 60): Price less than previous, so span is 1.
 * - Day 4 (price 70): Price greater than previous (60), so count 2 (including day 3), span is 2.
 * - Day 5 (price 60): Price less than previous, so span is 1.
 * - Day 6 (price 75): Price greater than previous (60 and 70), so count 4 (days 5, 4, 3, and itself), span is 4.
 * - Day 7 (price 85): Price greater than previous days (75, 60, 70, 60, 80), count 6, span is 6.
 *
 * Approach:
 * Uses a monotonic stack to keep track of indices where the price is higher than the current day's price.
 * For each day, pops indices from the stack until a higher price is found or the stack is empty.
 * The span for each day is calculated as the difference between the current day and the index at the top of the stack,
 * or the entire range if the stack is empty. This ensures O(n) computation for all days.
 *
 */
public class StockSpanProblem {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockSpanProblem.class);

    public static void calculateSpan(int[] price, int n, int[] S) {

        // This stack with store the index of the price array
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        S[0] = 1;
        // Pop elements from stack while stack is not
        // empty and top of stack is smaller than
        // price[i]
        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty()
                    && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            // If stack becomes empty, then price[i] is
            // greater than all elements on left of it, i.e.,
            // price[0], price[1], ..price[i-1]. Else price[i]
            // is greater than elements after top of stack
            S[i] = stack.isEmpty() ? (i + 1) : i - stack.peek();
            stack.push(i);
        }
    }

    public static void main(String[] args) {
        int[] price = new int[]{100, 80, 60, 70, 60, 75, 85};
        int[] S = new int[price.length];
        calculateSpan(price, price.length, S);
        LOGGER.info("Price By Day : {}", price);
        LOGGER.info("Stock Span : {}", S);
    }
}
