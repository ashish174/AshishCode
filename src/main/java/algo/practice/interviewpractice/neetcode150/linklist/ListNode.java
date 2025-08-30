package algo.practice.interviewpractice.neetcode150.linklist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Slf4j
public class ListNode {
    @NonNull private Integer key;
    private ListNode next;

    public static void printList(ListNode listNode, String msg) {
        log.info("Printing List for {}", msg);
        while (listNode != null) {
            log.info("{}", listNode.getKey());
            listNode = listNode.getNext();
        }
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "key=" + key +
                '}';
    }
}
