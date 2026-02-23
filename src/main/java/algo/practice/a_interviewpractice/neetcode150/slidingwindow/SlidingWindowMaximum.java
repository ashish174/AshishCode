package algo.practice.a_interviewpractice.neetcode150.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an array of integers nums and an integer k.
 * There is a sliding window of size k that starts at the left edge of the array.
 * The window slides one position to the right until it reaches the right edge of the array.
 *
 * Return a list that contains the maximum element in the window at each step.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,0,4,2,6], k = 3
 *
 * Output: [2,2,4,4,6]
 *
 * Explanation:
 * Window position            Max
 * ---------------           -----
 * [1  2  1] 0  4  2  6        2
 *  1 [2  1  0] 4  2  6        2
 *  1  2 [1  0  4] 2  6        4
 *  1  2  1 [0  4  2] 6        4
 *  1  2  1  0 [4  2  6]       6
 *
 * Deque = Double-Ended Queue
 *
 * Approach:
 * Use a deque to keep indexes of useful elements for each window.
 * - The deque stores indexes in decreasing order of their values (front has largest).
 * - Remove indexes from front if they are out of the window's left boundary.
 * - Remove indexes from back while the current element is larger, to always keep max at the front.
 * - For each window, the element at the front of the deque is the window maximum.
 * Time complexity: O(n), each element added and removed at most once.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1]; // Output array of window maximums
        Deque<Integer> deque = new LinkedList<>(); // Stores indices

        for (int i = 0; i < n; i++) {
            // Remove from front if index is out of current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // Remove from back all elements less than the current one
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // Add current element's index
            deque.offerLast(i);

            // Once the first window is formed (i >= k-1), add max to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;

    }

    // Optional: Simple main for quick, manual testing
    public static void main(String[] args) {
        SlidingWindowMaximum solution = new SlidingWindowMaximum();
        int[] nums = {1,2,1,0,4,2,6};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
