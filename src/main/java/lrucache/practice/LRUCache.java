package lrucache.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache {
    private static final Logger LOGGER = LoggerFactory.getLogger(LRUCache.class);

    List<Entry> entryList;
    Map<Integer, Entry> keytoEntryMap;
    int maxCapacity;

    public LRUCache(int initialCapacity) {
        maxCapacity = initialCapacity;
        this.entryList = new LinkedList<>();
        this.keytoEntryMap = new HashMap<>(initialCapacity, 1);
    }

    public void set(int key, String value) {
        if (keytoEntryMap.containsKey(key)) {
            Entry entry = keytoEntryMap.get(key);
            entryList.remove(entry);
            entry.setValue(value);
            entryList.add(0, entry);
        } else {
            if (keytoEntryMap.size() == maxCapacity) {
                Entry oldestEntry = entryList.remove(maxCapacity - 1);
                LOGGER.info("Cache full. Remove least recently used entry : ({} - {})", oldestEntry.getKey(), oldestEntry.getValue());
                keytoEntryMap.remove(oldestEntry.getKey());
            }
            Entry newEntry = new Entry(key, value);
            keytoEntryMap.put(key, newEntry);
            entryList.add(0, newEntry);
        }
    }

    public String get(int key) {
        if (!keytoEntryMap.containsKey(key)) {
            LOGGER.warn("Cache Miss");
            return null;
        }
        Entry entry = keytoEntryMap.get(key);
        entryList.remove(entry);
        entryList.add(0, entry);
        return entry.getValue();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.set(3, "ashu");
        lruCache.set(5, "ashu");
        lruCache.set(1, "anshu");
        lruCache.set(2, "juli");
        lruCache.set(4, "rani");
        lruCache.set(7, "reema");

    }
}
