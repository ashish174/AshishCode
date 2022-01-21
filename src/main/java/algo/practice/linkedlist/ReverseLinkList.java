package algo.practice.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseLinkList {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReverseLinkList.class);

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        printList(head);
        Node reverseLinkListHead = reverseLinkList(head);
        printList(reverseLinkListHead);
    }

    private static Node reverseLinkList(Node head) {
        Node prev = null;
        Node curr = head;
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;

    }

    private static void printList(Node head) {
        StringBuilder stringBuilder = new StringBuilder();
        while (head != null) {
            stringBuilder.append(head.data + " ");
            head = head.next;
        }
        LOGGER.info("Linked List : {}", stringBuilder.toString());
    }

}
