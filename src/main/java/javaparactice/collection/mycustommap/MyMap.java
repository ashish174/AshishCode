package javaparactice.collection.mycustommap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implemented a HashMap using
 *      - Array : it has list of entries to resolve collision
 *      - Store Entry Object in Array so as to find match when there is collision
 * @param <K>
 * @param <V>
 */
public class MyMap<K, V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyMap.class);

    private static final int DEFAULT_BUCKET_SIZE = 16;

    private int capacity;
    private Object[] bucketList;
    private int loadFactor = 75;

    public MyMap(int capacity) {
        this.capacity = capacity;
        this.bucketList = new Object[capacity];
    }

    public MyMap() {
        this.capacity = DEFAULT_BUCKET_SIZE;
        this.bucketList = new Object[capacity];
    }

    public void put(K key, V value) {
        int bucketPosition = key.hashCode() % capacity;
        Entry entry = new Entry<>(key, value);
        if (bucketList[bucketPosition] == null) {
            bucketList[bucketPosition] = new LinkedList<>();
        }
        ((List<Entry>)bucketList[bucketPosition]).add(entry);
    }

    public V get(K key) {
        int bucketPosition = key.hashCode() % capacity;
        if (bucketList[bucketPosition] == null) {
            LOGGER.info("Value with Key {} not found", key);
            return null;
        }
        List<Entry<K, V>> entries = (List<Entry<K, V>>) bucketList[bucketPosition];
        for (Entry entry : entries) {
            if (key.equals(entry.getKey())) {
                return (V) entry.getValue();
            }
        }
        LOGGER.info("Value with Key {} not found", key);
        return null;
    }

    public void remove(K key) {
        int bucketPosition = key.hashCode() % capacity;
        if (bucketList[bucketPosition] == null) {
            LOGGER.info("Entry with Key {} not found", key);
            return;
        }
        List<Entry<K, V>> entries = (List<Entry<K, V>>) bucketList[bucketPosition];
        for (Entry entry : entries) {
            if (key.equals(entry.getKey())) {
                LOGGER.info("Entry with Key {} Removed", key);
                entries.remove(entry);
                return;
            }
        }
        LOGGER.info("Entry with Key {} not found", key);
    }

    /**
     * When loadfactor is reached, we will double the size and migrate all data
     */
    private void resize(){

    }

    public static void main(String[] args) {
        MyMap<Integer, String> map = new MyMap<>();
        map.put(1, "Ashish");
        map.put(4, "Anshu");
        map.put(2, "Juli");
        LOGGER.info("Value for Key {} : {}", 4, map.get(4));
        LOGGER.info("Value for Key {} : {}", 2, map.get(2));
    }


}
