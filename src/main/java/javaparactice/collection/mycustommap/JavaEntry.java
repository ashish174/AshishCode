package javaparactice.collection.mycustommap;

import java.util.Objects;

public class JavaEntry<K, V> {
    private K key;
    private V value;
    private Entry next; // In Actual Map implementation, next is used for chaining.

    public JavaEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public Entry getNext() {
        return next;
    }

    public void setNext(Entry next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaEntry<?, ?> entry = (JavaEntry<?, ?>) o;
        return key.equals(entry.key) &&
                value.equals(entry.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
