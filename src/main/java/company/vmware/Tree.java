package company.vmware;

public class Tree {
    public static void main(String[] args) {

        //boolean isTreeMirrorImage = checkIfTreeAreMirrorImage(firstRoot, secondRoot);

    }

    public static boolean checkIfTreeAreMirrorImage(Node firstRoot, Node secondRoot) {
        if (firstRoot == null && secondRoot == null) {
            return true;
        }
        if (firstRoot == null || secondRoot == null) {
            return false;
        }
        return firstRoot.data == secondRoot.data
                && checkIfTreeAreMirrorImage(firstRoot.getLeft(), secondRoot.getRight())
                && checkIfTreeAreMirrorImage(firstRoot.getRight(), secondRoot.getLeft());
    }

    class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Tree.Node getLeft() {
            return left;
        }

        public void setLeft(Tree.Node left) {
            this.left = left;
        }

        public Tree.Node getRight() {
            return right;
        }

        public void setRight(Tree.Node right) {
            this.right = right;
        }
    }


}
