package javaparactice.concurrency.a_practice.mar2026.cache;

public interface Cache <K,V>{
    V get(K key);
    void put(K key, V value);
}
