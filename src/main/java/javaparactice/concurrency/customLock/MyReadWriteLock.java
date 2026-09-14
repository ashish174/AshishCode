package javaparactice.concurrency.customLock;

/**
 * To reduce writer starvation, a common strategy:
 * Track how many writers are waiting.
 * If writers are waiting, block new readers, even if no writer currently holds the lock.
 *
 */
public class MyReadWriteLock {
    private int readers = 0;
    private int writers = 0;          // 0 or 1
    private int waitingWriters = 0;   // count of writers waiting for the lock

    // Acquire read lock: wait if there's an active writer OR waiting writers
    public synchronized void lockRead() throws InterruptedException {
        while (writers > 0 || waitingWriters > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        if (readers <= 0) {
            throw new IllegalStateException("No reader to unlock");
        }
        readers--;
        if (readers == 0) {
            // Wake writers (or readers) that may be waiting
            notifyAll();
        }
    }

    // Acquire write lock: wait while readers or writer are active
    public synchronized void lockWrite() throws InterruptedException {
        waitingWriters++;
        try {
            while (readers > 0 || writers > 0) {
                wait();
            }
            writers = 1;
        } finally {
            // Once we've acquired the write lock, we're no longer 'waiting'
            waitingWriters--;
        }
    }

    public synchronized void unlockWrite() {
        if (writers != 1) {
            throw new IllegalStateException("No writer to unlock");
        }
        writers = 0;
        // Wake up both waiting readers and writers.
        notifyAll();
    }
}
