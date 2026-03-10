package algo.practice.a_common_code_functions.concurrency;

/**
 * Synchronized based locks:  Intrinsic Lock (synchronized) – Basic lock
 *
 * // Method level lock (on object lock or object level monitor)
 * class Counter {
 *     private int count = 0;
 *
 *     public synchronized void increment() {
 *         count++;
 *     }
 * }
 *
 * // Block level lock (can be acquired on current object monitor or any object monitor)
 * synchronized(this) {
 *     count++;
 * }
 *
 * // Static lock (Class lock)
 * public static synchronized void test() {
 * }
 *
 * When to use synchronized
 *
 * ✅ Simple cases
 * ✅ Low contention
 * ✅ Small critical section
 * ❌ Not good for high performance systems
 *
 * #########################################################################################
 *
 * ReentrantLock (java.util.concurrent.locks)
 * More powerful than synchronized.
 *
 * import java.util.concurrent.locks.ReentrantLock;
 *
 * class Counter {
 *     private int count = 0;
 *     private ReentrantLock lock = new ReentrantLock();
 *
 *     public void increment() {
 *         lock.lock();
 *         try {
 *             count++;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 * }
 *
 * Why ReentrantLock?
 * ✔ tryLock
 * ✔ timeout
 * ✔ fairness
 * ✔ interruptible
 * ✔ multiple conditions
 *
 * tryLock example
 * if(lock.tryLock()) {
 *     try {
 *         count++;
 *     } finally {
 *         lock.unlock();
 *     }
 * }
 *
 * Fair lock
 * ReentrantLock lock = new ReentrantLock(true);
 *
 * When to use ReentrantLock
 *
 * ✅ High concurrency
 * ✅ Need timeout / tryLock
 * ✅ Need fairness
 * ✅ Complex locking logic
 *
 * #########################################################################################
 *
 * ReadWriteLock (ReentrantReadWriteLock)
 * Used when many reads, few writes.
 * Allows:
 *  - Multiple readers
 *  - Single writer
 *
 * import java.util.concurrent.locks.*;
 *
 * class Cache {
 *
 *     private final ReadWriteLock lock =
 *         new ReentrantReadWriteLock();
 *
 *     private int data;
 *
 *     public int read() {
 *         lock.readLock().lock();
 *         try {
 *             return data;
 *         } finally {
 *             lock.readLock().unlock();
 *         }
 *     }
 *
 *     public void write(int value) {
 *         lock.writeLock().lock();
 *         try {
 *             data = value;
 *         } finally {
 *             lock.writeLock().unlock();
 *         }
 *     }
 * }
 *
 * When to use ReadWriteLock
 * ✅ Cache
 * ✅ Map
 * ✅ DB read heavy
 * ✅ Config objects
 * Interview line:
 * Use ReadWriteLock when reads >> writes
 *
 *
 * #########################################################################################
 * Volatile :-
 * volatile boolean running = true;
 * When you want to read value from memory directly than cache.
 *
 * #########################################################################################
 * Atomic classes (lock-free)
 *
 * AtomicInteger count = new AtomicInteger();
 * count.incrementAndGet();
 *
 * Use when:
 * ✅ Simple counters
 * ✅ No blocking needed
 * ✅ High performance
 *
 * #########################################################################################
 * Semaphore (permit based locking)
 *
 * // Allows N threads.
 *
 * Semaphore sem = new Semaphore(2);
 *
 * sem.acquire();
 * try {
 *     // critical
 * } finally {
 *     sem.release();
 * }
 *
 * Use when:
 * ✅ Connection pool
 * ✅ Limited resources
 * ✅ Rate limiter
 *
 *
 **/
public class LockSample {}
