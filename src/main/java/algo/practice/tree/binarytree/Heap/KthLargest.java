package algo.practice.tree.binarytree.Heap;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Design a class to find the kth largest integer in a stream of values, including duplicates. E.g. the 2nd largest from [1, 2, 3, 3] is 3. The stream is not necessarily sorted.
 *
 * Implement the following methods:
 *
 * constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
 * int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.
 * Example 1:
 *
 * Input:
 * ["KthLargest", [3, [1, 2, 3, 3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]
 *
 * Output:
 * [null, 3, 3, 3, 5, 6]
 *
 * Constraint: There will always be at least k integers in the stream when you search for the kth integer.
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
@Slf4j
class KthLargest {

    Queue<Integer> minHeap;
    private int heapSize;
    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>();
        heapSize = k;
        for(int num : nums){
            minHeap.add(num);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }

    public int add(int val) {
        minHeap.add(val);
        if(minHeap.size() > heapSize){
            minHeap.poll();
        }
        return minHeap.peek();
    }

    public static void main(String[] args){
        log.info("Kth largest : {}", new KthLargest(1, new int[0]).add(3));
    }
}