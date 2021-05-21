package algo.practice.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {
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
        int[] arr = {11,13,21,3};
        Map<Integer, Integer> nextGreaterElementMap = findNextGreaterElement(arr);
        System.out.println(nextGreaterElementMap.entrySet());
    }
}