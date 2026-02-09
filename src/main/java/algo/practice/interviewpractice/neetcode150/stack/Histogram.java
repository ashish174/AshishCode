package algo.practice.interviewpractice.neetcode150.stack;

import java.util.Stack;

/**
 * You are given an array of integers heights where heights[i] represents the height of a bar. The width of each bar is 1.
 *
 * Return the area of the largest rectangle that can be formed among the bars.
 *
 * Note: This chart is known as a histogram.
 *
 * Input: heights = [7,1,7,2,2,4]
 *
 * Output: 8
 */
public class Histogram {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        // An stack to store pairs (start_index, height)
        // Ensure stack has height in increasing order.
        Stack<int[]> startIndexWithHeightStack = new Stack<>();
        for(int i=0; i<heights.length; i++){
            // Initialize the start index of the current bar to its own index
            int start=i;
            //Process prev elems if curr elem height is smaller
            //Process the bars in the stack that are taller than the current bar
            //This is done by popping them from the stack and calculating the area of the rectangle that can be formed using the popped bar
            while(!startIndexWithHeightStack.isEmpty()
                    && startIndexWithHeightStack.peek()[1] > heights[i]) {
                int[] top = startIndexWithHeightStack.pop();
                int startIndex = top[0];
                int ht = top[1];
                // Calculate the area of the rectangle that can be formed using the popped top bar
                // update the max Area with Popped bar
                maxArea = Math.max(maxArea, ht*(i-startIndex));
                // Update the start index to the start index of the top bar
                // This is because the current bar can potentially be part of a rectangle that starts at the same index as the top bar
                start = startIndex;
            }
            // Push the current bar onto the stack
            // The start index of the current bar is the updated start index
            startIndexWithHeightStack.push(new int[]{start, heights[i]});
        }

        // Process the remaining bars in the stack
        // These are the bars that are still "active" after iterating through the entire histogram
        for(int[] pair : startIndexWithHeightStack){
            int startIndex = pair[0];
            int ht = pair[1];
            // Calculate the area of the rectangle that can be formed using the bar
            // The width of the rectangle is the difference between the length of the histogram and the start index of the bar
            maxArea = Math.max(maxArea, ht * heights.length-startIndex);
        }
        return maxArea;

    }

}
