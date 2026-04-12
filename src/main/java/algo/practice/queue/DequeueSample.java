package algo.practice.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

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
 * - ArrayDeque (Backed by a resizable circular array with head & tail. When the array fills up, Java allocates a larger array and copies elements over.)
 * - LinkedList (Backed by a doubly linked list with head and tail)
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
        Deque<Integer> dequeUsingArray = new ArrayDeque<>();
        Deque<Integer> dequeUsingList = new LinkedList<>();

        dequeUsingArray.addFirst(10);  // front
        dequeUsingArray.addLast(20);   // back
        dequeUsingArray.addFirst(5);

        System.out.println(dequeUsingArray); // [5, 10, 20]


        System.out.println(dequeUsingArray.removeFirst()); // 5
        System.out.println(dequeUsingArray.removeLast());  // 20

        System.out.println(dequeUsingArray); // [10]

        //Use as stack
        dequeUsingArray.push(3);
        dequeUsingArray.pop();

        //use as queue
        dequeUsingArray.add(4);
        dequeUsingArray.remove();

        dequeUsingArray.peekFirst();   // returns null if empty
        dequeUsingArray.peekLast();

        dequeUsingArray.pollFirst();
        dequeUsingArray.pollLast();


    }
}
