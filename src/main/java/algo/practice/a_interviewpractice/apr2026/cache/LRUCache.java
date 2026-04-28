package algo.practice.a_interviewpractice.apr2026.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Fixed Size
 * add item -> MRU
 * access item -> MRU
 * capacity reached -> remove LRU
 *
 *
 *
 */
public class LRUCache<K,V> implements Cache<K,V>{
    private final Map<K, Node<K,V>> cacheMap;
    private final Node<K,V> head;
    private final Node<K,V> tail;
    private final int capacity;
    private final Lock lock = new ReentrantLock();

    public LRUCache(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException("capacity must be positive integer \n");
        }
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        lock.lock();
        try{
            Node<K,V> exist = cacheMap.get(key);
            if(exist!=null){
                System.out.printf("Key already exist: %d. Updating \n", key);
                moveToFront(exist);
                exist.value = value;
                return;
            }
            if(cacheMap.size()==capacity){
                Node<K,V> toRemove = tail.prev;
                System.out.printf("Capacity reached. Deleting LRU key : %d \n", toRemove.key);
                cacheMap.remove(toRemove.key);
                removeNode(toRemove);
            }
            Node<K,V> node = new Node(key, value);
            addNodeToFront(node);
            //put into map
            cacheMap.put(key, node);

        } finally{
            lock.unlock();
        }

    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key);
        lock.lock();
        try{
            Node<K,V> exist = cacheMap.get(key);
            if(exist==null){
                return null;
            }
            moveToFront(exist);
            return exist.value;
        } finally{
            lock.unlock();
        }

    }

    @Override
    public void remove(K key) {
        Objects.requireNonNull(key);
        lock.lock();
        try{
            Node<K,V> exist = cacheMap.get(key);
            if(exist!=null){
                removeNode(exist);
                cacheMap.remove(exist.key);
            }
        } finally{
            lock.unlock();
        }

    }

    @Override
    public int getSize() {
        //lock is used so that when somebody checking size, nobody does add/remove
        lock.lock();
        try{
            return cacheMap.size();
        } finally{
            lock.unlock();
        }
    }

    private void moveToFront(Node<K,V> node){
        removeNode(node);
        addNodeToFront(node);
    }

    private void addNodeToFront(Node<K,V> node){
        Node<K,V> tmp = head.next;
        //Set node as first node
        head.next = node;
        node.prev = head;
        //Set others as after node
        tmp.prev = node;
        node.next = tmp;
    }

    private void removeNode(Node<K,V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }


    //this is static so that Node dont need LRUCache Instance to access
    //This class is just a helper data structure; it doesn’t depend on an instance of the outer class
    private static class Node<K,V> {
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> prev;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args){
        Cache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "Ashish");
        lruCache.put(2, "Anshu");
        lruCache.put(1, "Anamika");
        lruCache.put(3, "Anshu");
        lruCache.put(4, "Akash");

    }



}
