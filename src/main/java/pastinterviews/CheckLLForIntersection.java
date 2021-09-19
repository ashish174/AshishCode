package pastinterviews;

/**
 * * A1 = 1, 5 , 7 , 10, 11, 17 = 6
 * * B1 =    3 , 5 , 9, 11,  17 = 5
 */
public class CheckLLForIntersection {

    private static boolean checkIfListIntersect(ListNode firstListNode, ListNode secondListNode) {
        int firstListLength = findLength(firstListNode);
        int secondListLength = findLength(secondListNode);
        int diff;
        if (firstListLength > secondListLength) {
            diff = firstListLength - secondListLength;
            while (diff != 0) {
                firstListNode = firstListNode.next;
                diff--;
            }
        } else {
            diff = secondListLength - firstListLength;
            while (diff != 0) {
                secondListNode = secondListNode.next;
                diff--;
            }
        }
        while (firstListNode != null) {
            if (firstListNode == secondListNode) {
                return true;
            }
            firstListNode = firstListNode.next;
            secondListNode = secondListNode.next;
        }
        return false;
    }


    private static int findLength(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }
}
