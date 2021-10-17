package miscellenous;// Java proram to evaluate value of a postfix expression

import java.util.Stack;

/**
 * 2 3 1 * + 9 -
 */
public class PostFixEvaluation {
    // Method to evaluate value of a postfix expression
    static int evaluatePostfix(String exp) {
        //create a stack
        Stack<Integer> stack = new Stack<>();

        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if (Character.isDigit(c))
                stack.push(c - '0');

                // If the scanned character is an operator, pop two
                // elements from stack apply the operator
            else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                int result = performArithmaticOps(val1, val2, c);
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static int performArithmaticOps(int val1, int val2, char operator) {
        switch (operator) {
            case '+':
                return val2 + val1;
            case '-':
                return val2 - val1;
            case '/':
                return val2 / val1;
            case '*':
                return val2 * val1;
            default:
                return -1;
        }
    }

    // Driver program to test above functions
    public static void main(String[] args) {
        String exp = "231*+9-";
        System.out.println("postfix evaluation: " + evaluatePostfix(exp));
    }
}
// Contributed by Sumit Ghosh 
