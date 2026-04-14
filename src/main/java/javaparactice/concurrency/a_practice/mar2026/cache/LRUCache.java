package javaparactice.concurrency.a_practice.mar2026.cache;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Getter
public class LRUCache<K,V> implements Cache<K, V> {
    private int capacity;
    private Map<K, LinkList.ListNode> cacheMap;
    private LinkList<K,V> linkedList;
    private Lock lock;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.linkedList = new LinkList<>();
        lock = new ReentrantLock();
    }

    @Override
    public V get(K key) {
        lock.lock();
        try{
            if(cacheMap.containsKey(key)) {
                LinkList.ListNode listNode = cacheMap.get(key);
                linkedList.remove(listNode);
                linkedList.add(listNode);
                return (V) listNode.value;
            }
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        lock.lock();
        try{
            if(cacheMap.size()>=capacity) {
                LinkList.ListNode removedNode = linkedList.remove();
                cacheMap.remove(removedNode.key);
                log.info("Capacity reached. Remove LRU node : {}", removedNode);
            }
            LinkList.ListNode listNode = new LinkList.ListNode(key, value);
            cacheMap.put(key, listNode);
            linkedList.add(listNode);
        } finally{
            lock.unlock();
        }
    }

    class LinkList<K,V>{
        private ListNode head;
        private ListNode tail;

        public LinkList(){
            this.head = new ListNode(null, null);
            this.head.next = this.tail;
            this.tail = new ListNode(null, null);
            this.tail.prev = this.head;
        }

        ListNode add(ListNode listNode) {
            ListNode prev_head = head.next;
            head.next = listNode;
            listNode.prev = head;
            listNode.next = prev_head;
            prev_head.prev = listNode;
            return listNode;
        }

        ListNode remove(ListNode listNode){
            listNode.prev.next = listNode.next;
            listNode.next.prev = listNode.prev;
            return listNode;
        }

        ListNode remove(){
            if(tail.prev!=head){
                ListNode tailNode = tail.prev;
                tail.prev = tail.prev.prev;
                tail.prev.next = tail;
                return tailNode;
            }
            return null;
        }

        class ListNode<K,V>{
            K key;
            V value;
            ListNode<K,V> next;
            ListNode<K,V> prev;

            public ListNode(K key, V value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "ListNode{" +
                        "key=" + key +
                        ", value=" + value +
                        '}';
            }
        }
    }



}


