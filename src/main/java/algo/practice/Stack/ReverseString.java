package algo.practice.Stack;

import java.util.Stack;

public class ReverseString {
    public static String reverseString(String string){
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for(char ch : string.toCharArray()){
            stack.push(ch);
        }
        while(!stack.empty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseString("Ashish Kumar"));
    }
}
