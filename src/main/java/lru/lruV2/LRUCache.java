package lru.lruV2;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache implementation with O(1) get and put.
 *
 * Uses:
 * - HashMap<key, Node> for O(1) lookup
 * - Doubly linked list to maintain usage order:
 *     head <-> ... most recently used ... <-> ... least recently used ... <-> tail
 *
 * Most recently used (MRU) node is always right after head.
 * Least recently used (LRU) node is always right before tail.
 */
public class LRUCache {

    /**
     * Doubly-linked list node used to store key-value pairs and
     * to maintain the usage order.
     */
    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;

    // Dummy/sentinel nodes for the doubly linked list:
    // head.next is the most recently used node
    // tail.prev is the least recently used node
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);

        // Create dummy head and tail nodes to avoid null checks
        // and to simplify add/remove operations.
        // These do NOT represent real cache entries.
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        // Initially, the list is empty between head and tail:
        // head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Return the value associated with key, or -1 if not present.
     * Marks the key as most recently used if found.
     */
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            // Key not in cache
            return -1;
        }
        // Move this node to the front (right after head)
        // because it is now the most recently used.
        moveToHead(node);
        return node.value;
    }

    /**
     * Insert or update a key-value pair.
     * If inserting causes the cache to exceed capacity,
     * evict the least recently used entry.
     */
    public void put(int key, int value) {
        Node node = map.get(key);

        if (node != null) {
            // Key already exists: update value
            node.value = value;
            // And mark as most recently used
            moveToHead(node);
        } else {
            // New key: create a node
            Node newNode = new Node(key, value);
            // Add it to the hash map
            map.put(key, newNode);
            // Place node right after head (MRU position)
            addToHead(newNode);

            // If we exceed capacity, remove the least recently used
            if (map.size() > capacity) {
                // LRU node is right before tail
                Node lru = tail.prev;
                // Remove it from the linked list
                removeNode(lru);
                // Remove it from the map
                map.remove(lru.key);
            }
        }
    }

    // ---------------- Doubly Linked List helper methods ----------------

    /**
     * Insert node right after head.
     * This makes it the most recently used node.
     */
    private void addToHead(Node node) {
        // Current first node after head
        Node first = head.next;

        // Rewire pointers:
        node.prev = head;
        node.next = first;

        head.next = node;
        first.prev = node;
    }

    /**
     * Remove a node from the doubly linked list.
     * Does not touch the HashMap.
     */
    private void removeNode(Node node) {
        Node before = node.prev;
        Node after  = node.next;

        // Bypass node
        before.next = after;
        after.prev = before;
    }

    /**
     * Move a node to the head (MRU position).
     */
    private void moveToHead(Node node) {
        // First remove from current position
        removeNode(node);
        // Then insert right after head
        addToHead(node);
    }

    // Optional: small demo
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);              // cache: {1=10}
        cache.put(2, 20);              // cache: {1=10, 2=20}
        System.out.println(cache.get(1)); // 10, 1 becomes MRU, 2 is LRU
        cache.put(3, 30);              // evicts key 2; cache: {1=10, 3=30}
        System.out.println(cache.get(2)); // -1 (evicted)
        cache.put(4, 40);              // evicts key 1; cache: {3=30, 4=40}
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }
}
