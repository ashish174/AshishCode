package javaparactice.generics;

public class GNode<T> {
    T data;
    GNode<T> left;
    GNode<T> right;

    public GNode(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
