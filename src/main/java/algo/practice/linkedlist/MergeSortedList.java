package algo.practice.linkedlist;

public class MergeSortedList {
    /**
     * Merge Two sorted linked list
     * @param list1
     * @param list2
     * @return
     */
    public Node mergeTwoLists(Node list1, Node list2) {
        // Dummy node to hold empty tail, dataue doesn't matter
        Node dummy = new Node(0);
        Node tail = dummy;
        while(list1 != null && list2 != null) {
            if(list1.data < list2.data) {
                tail.next = list1;
                list1 = list1.next;
            } else{
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        // Attach any remaining nodes
        tail.next = list1 !=null ? list1 : list2;
        return dummy.next;
    }

    public Node mergeTwoListsRecursively(Node list1, Node list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.data <= list2.data) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public Node mergeTwoListsCopy(Node list1, Node list2) {
        if(list1==null) {
            return list2;
        }
        if(list2==null) {
            return list1;
        }
        Node mergedListHead = null;
        Node mergedListTail = null;
        while(list1!=null && list2!=null) {
            if(list1.data <= list2.data) {
                if(mergedListHead==null) {
                    mergedListHead = list1;
                    mergedListTail = list1;
                } else {
                    mergedListTail.next = list1;
                    mergedListTail = mergedListTail.next;
                }
                list1 = list1.next;

            } else {
                if(mergedListHead==null) {
                    mergedListHead = list2;
                    mergedListTail = list2;
                } else {
                    mergedListTail.next = list2;
                    mergedListTail = mergedListTail.next;
                }
                list2 = list2.next;
            }
        }

        if(list1==null) {
            mergedListTail.next = list2;
        } else {
            mergedListTail.next = list1;
        }
        return mergedListHead;

    }

}
