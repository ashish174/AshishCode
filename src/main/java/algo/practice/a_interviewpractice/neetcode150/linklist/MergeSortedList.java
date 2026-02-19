package algo.practice.a_interviewpractice.neetcode150.linklist;

import static algo.practice.a_interviewpractice.neetcode150.linklist.ListNode.printList;

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
 *
 *
 * Approach:
 * - Use a dummy head to simplify handling of the merged list.
 * - Traverse both lists with pointers, always appending the smaller valued node to the result.
 * - Advance the pointer in the list from which a node was selected.
 * - Once either list is exhausted, append the remainder of the other list.
 * - The merged list is built by reusing the existing nodes.
 * - Time complexity is O(n + m), where n and m are lengths of the input lists.
 */
public class MergeSortedList {

  /**
   * Merges two sorted linked lists and returns the head of the merged sorted list.
   *
   * @param list1 The head of the first sorted list.
   * @param list2 The head of the second sorted list.
   * @return The head of the merged sorted list.
   */
  public ListNode mergeTwoListsV2(ListNode list1, ListNode list2) {
    // Dummy head to simplify merge logic
    ListNode dummy = new ListNode(-1);
    ListNode current = dummy;

    // Traverse both lists, always choosing the node with the smaller key
    while (list1 != null && list2 != null) {
      if (list1.key <= list2.key) {
        current.next = list1;      // Append list1's node
        list1 = list1.next;        // Move list1 forward
      } else {
        current.next = list2;      // Append list2's node
        list2 = list2.next;        // Move list2 forward
      }
      current = current.next;        // Move the result pointer forward
    }

    // At least one of the lists is now null; append the non-null list
    if (list1 != null) {
      current.next = list1;
    } else {
      current.next = list2;
    }

    // Return the merged list, skipping over dummy node
    return dummy.next;
  }

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
