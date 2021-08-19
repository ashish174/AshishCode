package algo.practice.tree.binarytree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * each internal node corresponds to the operator(+,-,/,* etc)
 * and each leaf node corresponds to the operand (1,2,3,4,5 etc)
 * example expression tree for 3 + ((5+9)*2)
 *
 * Before processing any node, we need to process leftSubtree and rightSubtree
 */
public class ExpressionTree {
    private static Logger logger = LoggerFactory.getLogger(ExpressionTree.class);

    public static void main(String[] args) {
        Node root = new Node("+");
        root.left = new Node("3");
        root.right = new Node("*");
        root.right.right  = new Node("2");
        root.right.left = new Node("+");
        root.right.left.left = new Node("5");
        root.right.left.right = new Node("9");

        logger.info("Value of expression is {}", evaluateExpressionTree(root));

        root = new Node("+");
        root.left = new Node("*");
        root.left.left = new Node("5");
        root.left.right = new Node("4");
        root.right = new Node("-");
        root.right.left = new Node("100");
        root.right.right = new Node("/");
        root.right.right.left = new Node("20");
        root.right.right.right = new Node("2");
        logger.info("Value of expression is {}", evaluateExpressionTree(root));


    }

    /**
     * Assumption is each internal node must have 2 children
     * @param root
     * @return
     */
    private static int evaluateExpressionTree(Node root) {
        if (root == null) {
            return 0;
        }
        // if leaf node
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.key);
        }
        int lSubtree = evaluateExpressionTree(root.left);
        int rSubtree = evaluateExpressionTree(root.right);
        int result = 0;
        switch (root.key) {
            case "/":
                result = lSubtree / rSubtree;
                break;
            case "*":
                result = lSubtree * rSubtree;
                break;
            case "+":
                result = lSubtree + rSubtree;
                break;
            case "-":
                result = lSubtree - rSubtree;
                break;
        }
        return result;
    }

    static class Node {
        String key;
        Node left;
        Node right;

        public Node(String key) {
            this.key = key;
        }
    }
}

