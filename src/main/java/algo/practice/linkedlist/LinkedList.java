package algo.practice.linkedlist;

import java.util.Random;

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

