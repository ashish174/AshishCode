package algo.practice.tree.binarytree.Heap;

import java.util.Queue;

/**
 * Find Median From Data Stream
 *
 * <p>The median is the middle value in a sorted list of integers. For lists of even length, there
 * is no middle value, so the median is the mean of the two middle values.
 *
 * <p>For example:
 *
 * <p>For arr = [1,2,3], the median is 2. For arr = [1,2], the median is (1 + 2) / 2 = 1.5 Implement
 * the MedianFinder class:
 *
 * <p>MedianFinder() initializes the MedianFinder object. void addNum(int num) adds the integer num
 * from the data stream to the data structure. double findMedian() returns the median of all
 * elements so far. Example 1:
 *
 * <p>Input: ["MedianFinder", "addNum", "1", "findMedian", "addNum", "3" "findMedian", "addNum",
 * "2", "findMedian"]
 *
 * <p>Output: [null, null, 1.0, null, 2.0, null, 2.0]
 *
 * <p>Explanation: MedianFinder medianFinder = new MedianFinder(); medianFinder.addNum(1); // arr =
 * [1] medianFinder.findMedian(); // return 1.0 medianFinder.addNum(3); // arr = [1, 3]
 * medianFinder.findMedian(); // return 2.0 medianFinder.addNum(2); // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0 Constraints:
 *
 * <p>-100,000 <= num <= 100,000 findMedian will only be called after adding at least one integer to
 * the data structure.
 */
public class MedianFinder {
  Queue<Integer> maxHeap;
  Queue<Integer> minHeap;

  public MedianFinder() {
    maxHeap = new java.util.PriorityQueue<>(((o1, o2) -> o2 - o1));
    minHeap = new java.util.PriorityQueue<>();
  }

  public void addNum(int num) {
    if (maxHeap.size() > minHeap.size()) {
      if (maxHeap.size() > 0 && num < maxHeap.peek()) {
        int tmp = maxHeap.poll();
        maxHeap.add(num);
        minHeap.add(tmp);
      } else {
        minHeap.add(num);
      }
    } else {
      if (minHeap.size() > 0 && num > minHeap.peek()) {
        int tmp = minHeap.poll();
        maxHeap.add(tmp);
        minHeap.add(num);
      } else {
        maxHeap.add(num);
      }
    }
  }

  public double findMedian() {
    return maxHeap.size() == minHeap.size()
        ? ((maxHeap.peek() + minHeap.peek()) / 2.0)
        : maxHeap.peek();
  }
}
