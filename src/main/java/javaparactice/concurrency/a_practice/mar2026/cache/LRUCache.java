package javaparactice.concurrency.a_practice.mar2026.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread-safe fixed-capacity LRU cache.
 *
 * Design:
 * - HashMap gives O(1) lookup by key.
 * - Doubly linked list gives O(1) recency updates and eviction.
 * - Head side is most recently used, tail side is least recently used.
 * - One lock protects both structures because the map and list must move
 *   together to preserve cache invariants.
 */
public final class LRUCache<K, V> implements Cache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> entries;
    private final ReentrantLock lock;

    // Sentinel nodes remove edge cases when adding or removing real nodes.
    private final Node<K, V> head;
    private final Node<K, V> tail;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }

        this.capacity = capacity;
        this.entries = new HashMap<>(capacity);
        this.lock = new ReentrantLock();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key, "key must not be null");

        lock.lock();
        try {
            Node<K, V> node = entries.get(key);
            if (node == null) {
                return null;
            }

            // A read counts as use, so move the entry to the MRU position.
            moveToFront(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key, "key must not be null");

        lock.lock();
        try {
            Node<K, V> existingNode = entries.get(key);
            if (existingNode != null) {
                // Update in place and refresh recency without growing the cache.
                existingNode.value = value;
                moveToFront(existingNode);
                return;
            }

            // Evict the least recently used entry only when a true new key arrives.
            if (entries.size() == capacity) {
                Node<K, V> lruNode = removeLeastRecentlyUsedNode();
                entries.remove(lruNode.key);
            }

            Node<K, V> newNode = new Node<>(key, value);
            addAfterHead(newNode);
            entries.put(key, newNode);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(K key) {
        Objects.requireNonNull(key, "key must not be null");

        lock.lock();
        try {
            Node<K, V> node = entries.remove(key);
            if (node == null) {
                return null;
            }

            unlink(node);
            return node.value;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return entries.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Visible for demos and debugging.
     * The returned string is a snapshot from most-recently-used to least-recently-used.
     */
    public String snapshot() {
        lock.lock();
        try {
            StringBuilder builder = new StringBuilder();
            Node<K, V> current = head.next;
            while (current != tail) {
                if (builder.length() > 0) {
                    builder.append(" -> ");
                }
                builder.append(current.key).append('=').append(current.value);
                current = current.next;
            }
            return builder.toString();
        } finally {
            lock.unlock();
        }
    }

    private void moveToFront(Node<K, V> node) {
        unlink(node);
        addAfterHead(node);
    }

    private void addAfterHead(Node<K, V> node) {
        Node<K, V> firstRealNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = firstRealNode;
        firstRealNode.prev = node;
    }

    private void unlink(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
    }

    private Node<K, V> removeLeastRecentlyUsedNode() {
        Node<K, V> lruNode = tail.prev;
        if (lruNode == head) {
            throw new IllegalStateException("cannot evict from an empty cache");
        }

        unlink(lruNode);
        return lruNode;
    }

    private static final class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
