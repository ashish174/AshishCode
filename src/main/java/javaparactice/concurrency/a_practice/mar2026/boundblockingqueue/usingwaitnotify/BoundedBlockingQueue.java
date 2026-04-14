package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingwaitnotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ClassicApproach: using synchronized, wait(), notifyAll()
 *
 * Here we have used notifyAll() instead of notify().
 * bcoz same lock is used for 2 functions : checkFull and checkEmpty.
 * ex:
 * Queue of size 1,
 * P1 & P2 both waititng, C1 executing, C2 waiting.
 * Assume C1 notify, queue is empty, but C2 wake up, instead of P1 or P2.
 * But now queue is empty, so C2 remain waiting (and P1, P2 are already waiting).
 * That s why, we use notifyAll to wakeup all waiting threads,
 * and they will check again on their while loop to see if their condition satisfies.
 *
 * notifyAll() wakes all waiting threads, and each thread re-checks its condition in
 *   the while loop before continuing. This avoids missed progress and liveness bugs in the wait/notify design.
 *
 * Alternatively, you can use 2 locks for 2 functions using Renetrant
 *
 */
public class BoundedBlockingQueue<T> {
    // The underlying storage is a plain queue.
    private final Queue<T> queue = new LinkedList<>();
    // Capacity is fixed once the queue is created.
    private final int capacity;
    // Closed means no more producers are allowed to insert new work.
    private boolean closed;

    public BoundedBlockingQueue(int capacity) {
        // A bounded queue with zero or negative size is invalid.
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        // Reject any new item once the queue has been closed.
        if (closed) {
            throw new IllegalStateException("queue is closed");
        }

        // Use while, not if, because wakeups can be spurious and multiple
        // producer threads may compete after a notifyAll.
        while (queue.size() == capacity) {
            wait();

            // Re-check closure after waking up.
            if (closed) {
                throw new IllegalStateException("queue is closed");
            }
        }

        // Insert the item after the queue has available space.
        queue.add(item);
        // Wake all waiters because both producers and consumers may be blocked
        // on the same monitor for different reasons.
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        // Consumers wait while there is no data and producers may still add more.
        while (queue.isEmpty() && !closed) {
            wait();
        }

        // Returning null here is the end-of-stream signal:
        // queue is empty and no future producer will add more work.
        if (queue.isEmpty()) {
            return null;
        }

        // Remove one item for the consumer.
        T item = queue.poll();
        // Wake all waiters because producers may now have free space.
        notifyAll();
        return item;
    }

    public synchronized void close() {
        // Closing does not discard queued work; it only prevents future puts.
        closed = true;
        // Wake blocked consumers and producers so they can observe closure.
        notifyAll();
    }

    public synchronized boolean isClosed() {
        return closed;
    }

    public synchronized int size() {
        return queue.size();
    }
}
