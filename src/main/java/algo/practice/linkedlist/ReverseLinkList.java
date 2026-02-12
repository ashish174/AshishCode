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
            // Swap pointers
            next = curr.next;
            curr.next = prev;
            prev = curr;
            // Swap pointers

            curr = next;
        }
        return prev;

    }

    public Node reverseListCopy(Node head) {
        Node head2 = null;
        while(head!=null){
            //Insert at beginning of new list
            //Store new list
            Node tmp = head2;
            head2 = head;
            head = head.next;
            //Insert elem at new head position
            head2.next = tmp;
        }
        return head2;

    }

    private static Node reverseLinkListRecursively(Node head) {
        // Base case: if list is empty or only one node, it's already reversed
        if(head==null || head.next==null){
            return head;
        }
        // Recursively reverse the rest of the list
        Node newHead = reverseLinkListRecursively(head.next);
        // Reverse the current node's pointer
        //head.next will become 2nd last elem in reversed list, and head will be last elem
        head.next.next = head;
        //since head is last, so it points to null
        head.next = null;
        return newHead;
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
