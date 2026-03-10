package lru.V3;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class MassiveLRUCache<K, V> {

    private static class CacheNode<K, V> implements Comparable<CacheNode<K, V>> {
        K key;
        V value;
        long expiryTime;
        CacheNode<K, V> prev, next;

        CacheNode(K key, V value, long ttlMillis) {
            this.key = key;
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        // PriorityQueue uses this to keep the "soonest to expire" at the top
        @Override
        public int compareTo(CacheNode<K, V> other) {
            return Long.compare(this.expiryTime, other.expiryTime);
        }
    }

    private final int capacity;
    private final Map<K, CacheNode<K, V>> map;
    private final CacheNode<K, V> head, tail;
    private final PriorityQueue<CacheNode<K, V>> expirationQueue;

    // Using a single ReentrantLock for simplicity in this refined version
    private final ReentrantLock lock = new ReentrantLock();

    public MassiveLRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.expirationQueue = new PriorityQueue<>();

        head = new CacheNode<>(null, null, 0);
        tail = new CacheNode<>(null, null, 0);
        head.next = tail;
        tail.prev = head;

        startBackgroundCleanup();
    }

    public void put(K key, V value, long ttlMillis) {
        lock.lock();
        try {
            CacheNode<K, V> node = map.get(key);
            if (node != null) {
                // Update logic: Remove from PQ because expiryTime changes
                expirationQueue.remove(node);
                node.value = value;
                node.expiryTime = System.currentTimeMillis() + ttlMillis;
                moveToHead(node);
            } else {
                if (map.size() >= capacity) {
                    evictLRU();
                }
                node = new CacheNode<>(key, value, ttlMillis);
                map.put(key, node);
                addToHead(node);
            }
            expirationQueue.add(node); // O(log N) - much better than O(N) scan
        } finally {
            lock.unlock();
        }
    }

    public V get(K key) {
        lock.lock();
        try {
            CacheNode<K, V> node = map.get(key);
            if (node == null || System.currentTimeMillis() > node.expiryTime) {
                if (node != null) removeFully(node);
                return null;
            }
            moveToHead(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Optimized Eviction: Only processes items that have actually expired.
     * O(log N) per expired item, rather than O(N) for the whole cache.
     */
    private void startBackgroundCleanup() {
        Thread cleanupThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    lock.lock();
                    try {
                        long now = System.currentTimeMillis();
                        while (!expirationQueue.isEmpty() && expirationQueue.peek().expiryTime <= now) {
                            CacheNode<K, V> expiredNode = expirationQueue.poll();
                            removeFully(expiredNode);
                        }
                    } finally {
                        lock.unlock();
                    }
                    // Sleep for a bit to avoid CPU spinning
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        cleanupThread.setDaemon(true);
        cleanupThread.start();
    }

    private void removeFully(CacheNode<K, V> node) {
        if (node == null) return;
        map.remove(node.key);
        expirationQueue.remove(node); // Important to keep PQ in sync
        removeNodeFromList(node);
    }

    private void evictLRU() {
        CacheNode<K, V> lru = tail.prev;
        if (lru != head) {
            removeFully(lru);
        }
    }

    // Standard DLL helpers...
    private void addToHead(CacheNode<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNodeFromList(CacheNode<K, V> node) {
        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
    }

    private void moveToHead(CacheNode<K, V> node) {
        removeNodeFromList(node);
        addToHead(node);
    }
}