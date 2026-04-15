package javaparactice.concurrency.a_practice.mar2026.cache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(2);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        System.out.println("After two inserts: " + cache.snapshot());

        // Access key1 so it becomes the most recently used entry.
        cache.get("key1");
        System.out.println("After reading key1: " + cache.snapshot());

        // Inserting key3 now evicts key2 because it is the least recently used entry.
        cache.put("key3", "value3");
        System.out.println("After inserting key3: " + cache.snapshot());

        // Updating an existing key should not trigger eviction.
        cache.put("key1", "value1-updated");
        System.out.println("After updating key1: " + cache.snapshot());

        cache.put("key4", "value4");
        System.out.println("After inserting key4: " + cache.snapshot());
    }
}
