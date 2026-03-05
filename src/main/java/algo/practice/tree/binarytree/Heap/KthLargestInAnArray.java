package algo.practice.tree.binarytree.Heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.
 *
 * By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.
 *
 * Follow-up: Can you solve it without sorting?
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,5,4], k = 2
 *
 * Output: 4
 * Example 2:
 *
 * Input: nums = [2,3,1,1,5,5,4], k = 3
 *
 * Output: 4
 *
 * Approach:
 * 1. Use a min heap to store k largest integers.
 * 2. In constructor, initialize min heap with first k integers.
 * 3. In add method, add new integer to min heap and remove smallest if size > k.
 * 4. Return the kth largest integer from largest value backward (min heap top).
 *
 * Time Complexity: O(log k) for add operation.
 * Space Complexity: O(k) for the min heap.
 */
public class KthLargestInAnArray {
    public int findKthLargest(int[] nums, int k) {
    Queue<Integer> minHeap = new PriorityQueue<>();
        for(int num : nums) {
            minHeap.add(num);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

}
