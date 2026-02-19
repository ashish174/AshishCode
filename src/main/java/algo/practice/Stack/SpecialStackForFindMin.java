package algo.practice.Stack;

import java.util.Stack;


/**
 * A specialized implementation of a stack that keeps track of the minimum element at each step.
 * i.e. findMin = O(1)
 * This allows for efficient retrieval of the current minimum element without having to scan the entire stack.
 *
 * Approach:
 * Maintains two stacks: one for storing all elements and another for tracking the minimum element at each level.
 * On each push, updates the min stack with the smaller of the new element or the current minimum.
 * On each pop, both stacks are popped together, ensuring the min stack always reflects the minimum of the current state.
 * Allows for O(1) retrieval of the current minimum element.
 */
public class SpecialStackForFindMin {
    Stack<Integer> stackForElement = new Stack<>();
    Stack<Integer> stackForMinElement = new Stack<>();

    /**
     * Pushes a new element onto the stack and updates the minimum element tracking.
     *
     * @param key the element to be pushed onto the stack
     */
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

    /**
     * Removes the top element from the stack and updates the minimum element tracking.
     *
     * @return the removed element, or -1 if the stack is empty
     */
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
        SpecialStackForFindMin specialStackForFindMin = new SpecialStackForFindMin();
        specialStackForFindMin.push(16);
        specialStackForFindMin.push(18);
        specialStackForFindMin.push(5);
        specialStackForFindMin.push(10);
        specialStackForFindMin.printStack(specialStackForFindMin.stackForElement);
        specialStackForFindMin.printStack(specialStackForFindMin.stackForMinElement);
        System.out.println(specialStackForFindMin.findMin());
    }

}
