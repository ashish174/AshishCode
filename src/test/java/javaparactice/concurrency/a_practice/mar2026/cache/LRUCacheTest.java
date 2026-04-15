package javaparactice.concurrency.a_practice.mar2026.cache;

import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {

    @Test
    public void shouldEvictLeastRecentlyUsedEntry() {
        LRUCache<String, String> cache = new LRUCache<>(2);

        cache.put("A", "1");
        cache.put("B", "2");
        cache.get("A");
        cache.put("C", "3");

        Assert.assertEquals("1", cache.get("A"));
        Assert.assertNull(cache.get("B"));
        Assert.assertEquals("3", cache.get("C"));
    }

    @Test
    public void shouldUpdateExistingKeyWithoutEvictingAnotherEntry() {
        LRUCache<String, String> cache = new LRUCache<>(2);

        cache.put("A", "1");
        cache.put("B", "2");
        cache.put("A", "1-updated");

        Assert.assertEquals(2, cache.size());
        Assert.assertEquals("1-updated", cache.get("A"));
        Assert.assertEquals("2", cache.get("B"));
    }

    @Test
    public void shouldRemoveEntryByKey() {
        LRUCache<String, String> cache = new LRUCache<>(2);

        cache.put("A", "1");
        cache.put("B", "2");

        Assert.assertEquals("1", cache.remove("A"));
        Assert.assertNull(cache.get("A"));
        Assert.assertEquals(1, cache.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNonPositiveCapacity() {
        new LRUCache<String, String>(0);
    }
}
