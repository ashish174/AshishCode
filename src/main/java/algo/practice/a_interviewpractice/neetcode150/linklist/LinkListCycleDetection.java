package algo.practice.a_interviewpractice.neetcode150.linklist;

import lombok.extern.slf4j.Slf4j;

/**
 * Given the beginning of a linked list head, return true if there is a cycle in the linked list.
 * Otherwise, return false.
 *
 * <p>There is a cycle in a linked list if at least one node in the list can be visited again by
 * following the next pointer.
 *
 * <p>Internally, index/position determines the index/position of the beginning of the cycle, if it
 * exists. The tail node of the list will set it's next pointer to the index-th node. If index = -1,
 * then the tail node points to null and no cycle exists.
 *
 * <p>Note: index is not given to you as a parameter. Example 1: Input: head = [1,2,3,4], index = 1
 *
 * <p>Output: true Explanation: There is a cycle in the linked list, where the tail connects to the
 * 1st node (0-indexed).
 *
 * <p>Example 2: Input: head = [1,2], index = -1
 *
 * <p>Output: false Constraints:
 *
 * <p>1 <= Length of the list <= 1000. -1000 <= Node.val <= 1000 index is -1 or a valid index in the
 * linked list.
 */
@Slf4j
public class LinkListCycleDetection {

  public boolean hasCycle(ListNode head) {
    if (head == null || head.getNext() == null) {
      return false;
    }
    ListNode slowPtr = head;
    ListNode fastPtr = head;
    while (slowPtr != null && fastPtr != null && fastPtr.getNext() != null) {
      slowPtr = slowPtr.getNext();
      fastPtr = fastPtr.getNext().getNext();
      if (slowPtr == fastPtr) {
        log.info("Key {}", slowPtr.getKey());
        return true;
      }
    }
    return false;
  }

  public void detectAndRemoveLoop(ListNode head) {
    boolean loopDetected = false;
    if (head == null || head.getNext() == null) {
      return;
    }
    ListNode slowPtr = head;
    ListNode fastPtr = head;
    while (slowPtr != null && fastPtr != null && fastPtr.getNext() != null) {
      slowPtr = slowPtr.getNext();
      fastPtr = fastPtr.getNext().getNext();
      if (slowPtr == fastPtr) {
        log.info("Loop Detected");
        loopDetected = true;
        break;
      }
    }
    if (loopDetected) {
      removeLoop(head, slowPtr, fastPtr);
    }
  }

  /**
   * we will point the slow pointer to head node and fast pointer will remain at its position. Both
   * slow and fast pointers move one step ahead until fast->next is not equals to slow->next. When
   * slow->next equals to fast->next we can easily point fast->next to NULL to remove the loop.
   *
   */
  private void removeLoop(ListNode head, ListNode slowPtr, ListNode fastPtr) {
    slowPtr = head;
    while (slowPtr.getNext() != fastPtr.getNext()) {
      slowPtr = slowPtr.getNext();
      fastPtr = fastPtr.getNext();
    }
    log.info("Loop Detected at Key {}", fastPtr.getNext());
    log.info("Removing the Loop");
    fastPtr.setNext(null);
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(0);
    head.setNext(new ListNode(1));
    head.getNext().setNext(new ListNode(2));
    head.getNext().getNext().setNext(new ListNode(3));
    head.getNext().getNext().getNext().setNext(new ListNode(4, head.getNext().getNext()));
    log.info("list1 : has Cycle: {}", new LinkListCycleDetection().hasCycle(head));
    new LinkListCycleDetection().detectAndRemoveLoop(head);
    log.info("list1 : After removing loop, checking has Cycle: {}", new LinkListCycleDetection().hasCycle(head));


    ListNode head1 = new ListNode(0);
    head1.setNext(new ListNode(1));
    head1.getNext().setNext(new ListNode(2));
    head1.getNext().getNext().setNext(new ListNode(3));
    log.info("list2 : has Cycle: {}", new LinkListCycleDetection().hasCycle(head1));
  }
}
