package algo.practice.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StackMain {
    public static Logger logger = LoggerFactory.getLogger(StackMain.class);
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
