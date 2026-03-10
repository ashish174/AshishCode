package algo.practice.linkedlist;

import java.util.Random;

/**
 * You can improve the boundary usecase by keeping dummy nodes
 * In a linked list, “dummy” (or “sentinel”) nodes are special nodes that are not part of the actual data; they exist only to simplify pointer logic.
 * They prevent you from having to write special‑case code for:
 * - Inserting/removing at the head
 * - Inserting/removing at the tail
 * - Handling empty list / single element list transitions
 *
 * Sometime we use 1 dummyNode at head, but sometimes we also use 2nd dummynode at tail.
 * When to use 1 dummy node vs 2??
 *
 * Case A: Single dummy head (very common)
 * Use one dummy head when:
 * - You mostly operate at the front of the list (insert/remove head, traversal).
 * - You don’t often need an O(1) operation at the back, or you’re okay with tracking a tail pointer separately and writing some special cases.
 * Example: singly linked list utilities (reverse, merge, remove N‑th from end, etc.):
 *
 * Case B: Two dummy nodes (head + tail) – as in LRU. So the list is always:
 * head <-> ... real nodes ... <-> tail
 * Use two dummy nodes when:
 * - The list is doubly linked, and
 * - You want O(1) insert/remove at both ends, with clean code:
 *      - Front: head.next
 *      - Back: tail.prev
 *
 *
 **/
public class LinkedList {
    Node head;

    public void add(int data) {
        Node tmp = head;
        head = new Node(data);
        head.next = tmp;
    }

    public Node remove() {
        Node tmp = head;
        head = head.next;
        return tmp;
    }

    public Node remove(int data){
        Node node;
        Node prev = null;
        if(head==null){
            return null;
        }
        if(head!=null & head.data==data){
            node = head;
            head = head.next;
            return node;
        }
        node = head;
        while(node!=null && node.data!=data){
            prev = node;
            node = node.next;
        }
        if(node==null){
            return null;
        }
        prev.next = prev.next.next;
        return node;
    }

    public static int findLength(Node head){
        int count = 0;
        while(head!=null){
            count++;
            head = head.next;
        }
        return count;
    }

    public static int findLengthRecursive(Node head){
        if(head==null){
            return 0;
        }
        return 1 + findLengthRecursive(head.next);
    }

    public static Node reverseLinkedList(Node head){
        Node prev = null;
        Node curr = head;
        Node next = null;
        while(curr!=null){
            // Swap pointers
            next = curr.next;
            curr.next = prev;
            prev = curr;
            // Swap pointers
            
            curr = next;
        }
        return prev;
    }

    public static Node reverseLinkedListRecursively(Node head){
        if(head==null|| head.next==null){
            return head;
        }
        Node tailOfReversedList = head.next;
        Node reverseHead = reverseLinkedListRecursively(head.next);
        tailOfReversedList.next = head;
        head.next = null;
        return reverseHead;
    }


    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }



    public static void main(String[] args) {
        /*LinkedList ll = new LinkedList();
        ll.add(3);
        ll.add(1);
        ll.add(8);
        ll.add(7);
        printList(ll.head);
        ll.remove(8);
        printList(ll.head);*/
        LinkedList ll1 = new LinkedList();
        for(int i = 0; i < 10; i++){
            ll1.add(new Random().nextInt(30));
        }
        printList(ll1.head);
        System.out.println("Reverse List:-");
        //printList(reverseLinkedList(ll1.head));
        printList(reverseLinkedListRecursively(ll1.head));

    }
}

