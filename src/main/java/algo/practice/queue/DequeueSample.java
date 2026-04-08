package algo.practice.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A Deque in Java means Double Ended Queue.
 * It is a data structure where you can add or remove elements from both ends:
 * - front
 * - back
 *
 * So it can behave like both:
 * a queue (FIFO: first in, first out)
 * a stack (LIFO: last in, first out)
 *
 * Dequeue is an interface with 2 implementations
 * - ArrayDeque
 * - LinkedList
 *
 * Deque is very useful in DSA problems like:
 * - sliding window maximum
 * - monotonic queue
 * - BFS
 * - palindrome checks
 *
 *
 * Use Deque when:
 * - you need stack behavior
 * - you need queue behavior
 * - you need insertion/removal from both ends
 * - you are solving window-based or BFS-style problems
 *
 */
public class DequeueSample {
    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();

        deque.addFirst(10);  // front
        deque.addLast(20);   // back
        deque.addFirst(5);

        System.out.println(deque); // [5, 10, 20]

        deque.peekFirst();   // returns null if empty
        deque.peekLast();

        System.out.println(deque.removeFirst()); // 5
        System.out.println(deque.removeLast());  // 20

        System.out.println(deque); // [10]


    }
}
