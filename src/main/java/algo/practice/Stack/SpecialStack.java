package algo.practice.Stack;

import java.util.Stack;


public class SpecialStack {
    Stack<Integer> stackForElement = new Stack<>();
    Stack<Integer> stackForMinElement = new Stack<>();

    public void push(int key){
        if(stackForElement.isEmpty()){
            stackForElement.push(key);
            stackForMinElement.push(key);
        } else{
            stackForElement.push(key);
            int minSoFar = stackForMinElement.peek();
            int minForCurrentElem = Math.min(key, minSoFar);
            stackForMinElement.push(minForCurrentElem);
        }
    }

    public int pop(){
        if(stackForElement.isEmpty()){
            System.out.println("Stack underflow");
            return -1;
        }
        stackForMinElement.pop();
        return stackForElement.pop();
    }

    public int findMin(){
        return stackForMinElement.peek();
    }

    public void printStack(Stack<Integer> stack){
        System.out.println(stack);
    }

    public static void main(String[] args) {
        SpecialStack specialStack = new SpecialStack();
        specialStack.push(16);
        specialStack.push(18);
        specialStack.push(5);
        specialStack.push(10);
        specialStack.printStack(specialStack.stackForElement);
        specialStack.printStack(specialStack.stackForMinElement);
        System.out.println(specialStack.findMin());
    }

}
