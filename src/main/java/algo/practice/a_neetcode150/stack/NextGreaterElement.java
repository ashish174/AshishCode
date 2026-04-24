package algo.practice.a_neetcode150.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The Next greater Element for an element x is the first greater element
 * on the right side of x in the array.
 * [4, 5, 2, 25] -> [5, 25, 25, -1]
 *
 * Approach:
 * Uses a stack to efficiently find the next greater element for each array value in a single pass.
 * For each element, pops from the stack all elements smaller than the current element and records the current element as their next greater.
 * Any elements left in the stack after traversal do not have a next greater, so they are assigned -1.
 * This results in an overall O(n) time complexity.
 */
public class NextGreaterElement {
    /**
     * Finds the next greater element for each element in the given array and returns a map where the keys are the elements
     * from the array and the values are their corresponding next greater elements. If an element does not have a next greater
     * element, its value in the map will be -1.
     *
     * @param arr the input array
     * @return a map containing the next greater element for each element in the array
     *
     * Note: This code assume arr elements are unique. In case you have duplicates in arrays ex: [4, 4, 5, 2, 2, 3],
     * then Map keys will get overwritten.
     * In such cases int[] will be the ideal output of the function. Check findNextGreaterElementArray()
     *
     */
    public static Map<Integer, Integer> findNextGreaterElement(int[] arr){
        Map<Integer, Integer> NGE = new HashMap<>();
        if(arr.length==0){
            return NGE;
        }
        //This code is redundant
        /*if(arr.length==1){
            NGE.put(arr[0], -1);
            return NGE;
        }*/
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for(int i = 1; i < arr.length; i++){
            int curr = arr[i];
            while(!stack.isEmpty() && stack.peek() < curr){
                //Pop from stack if current array item is greater else keep on pushing the item on stack till you find something greater.
                Integer poppedItem = stack.pop();
                NGE.put(poppedItem, curr);
            }
            stack.push(curr);
        }

        //Anything remianing in stack in end will have no next greater value (-1)
        // Remaining elements have no next greater element
        while(!stack.isEmpty()){
            NGE.put(stack.pop(), -1);
        }
        return NGE;
    }

    public static void main(String[] args) {
        int[] arr = {11,13,21,3,1,5};
        //11,13,21,3,1,5
        Map<Integer, Integer> nextGreaterElementMap = findNextGreaterElement(arr);
        System.out.println(nextGreaterElementMap.entrySet());
    }


    /**
     * For each element in the given array, finds the next greater element
     * to its right. If no such element exists, the result for that index is -1.
     *
     * This version handles duplicates correctly because it works by index,
     * not by value.
     *
     * Example:
     *   arr = [4, 5, 2, 25]
     *   returns [5, 25, 25, -1]
     *
     *   arr =   [4, 4, 5, 2, 2, 3]
     *   returns [5, 5, -1, 3, 3, -1]
     *
     * @param arr the input array
     * @return an array result where result[i] is the next greater element to the right of arr[i], or -1 if none
     */
    public static int[] findNextGreaterElementArray(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        if (n == 0) {
            return result; // empty array
        }

        Stack<Integer> stack = new Stack<>(); // will store indices
        // Initialize all results to -1 by default
        for (int i = 0; i < n; i++) {
            result[i] = -1;
        }

        for (int i = 0; i < n; i++) {
            int curr = arr[i];

            // Resolve NGE for any previous indices whose value is smaller than curr
            while (!stack.isEmpty() && arr[stack.peek()] < curr) {
                int idx = stack.pop();
                result[idx] = curr;
            }

            // Push current index onto the stack
            stack.push(i);
        }

        // Any indices left in stack already have result[i] = -1
        return result;
    }

}
