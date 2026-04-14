package javaparactice.concurrency.a_practice.mar2026.boundblockingqueue.usingReentrantLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * "Explicit" approach : uses (ReentrantLock, Condition)
 */
public class BoundedBlockingQueue<T> {
    // The queue still uses a simple linked list as the data container.
    private final Queue<T> queue = new LinkedList<>();
    // Capacity is fixed and enforced by the producer path.
    private final int capacity;
    // Separate lock from the queue object gives us explicit concurrency control.
    private final ReentrantLock lock = new ReentrantLock();
    // Consumers wait on this condition when there is no data to read.
    private final Condition notEmpty = lock.newCondition();
    // Producers wait on this condition when the queue is full.
    private final Condition notFull = lock.newCondition();
    // Closed means no future puts are accepted.
    private boolean closed;

    public BoundedBlockingQueue(int capacity) {
        // Capacity must be strictly positive for a bounded queue.
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            // Do not allow new work after closure.
            if (closed) {
                throw new IllegalStateException("queue is closed");
            }

            // Wait while the queue is full.
            while (queue.size() == capacity) {
                notFull.await();

                // Re-check closure after waking.
                if (closed) {
                    throw new IllegalStateException("queue is closed");
                }
            }

            // Add one item and signal a waiting consumer.
            queue.add(item);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            // Wait while the queue is empty and producers may still add more data.
            while (queue.isEmpty() && !closed) {
                notEmpty.await();
            }

            // Null is the end-of-stream signal once the queue is closed and drained.
            if (queue.isEmpty()) {
                return null;
            }

            // Remove one item and signal a waiting producer.
            T item = queue.poll();
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        lock.lock();
        try {
            // Mark the queue as closed and wake both producer and consumer waiters.
            closed = true;
            notEmpty.signalAll();
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public boolean isClosed() {
        lock.lock();
        try {
            return closed;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }
}
