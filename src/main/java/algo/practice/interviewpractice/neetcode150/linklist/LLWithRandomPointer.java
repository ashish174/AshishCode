package algo.practice.interviewpractice.neetcode150.linklist;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given the head of a linked list of length n. Unlike a singly linked list, each node contains an additional pointer random, which may point to any node in the list, or null.
 *
 * Create a deep copy of the list.
 *
 * The deep copy should consist of exactly n new nodes, each including:
 *
 * The original value val of the copied node
 * A next pointer to the new node corresponding to the next pointer of the original node
 * A random pointer to the new node corresponding to the random pointer of the original node
 * Note: None of the pointers in the new list should point to nodes in the original list.
 *
 * Return the head of the copied linked list.
 *
 * Approach : can be done in 2 pass
 * 1st pass: create node and assign next pointer. Also keep Map<oldNode-> NewNode>
 * 2nd pass: assign random pointer using map
 *
 * Below code is a different approach
 *
 */
public class LLWithRandomPointer {

    public Node copyRandomList(Node head) {
        Map<Node, Node> oldToCopy = new HashMap<>();
        oldToCopy.put(null, null);

        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            oldToCopy.put(cur, copy);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node copy = oldToCopy.get(cur);
            copy.next = oldToCopy.get(cur.next);
            copy.random = oldToCopy.get(cur.random);
            cur = cur.next;
        }

        return oldToCopy.get(head);
    }

    public Node copyRandomListCpy(Node head) {
        Node node = head;
        // Dummy node to help build the new copied list
        Node dummy = new Node(0);
        Node tail = dummy;        // Points to the last node in the new list

        // Map to store the mapping from original node to copied node
        Map<Node, Node> nodeMap = new HashMap<>();

        // First pass: create new nodes and the mapping (original -> copy)
        while (node != null) {
            Node newNode = deepCopy(node); // Copy node value and random (random is temporary here)
            tail.next = newNode;           // Link the new node
            tail = tail.next;              // Advance the tail
            nodeMap.put(node, newNode);    // Store mapping (original node -> copy node)
            node = node.next;              // Move to next node in original list
        }

        // Step 2: Assign correct random pointers to the new nodes
        Node hd2 = dummy.next; // This is the starting node of the copied list
        while(hd2!=null){
            // If the original node's random is not null, assign the copy's random
            if(hd2.random!=null){
                hd2.random = nodeMap.get(hd2.random);
            }
            hd2 = hd2.next;
        }
        return dummy.next;

    }

    // Helper method to create a node with the same value (random points to the same original for now)
    Node deepCopy(Node node) {
        Node newNode = new Node(node.val);
        newNode.random = node.random; // Will be fixed in the second pass
        return newNode;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

}
