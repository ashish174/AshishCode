package algo.practice.queue;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Slf4j
public class myQueue {
  public static void main(String[] args) {

    Queue<Integer> queue = new LinkedList<>();
    // In Java, the PriorityQueue class implements a min-heap by default.

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // In Java, the PriorityQueue class implements a min-heap by default.
    // However, you can create a max-heap using a custom comparator.
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /*
    *
    * add --> to add element to the queue. O(log n)
      remove --> to get and remove the min/max. O(log n)
      peek --> to get, but not remove the min/max. O(1)
    * */
    queue.add(1);
    queue.add(2);
    queue.add(3);
    log.info("Queue: {}", queue);
    log.info("{}", queue.peek());
    log.info("{}", queue.remove());
    log.info("Queue: {}", queue);

    minHeap.add(3);
    minHeap.add(1);
    minHeap.add(2);
    log.info("minHeapPriorityQueue: {}", minHeap);
    log.info("{}", minHeap.peek());
    log.info("{}", minHeap.remove());
    log.info("{}", minHeap.remove());
    log.info("{}", minHeap.remove());
    log.info("minHeapPriorityQueue: {}", minHeap);

    maxHeap.add(3);
    maxHeap.add(1);
    maxHeap.add(2);
    log.info("maxHeapPriorityQueue: {}", maxHeap);
    log.info("{}", maxHeap.peek());
    log.info("{}", maxHeap.remove());
    log.info("{}", maxHeap.remove());
    log.info("{}", maxHeap.remove());
    log.info("maxHeapPriorityQueue: {}", maxHeap);
  }
}
