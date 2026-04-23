package javaparactice.concurrency.usingLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * While await() immediately give up the lock.
 * But signal() does not transfer the lock immediately. The signaling thread still keeps the lock until it unlocks:
 *
 *
 * await()/wait() → immediately releases lock and waits.
 * signal()/notify() → wakes a waiter, but does not release lock until unlock is called or synchronized block finishes
 *
 */
public class LockWithCondition {
    private final Lock lock = new ReentrantLock();
    private final Condition readyCondition = lock.newCondition();
    private boolean ready = false;

    public void awaitReady() throws InterruptedException {
        lock.lock();
        try {
            //always use while loop instead of if, bcoz thread can do spurious wake up, or other thread already consumed the obj
            while (!ready) {
                readyCondition.await();
            }
            System.out.println("Proceeding...");
        } finally {
            lock.unlock();
        }
    }

    public void markReady() {
        lock.lock();
        try {
            ready = true;
            readyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
