package algo.practice.a_interviewpractice.neetcode150.arraysandhashing;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 *
 * Output: [1,2]
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 *
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 *
 * Output: [1,2]
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 *
 *
 * Approach
 * -------
 * 1. Count frequencies:
 *    Scan the array once and store value → frequency in a HashMap.
 *    This step costs O(n) where n = nums.length.
 *
 * 2. Keep only the K biggest frequencies:
 *    Use a min‑heap (PriorityQueue) that stores map entries ordered by their frequency.
 *    While iterating over the map entries, push each entry onto the heap.
 *    When the heap size exceeds k, remove the smallest element.
 *    After the loop the heap contains exactly the k entries with the highest frequencies.
 *    Each insertion/removal costs O(log k), so this phase is O(m log k) where
 *    m is the number of distinct values (m ≤ n).
 *
 * 3. Extract the result:
 *    Pop all elements from the heap into an int[] result array.
 *    Because the required order is unspecified we can return the values in any order.
 *
 * Complexity
 * ----------
 *   Time   : O(n + m log k)  (worst case O(n log k))
 *   Space  : O(m) for the frequency map + O(k) for the heap.
 *
 * Alternative solutions such as bucket sort or quick‑select can achieve O(n) time,
 * but the heap solution is simple, easy to understand and works well for the
 * given limits (n ≤ 100 000).
 *
 *
 * **/
public final class TopKFrequentElement {

    private TopKFrequentElement() {
        // Utility class – prevent instantiation
    }

    /**
     * Returns the k most frequent elements in the given array.
     *
     * @param nums input array, length 1 … 100 000
     * @param k    number of elements to return, 1 ≤ k ≤ number of distinct values
     * @return an int[] of length k containing the most frequent values
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // --------------------------------------------------------------
        // 1. Build frequency map: value → count
        // --------------------------------------------------------------
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // --------------------------------------------------------------
        // 2. Min‑heap that keeps the k entries with the highest counts.
        //    The comparator orders by count ascending, so the smallest
        //    frequency is at the top and removed when size > k.
        // --------------------------------------------------------------
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                // Discard the entry with the smallest frequency
                minHeap.poll();
            }
        }

        // --------------------------------------------------------------
        // 3. Extract the keys from the heap into the result array.
        //    Order does not matter – we simply pop whatever order the heap gives.
        // --------------------------------------------------------------
        int[] result = new int[k];
        int idx = 0;
        while (!minHeap.isEmpty()) {
            result[idx++] = minHeap.poll().getKey();
        }

        return result;
    }

    // --------------------------------------------------------------
    // Simple demonstration (replace with unit tests in production)
    // --------------------------------------------------------------
    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int k1 = 2;
        System.out.println(java.util.Arrays.toString(topKFrequent(nums1, k1))); // e.g. [1, 2]

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(java.util.Arrays.toString(topKFrequent(nums2, k2))); // [1]

        int[] nums3 = {1, 2, 1, 2, 1, 2, 3, 1, 3, 2};
        int k3 = 2;
        System.out.println(java.util.Arrays.toString(topKFrequent(nums3, k3))); // e.g. [1, 2]
    }
}
