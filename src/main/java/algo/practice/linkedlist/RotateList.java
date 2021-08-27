package algo.practice.linkedlist;

import java.util.Random;

public class RotateList {
    public static Node rotateByK(Node head, int k){
        Node node = head;
        int count = 0;
        while(count<k && node!=null){
            node = node.next;
            count++;
        }
        Node lNode = head;
        Node rNode = node;


        while(rNode.next!=null){
            lNode = lNode.next;
            rNode = rNode.next;
        }
        rNode.next = head;
        Node rotatedHead = lNode.next;
        lNode.next = null;
        return rotatedHead;
    }

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        for(int i = 0; i < 10; i++){
            ll1.add(new Random().nextInt(30));
        }
        LinkedList.printList(ll1.head);
        System.out.println("Rotate list:-");
        LinkedList.printList(rotateByK(ll1.head, 3));


    }
}
