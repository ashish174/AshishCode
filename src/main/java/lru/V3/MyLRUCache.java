package lru.V3;

/**
 * Rank #1: Thread-Safe LRU Cache with TTL
 * The Problem
 * Design and implement an LRU (Least Recently Used) Cache that is thread-safe.
 *
 * Requirements:
 *
 * get(K key): Returns the value if present, otherwise returns null.
 *
 * put(K key, V value, long ttlMillis): Inserts or updates the value with an expiration time.
 *
 * Eviction: If the cache reaches capacity, the least recently used item must be removed.
 *
 * Expiration: Items must be inaccessible once their TTL has passed.
 *
 * In Java 8/11, the most robust way to handle this is
 * using a ReentrantReadWriteLock to allow multiple concurrent readers but exclusive access for writers,
 * and a ScheduledExecutorService for the background TTL cleanup.
 *
 *
 * **/
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 * A Thread-safe LRU Cache with Time-To-Live (TTL) support.
 * Suitable for SDE3 level discussions on lock granularity and resource management.
 */
public class MyLRUCache<K, V> {

    // Internal class to wrap the value with its expiration timestamp
    private static class CacheNode<K, V> {
        K key;
        V value;
        long expiryTime;
        CacheNode<K, V> prev, next;

        CacheNode(K key, V value, long ttlMillis) {
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }
    }

    private final int capacity;
    private final Map<K, CacheNode<K, V>> map;
    private final CacheNode<K, V> head, tail;

    // ReadWriteLock allows multiple readers but only one writer
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    // Background thread to clean up expired entries (Prime Video style resource management)
    private final ScheduledExecutorService cleanUpService = Executors.newSingleThreadScheduledExecutor();

    public MyLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        // Dummy head and tail to avoid null checks in DLL logic
        head = new CacheNode<>(null, null, 0);
        tail = new CacheNode<>(null, null, 0);
        head.next = tail;
        tail.prev = head;

        // Schedule periodic cleanup every 5 seconds
        cleanUpService.scheduleAtFixedRate(this::evictExpired, 5, 5, TimeUnit.SECONDS);
    }

    /**
     * Retrieve a value. Moves the node to the front (Most Recently Used).
     */
    public V get(K key) {
        writeLock.lock(); // Need write lock because "get" modifies the order of the DLL
        try {
            CacheNode<K, V> node = map.get(key);
            if (node == null || isExpired(node)) {
                if (node != null) removeNodeFromMapAndList(node);
                return null;
            }
            moveToHead(node);
            return node.value;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Adds or updates an entry. If at capacity, evicts the Least Recently Used.
     */
    public void put(K key, V value, long ttlMillis) {
        writeLock.lock();
        try {
            CacheNode<K, V> existingNode = map.get(key);
            if (existingNode != null) {
                // Update existing node
                existingNode.value = value;
                existingNode.expiryTime = System.currentTimeMillis() + ttlMillis;
                moveToHead(existingNode);
            } else {
                // Check capacity
                if (map.size() >= capacity) {
                    removeNodeFromMapAndList(tail.prev); // Evict LRU (tail.prev)
                }
                CacheNode<K, V> newNode = new CacheNode<>(key, value, ttlMillis);
                addToHead(newNode);
                map.put(key, newNode);
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Proactive cleanup of expired keys.
     * Better approach would be to store in a sortedSet score by TTL.(or can use PriorityQueue)
     * Then we can remove old scores
     */
    private void evictExpired() {
        writeLock.lock();
        try {
            long now = System.currentTimeMillis();
            // In a real SDE3 interview, mention that iterating the whole map
            // under a write lock is O(N). Optimization: Use a PriorityQueue for TTL.
            Iterator<Map.Entry<K, CacheNode<K, V>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                CacheNode<K, V> node = it.next().getValue();
                if (node.expiryTime < now) {
                    removeNodeFromList(node);
                    it.remove();
                }
            }
        } finally {
            writeLock.unlock();
        }
    }

    // --- Helper Methods (Assume Write Lock is held) ---

    private boolean isExpired(CacheNode<K, V> node) {
        return System.currentTimeMillis() > node.expiryTime;
    }

    private void addToHead(CacheNode<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNodeFromList(CacheNode<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(CacheNode<K, V> node) {
        removeNodeFromList(node);
        addToHead(node);
    }

    private void removeNodeFromMapAndList(CacheNode<K, V> node) {
        map.remove(node.key);
        removeNodeFromList(node);
    }

    /**
     * Always offer to shut down the executor service in SDE3 interviews.
     */
    public void shutdown() {
        cleanUpService.shutdown();
    }
}
