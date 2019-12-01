package lrucacheWithGeneric;

import java.util.Objects;

public class Node <K,V> {
  private K key;
  private V value;
  private Node left;
  private Node right;

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Node<?, ?> node = (Node<?, ?>) o;
    return Objects.equals(key, node.key);
  }

  @Override
  public int hashCode() {

    return Objects.hash(key);
  }
}
