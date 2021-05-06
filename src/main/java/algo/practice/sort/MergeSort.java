package algo.practice.sort;

import java.util.Random;
import algo.practice.linkedlist.LinkedList;
import algo.practice.linkedlist.Node;

public class MergeSort {
    public static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node middle = findMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);
        return merge(left, right);
    }

    public static Node merge(Node head1, Node head2) {
        Node head = null;
        Node tail = null;
        while (head1 != null && head2 != null) {
            Node newNode = new Node(Math.min(head1.data, head2.data));
            if (tail == null) {
                tail = newNode;
                head = newNode;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
            if (head1.data < head2.data) {
                head1 = head1.next;
            } else {
                head2 = head2.next;
            }
        }
        if (head1 == null) {
            tail.next = head2;
        }
        if (head2 == null) {
            tail.next = head1;
        }
        return head;
    }

    public static Node findMiddle(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node fastPtr = head.next;
        Node slowPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }
        return slowPtr;
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        Node tail = head;
        for (int i = 0; i < 9; i++) {
            tail.next = new Node(new Random().nextInt(30));
            tail = tail.next;
        }
        LinkedList.printList(head);
        System.out.println("Merge Sort :");
        Node newHead = mergeSort(head);
        LinkedList.printList(newHead);

    }


}
