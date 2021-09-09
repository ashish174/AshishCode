package javaparactice.collection.mycustommap;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMap<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMap.class);

    private static final int DEFAULT_BUCKET_SIZE = 16;

    private int capacity;
    private List<Entry<K, V>>[] bucketList;

    public MyMap(int capacity) {
        this.capacity = capacity;
        this.bucketList = (List<Entry<K, V>>[]) new Object[capacity];
    }

    public MyMap() {
        this.capacity = DEFAULT_BUCKET_SIZE;
        this.bucketList = (List<Entry<K, V>>[]) new Object[capacity];
    }

    public void put(K key, V value) {
        int bucketPosition = key.hashCode() % capacity;
        Entry entry = new Entry<>(key, value);
        if (bucketList[bucketPosition] == null) {
            bucketList[bucketPosition] = new ArrayList<>();
        }
        bucketList[bucketPosition].add(entry);
    }

    public V get(K key) {
        int bucketPosition = key.hashCode() % capacity;
        if (bucketList[bucketPosition] == null) {
            LOGGER.info("Value with Key {} not found", key);
            return null;
        }
        List<Entry<K, V>> entries = bucketList[bucketPosition];
        for (Entry entry : entries) {
            if (key.equals(entry.getKey())) {
                return (V) entry.getValue();
            }
        }
        LOGGER.info("Value with Key {} not found", key);
        return null;
    }

    public void remove(K key) {

    }

    public static void main(String[] args) {
        List<Character> characterList = new ArrayList<>();
        System.out.println(characterList.get(2));
    }


}
