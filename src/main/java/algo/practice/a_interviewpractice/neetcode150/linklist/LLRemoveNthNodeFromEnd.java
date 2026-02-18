package algo.practice.a_interviewpractice.neetcode150.linklist;

public class LLRemoveNthNodeFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;
        //n+1 to account for dummy node as well
        // Move fast pointer n+1 steps ahead (so slow will stand right before the node to remove)
        for(int i=0; i< n+1; i++){
            fast = fast.next;
        }
        // Move both pointers until fast reaches the end
        while(fast!=null){
            fast= fast.next;
            slow=slow.next;
        }
        // Remove the nth node from end
        slow.next = slow.next.next;
        // Return the new head (dummy.next handles the case of deleting head)
        return dummy.next;

    }
}
