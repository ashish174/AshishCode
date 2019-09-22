package lrucache;

public class Node {
  private String Key;
  private int Value;
  private Node left;
  private Node right;

  public Node(String key, int value) {
    Key = key;
    Value = value;
  }

  public String getKey() {
    return Key;
  }

  public void setKey(String key) {
    Key = key;
  }

  public int getValue() {
    return Value;
  }

  public void setValue(int value) {
    Value = value;
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
  public String toString() {
    return "Node{" +
        "Key='" + Key + '\'' +
        ", Value=" + Value +
        '}';
  }
}
