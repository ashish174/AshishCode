package algo.practice.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * The Next greater Element for an element x is the first greater element
 * on the right side of x in the array.
 * [4, 5, 2, 25] -> [5, 25, 25, -1]
 */
public class NextGreaterElement {
    /**
     * Finds the next greater element for each element in the given array and returns a map where the keys are the elements
     * from the array and the values are their corresponding next greater elements. If an element does not have a next greater
     * element, its value in the map will be -1.
     *
     * @param arr the input array
     * @return a map containing the next greater element for each element in the array
     */
    public static Map<Integer, Integer> findNextGreaterElement(int[] arr){
        Map<Integer, Integer> NGE = new HashMap<>();
        if(arr.length==0){
            return NGE;
        }
        if(arr.length==1){
            NGE.put(arr[0], -1);
            return NGE;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[0]);
        for(int i = 1; i < arr.length; i++){
            int curr = arr[i];
            if(stack.isEmpty()){
                break;
            }
            while(!stack.isEmpty() && stack.peek() < curr){
                //Pop from stack if current array item is greater else keep on pushing the item on stack till you find something greater.
                //Anything remianing in stack in end will have no next greater value (-1)
                Integer poppedItem = stack.pop();
                NGE.put(poppedItem, curr);
            }
            stack.push(curr);
        }
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
}
