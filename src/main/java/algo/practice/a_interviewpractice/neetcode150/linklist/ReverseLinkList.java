package algo.practice.a_interviewpractice.neetcode150.linklist;

import lombok.extern.slf4j.Slf4j;

import static algo.practice.a_interviewpractice.neetcode150.linklist.ListNode.printList;

/**
 * Given the beginning of a singly linked list head, reverse the list, and return the new beginning
 * of the list.
 *
 * <p>Example 1:
 *
 * <p>Input: head = [0,1,2,3] Output: [3,2,1,0]
 *
 * <p>Example 2: Input: head = [] Output: []
 *
 * <p>Constraints: 0 <= The length of the list <= 1000. -1000 <= Node.val <= 1000
 */
@Slf4j
public class ReverseLinkList {

  public ListNode reverseListIterative(ListNode head) {
    ListNode reverseListHead = null;
    while (head != null) {
      ListNode tmp = reverseListHead;
      reverseListHead = head;
      head = head.getNext();
      reverseListHead.setNext(tmp);
    }
    return reverseListHead;
  }

  public ListNode reverseListRecursive(ListNode head) {
    if (head == null || head.getNext() == null) {
      return head;
    }
    ListNode tailOfReversedList = head.getNext();
    ListNode reverseHead = reverseListRecursive(head.getNext());
    tailOfReversedList.setNext(head);
    head.setNext(null);
    return reverseHead;
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(0);
    head.setNext(new ListNode(1));
    head.getNext().setNext(new ListNode(2));
    head.getNext().getNext().setNext(new ListNode(3));
    head.getNext().getNext().getNext().setNext(new ListNode(4));
    printList(head, "Original");
    ListNode reverseListHeadIterative = new ReverseLinkList().reverseListIterative(head);
    printList(reverseListHeadIterative, "reverse by Iteration");

    ListNode head1 = new ListNode(0);
    head1.setNext(new ListNode(1));
    head1.getNext().setNext(new ListNode(2));
    head1.getNext().getNext().setNext(new ListNode(3));
    head1.getNext().getNext().getNext().setNext(new ListNode(4));
    printList(head1, "Original");
    ListNode reverseListHeadRecursive = new ReverseLinkList().reverseListRecursive(head1);
    printList(reverseListHeadRecursive, "reverse by Recursion");
  }
}
