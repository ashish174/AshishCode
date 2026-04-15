package javaparactice.concurrency.a_practice.mar2026.cache;

/**
 * Minimal cache abstraction.
 *
 * This interface is intentionally small so implementations can focus on
 * correctness of concurrency and eviction policy.
 */
public interface Cache<K, V> {
    V get(K key);

    void put(K key, V value);

    V remove(K key);

    int size();
}
