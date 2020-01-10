package dataStructure.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackUsingLL {
    StackNode top;
    Logger logger = LoggerFactory.getLogger(StackUsingLL.class);

    public StackUsingLL() {
        top = null;
    }

    boolean push(int num){
        StackNode node = new StackNode(num);
        if(top==null){
            top = node;
        } else {
            StackNode tmp = top;
            top = node;
            top.setNext(tmp);
        }
        return true;
    }

    int pop(){
        if(top==null){
            logger.error("Stack Empty");
            return -1;
        } else {
            StackNode node = top;
            top = top.getNext();
            return node.getData();
        }
    }

    boolean isEmpty(){
        return top==null;
    }

    int peek(){
        if(top==null){
            logger.error("Stack Empty");
            return -1;
        } else {
            return top.getData();
        }
    }

    private class StackNode {
        int data;
        StackNode next;

        public StackNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public StackNode getNext() {
            return next;
        }

        public void setNext(StackNode next) {
            this.next = next;
        }
    }

}

