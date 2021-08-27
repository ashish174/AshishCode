package algo.practice.linkedlist;

public class LinkedListWithTail {
    Node head;
    Node tail;

    public void addAtStart(int data) {
        Node tmp = head;
        head = new Node(data);
        head.next = tmp;
        if(tmp == null){
            tail = head;
        }
    }

    public void addAtEnd(int data) {
        Node tmp = tail;
        if(tmp==null){
            tail = new Node(data);
            head = tail;
        } else{
            tail.next = new Node(data);
            tail = tail.next;
        }
    }

    public Node removeAtStart() {
        Node tmp = head;
        if(head==tail){
            tail = null;
        }
        head = head.next;
        return tmp;
    }



    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        LinkedListWithTail ll = new LinkedListWithTail();
        ll.addAtEnd(3);
        ll.addAtEnd(1);
        ll.addAtEnd(8);
        ll.addAtStart(5);
        printList(ll.head);
    }
}

