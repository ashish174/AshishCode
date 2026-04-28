package algo.practice.a_interviewpractice.apr2026.cache;

public interface Cache<K,V> {
    void put(K key, V value);
    V get(K key);

    void remove(K key);

    int getSize();
}
