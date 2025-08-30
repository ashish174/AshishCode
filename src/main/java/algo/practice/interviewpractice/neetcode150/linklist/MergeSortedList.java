package algo.practice.interviewpractice.neetcode150.linklist;

import static algo.practice.interviewpractice.neetcode150.linklist.ListNode.printList;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * <p>Merge the two lists into one sorted linked list and return the head of the new sorted linked
 * list.
 *
 * <p>The new list should be made up of nodes from list1 and list2.
 *
 * <p>Example 1: Input: list1 = [1,2,4], list2 = [1,3,5]
 *
 * <p>Output: [1,1,2,3,4,5] Example 2:
 *
 * <p>Input: list1 = [], list2 = [1,2]
 *
 * <p>Output: [1,2] Example 3:
 *
 * <p>Input: list1 = [], list2 = []
 *
 * <p>Output: [] Constraints:
 *
 * <p>0 <= The length of the each list <= 100. -100 <= Node.val <= 100
 */
public class MergeSortedList {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if(list1==null) {
      return list2;
    }
    if(list2==null) {
      return list1;
    }
    ListNode mergedListHead = null;
    ListNode mergedListTail = null;
    while(list1!=null && list2!=null) {
      if(list1.getKey() <= list2.getKey()) {
        if(mergedListHead==null) {
          mergedListHead = list1;
          mergedListTail = list1;
        } else {
          mergedListTail.setNext(list1);
          mergedListTail = mergedListTail.getNext();
        }
        list1 = list1.getNext();

      } else {
        if(mergedListHead==null) {
          mergedListHead = list2;
          mergedListTail = list2;
        } else {
          mergedListTail.setNext(list2);
          mergedListTail = mergedListTail.getNext();
        }
        list2 = list2.getNext();
      }
    }

    if(list1==null) {
      mergedListTail.setNext(list2);
    } else {
      mergedListTail.setNext(list1);
    }
  return mergedListHead;

  }

  public static void main(String[] args){
    ListNode head1 = new ListNode(1);
    head1.setNext(new ListNode(2));
    head1.getNext().setNext(new ListNode(4));

    ListNode head2 = new ListNode(1);
    head2.setNext(new ListNode(3));
    head2.getNext().setNext(new ListNode(5));
    printList(head1, "list1");
    printList(head2, "list2");
    ListNode mergedListHead = new MergeSortedList().mergeTwoLists(head1, head2);
    printList(mergedListHead, "mergedList");


  }
}
