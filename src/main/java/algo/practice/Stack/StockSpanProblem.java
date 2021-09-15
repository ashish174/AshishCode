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
