package javaparactice.concurrency.a_practice.mar2026.cache;

public class LRUCacheDemo {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache(2);
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.get("key1");
        cache.put("key4", "value4");
    }
}
