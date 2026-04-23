package javaparactice.concurrency.usingLock;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition are used to do signalling. like wait & notify on synchronized.
 * They must be executed once lock is acquired.
 * In synchronized, you can do wait/notify on just one condition.
 * With lock, you can create multiple conditions for await & signal
 *
 * While await() immediately give up the lock.
 * But signal() does not transfer the lock immediately. The signaling thread still keeps the lock until it unlocks:
 *
 *
 * await()/wait() → immediately releases lock and sleep
 * signal()/notify() → wakes a waiter, but does not release lock until unlock is called or synchronized block finishes
 *
 * @param <T>
 */
public class BoundedQueue<T> {
    private final Deque<T> queue = new ArrayDeque<>();
    private final int capacity;

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public BoundedQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            //always use while loop instead of if, bcoz thread can do spurious wake up, or other thread already consumed the obj
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " waiting: queue full");
                notFull.await(); //lock is immediately released, and thread goes to sleep state
                //when somebody call signal, then this thread will wakeup and attempt to acquire lock.
            }

            queue.addLast(item);
            System.out.println(Thread.currentThread().getName() + " produced: " + item);

            // queue is no longer empty, wake one waiting consumer
            notEmpty.signal();
            // still holding the lock here until unlock is called
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            //always use while loop instead of if, bcoz thread can do spurious wake up, or other thread already consumed the obj
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting: queue empty");
                notEmpty.await();
            }

            T item = queue.removeFirst();
            System.out.println(Thread.currentThread().getName() + " consumed: " + item);

            // queue is no longer full, wake one waiting producer
            notFull.signal();

            return item;
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

    public static void main(String[] args) {
        BoundedQueue<Integer> bq = new BoundedQueue<>(3);

        Runnable producer = () -> {
            int value = 1;
            try {
                while (true) {
                    bq.put(value++);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                while (true) {
                    bq.take();
                    Thread.sleep(1200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer, "Producer-1").start();
        new Thread(consumer, "Consumer-1").start();
    }
}