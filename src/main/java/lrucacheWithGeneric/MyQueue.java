package lrucacheWithGeneric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyQueue {
  Logger logger = LoggerFactory.getLogger(MyQueue.class);
  Node head;
  Node tail;

  //Insert in beginning
  Node insert(Node elem) {
    if (head == null) {
      head = elem;
      tail = elem;
    } else {
      head.setLeft(elem);
      elem.setRight(head);
      head = elem;
    }
    return elem;
  }

  //Remove from end
  Node remove() {
    Node nodeTodelete;
    if (head == null) {
      nodeTodelete = null;
    } else if (head == tail) {
      nodeTodelete = head;
      head = null;
      tail = null;
    } else {
      nodeTodelete = tail;
      tail = tail.getLeft();
      tail.setRight(null);
    }
    return nodeTodelete;
  }

  Node remove(Node elem) {
    if (head == null) {
      return null;
    } else if (head == elem) {
      head = elem.getRight();
      head.setLeft(null);
    } else if (tail == elem) {
      tail = tail.getLeft();
      tail.setRight(null);
    } else {
      Node lnode = elem.getLeft();
      Node rnode = elem.getRight();
      lnode.setRight(rnode);
      rnode.setLeft(lnode);
    }
    return elem;
  }

  public void printQueue() {
    Node tmp = head;
    while (tmp != null) {
      logger.info("{} : {}", tmp.getKey(), tmp.getValue());
      tmp = tmp.getRight();
    }
  }
}
