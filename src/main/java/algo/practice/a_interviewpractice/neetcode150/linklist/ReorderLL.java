package algo.practice.a_interviewpractice.neetcode150.linklist;

/**
 *
 * We can divide the list into two halves using the fast and slow pointer approach,
 * which helps identify the midpoint of the list. This allows us to split the list into two halves,
 * with the heads labeled as l1 and l2. Next, we reverse the second half (l2).
 * After these steps, we proceed to reorder the two lists by iterating through them node by node,
 * updating the next pointers accordingly.
 *
 *
 * Example 1:
 *
 * Input: head = [2,4,6,8]
 *
 * Output: [2,8,4,6]
 * Example 2:
 *
 * Input: head = [2,4,6,8,10]
 *
 * Output: [2,10,4,8,6]
 *
 */
public class ReorderLL {

    public void reorderList(ListNode head) {
        // Handle edge case: empty list or single element list
        if (head == null || head.next == null) return;

        // Step 1: Find the middle of the list using slow and fast pointers
        // When the loop ends, slowPtr will point to the middle node
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while (fastPtr != null && fastPtr.next != null) {
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Step 2: Split the list into two halves
        // First half starts from head, second half starts from slowPtr.next
        ListNode hd1 = head;          // Will be the head of the first half
        ListNode hd2 = slowPtr.next;  // Head of second half
        slowPtr.next = null;          // Terminate the first half

        // Step 3: Reverse the second half of the list
        ListNode reverseHd2 = reverseList(hd2);

        // Step 4: Merge the two halves in alternate fashion
        // Use a dummy node to simplify pointer manipulations
        ListNode dummyNode = new ListNode(0);
        ListNode tailNode = dummyNode;
        while (hd1 != null && reverseHd2 != null) {
            // Add one node from the first half
            tailNode.next = hd1;
            hd1 = hd1.next;
            tailNode = tailNode.next;

            // Add one node from the reversed second half
            tailNode.next = reverseHd2;
            reverseHd2 = reverseHd2.next;
            tailNode = tailNode.next;
        }

        // Attach any remaining nodes from either half (only one of these will be non-null)
        tailNode.next = hd1 != null ? hd1 : reverseHd2;

        // The original head reference still points to the unchanged starting node,
        // since we only rearranged the next pointers.
        // No need to assign head = dummyNode.next here (Java reference semantics).
    }

    // Helper function to recursively reverse a singly linked list
    ListNode reverseList(ListNode head) {
        // Base case: empty list or single node list
        if (head == null || head.next == null) {
            return head;
        }
        // Reverse the rest of the list
        ListNode newHead = reverseList(head.next);
        // Make the next node point to the current node
        head.next.next = head;
        // Set current node's next to null (it's the new tail)
        head.next = null;
        return newHead;
    }

}
