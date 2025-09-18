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
