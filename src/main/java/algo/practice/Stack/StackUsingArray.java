package algo.practice.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackUsingArray {
    int maxSize;
    int[] a;
    int top;
    Logger logger = LoggerFactory.getLogger(StackUsingArray.class);

    public StackUsingArray() {
        top = -1;
        maxSize = 100;
        a = new int[maxSize];
    }

    public StackUsingArray(int size) {
        top = -1;
        maxSize = size;
        a = new int[maxSize];
    }

    boolean push(int num){
        if(top>=maxSize){
            logger.error("Stack Overflow ");
            return false;
        } else {
            a[++top] = num;
            return true;
        }
    }

    int pop(){
        if(top<0){
            logger.error("Stack Empty ");
        } else {
            int num = a[top];
            top--;
            return num;
        }
        return -1;
    }

    int peek(){
        if(top<0){
            logger.error("Stack Empty ");
        } else {
            return a[top];
        }
        return -1;
    }

    boolean isEmpty(){
        return top<0;
    }

    public static void main(String[] args) {
        StackUsingArray stackUsingArray = new StackUsingArray(5);
        stackUsingArray.push(4);
        stackUsingArray.push(3);
        stackUsingArray.pop();
        stackUsingArray.pop();
        stackUsingArray.pop();

        StackUsingLL stackUsingLL = new StackUsingLL();
        stackUsingLL.push(4);
        stackUsingLL.push(7);
        stackUsingLL.pop();
        stackUsingLL.pop();
        stackUsingLL.pop();
    }
}
