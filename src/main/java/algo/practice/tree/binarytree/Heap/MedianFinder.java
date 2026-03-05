package algo.practice.tree.binarytree.Heap;

import java.util.Queue;

/**
 * Find Median From Data Stream
 *
 * The median is the middle value in a sorted list of integers. For lists of even length, there
 * is no middle value, so the median is the mean of the two middle values.
 *
 * For example:
 *
 * For arr = [1,2,3], the median is 2. For arr = [1,2], the median is (1 + 2) / 2 = 1.5 Implement
 * the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object. void addNum(int num) adds the integer num
 * from the data stream to the data structure. double findMedian() returns the median of all
 * elements so far. Example 1:
 *
 * Input: ["MedianFinder", "addNum", "1", "findMedian", "addNum", "3" "findMedian", "addNum",
 * "2", "findMedian"]
 *
 * Output: [null, null, 1.0, null, 2.0, null, 2.0]
 *
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 *
 * -100,000 <= num <= 100,000 findMedian will only be called after adding at least one integer to
 * the data structure.
 *
 * Approach:
 * Maintain two heaps: max heap for smaller half and min heap for larger half.
 * Balance heaps to ensure size difference <= 1. addNum() ensures that.
 * Median is top of max heap or average of tops of both heaps. findMedian() does that.
 *
 * Time Complexity: O(log n) for addNum and O(1) for findMedian.
 * Space Complexity: O(n) for the heaps.
 */
public class MedianFinder {
  // Max heap to store the smaller half of the numbers
  Queue<Integer> maxHeap;
  // Min heap to store the larger half of the numbers
  Queue<Integer> minHeap;

  public MedianFinder() {
    // Initialize max heap with a custom comparator to make it a max heap
    maxHeap = new java.util.PriorityQueue<>(((o1, o2) -> o2 - o1));
    // Initialize min heap (default is min heap)
    minHeap = new java.util.PriorityQueue<>();
  }

  public void addNum(int num) {
    // If max heap has more elements than min heap
    if (maxHeap.size() > minHeap.size()) {
      if (maxHeap.size() > 0 && num < maxHeap.peek()) {
        int tmp = maxHeap.poll();
        maxHeap.add(num);
        minHeap.add(tmp);
      } else {
        minHeap.add(num);
      }
    } else {
      // max heap has <= elements than min heap
      // If num is larger than the min heap top, swap it with the top
      if (minHeap.size() > 0 && num > minHeap.peek()) {
        int tmp = minHeap.poll();
        maxHeap.add(tmp);
        minHeap.add(num);
      } else {
        maxHeap.add(num);
      }
    }
  }

  public void addNumV2(int num) {
    maxHeap.add(num);
    minHeap.add(maxHeap.poll());
    if (maxHeap.size() < minHeap.size()) {
      maxHeap.add(minHeap.poll());
    }
  }

  public double findMedian() {
    return maxHeap.size() == minHeap.size()
        ? ((maxHeap.peek() + minHeap.peek()) / 2.0)
        : maxHeap.peek();
  }
}
