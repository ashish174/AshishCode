package algo.practice.tree.binarytree;


import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserialize {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        /*TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node1.right = node2;*/

        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();
        // Serialize the tree & print
        String serializeTreeStr = serializeDeserialize.serialize(node1);
        System.out.println("Serialized Tree : "+ serializeTreeStr);
        //[1,2,3,null,null,4,5,null,null,null,null,]
        // deserialize the string to form a tree. To test, serialize again to see if it generate initial string
        System.out.println("Input String for deserialization : "+ serializeTreeStr);
        TreeNode root = serializeDeserialize.deserialize(serializeTreeStr);
        String deserializeTreeStr = serializeDeserialize.serialize(root);
        System.out.println("Output String from printing constructed tree : "+deserializeTreeStr);

        //TreeNode root = serializeDeserialize.deserialize("[]");


    }


    /**
     * Encodes a binary tree to a single string representation. The serialization
     * is done using a level-order traversal (BFS) approach. If the input tree is
     * null, an empty list "[]" is returned.
     *
     * @param root the root of the binary tree to be serialized
     * @return a string representation of the binary tree
     */
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return "[]";
        }
        queue.add(root);
        return serializeIntoString(queue);

    }

    /**
     * Converts a queue of TreeNodes into a string representation. The queue is
     * expected to contain nodes from a binary tree, and the method processes
     * these nodes in the order they are dequeued. For each node, its value is
     * appended to the result string, and its children (if any) are added to the
     * end of the queue. The resulting string is a comma-separated list of node
     * values, enclosed in square brackets. If a node is null, "null" is appended
     * to the string.
     *
     * @param queue the queue of TreeNodes to be serialized into a string
     * @return a string representation of the TreeNodes in the queue
     */
    String serializeIntoString(Queue<TreeNode> queue) {
        StringBuilder strbuilder = new StringBuilder("[");
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if(node!=null){
                queue.add(node.left);
                queue.add(node.right);
            }
            String val = node == null ? "null" : String.valueOf(node.val);
            strbuilder.append(val).append(",");
        }
        if(strbuilder.length()>1){
            strbuilder.deleteCharAt(strbuilder.length()-1);
        }
        strbuilder.append("]");
        return strbuilder.toString();
    }

    /**
     * [1,2,3,null,null,4,5,null,null,null,null,]
     * Decodes your encoded data to tree.
     *
     * Decodes a serialized binary tree representation back into a binary tree.
     * The input string is expected to be in the format produced by the {@link #serialize(TreeNode)}
     * method, which is a level-order traversal (BFS) representation of the tree.
     * If the input string is "[]", indicating an empty tree, this method returns null.
     *
     * @param data the serialized binary tree representation as a string
     * @return the root of the deserialized binary tree, or null if the input string represents an empty tree
     */
    public TreeNode deserialize(String data) {
        Queue<TreeNode> queue = new LinkedList<>();
        data = data.replace("[", "");
        data = data.replace("]", "");
        if(data.equals("")){
            return null;
        }
        String[] split = data.split(",");
        Integer firstVal = split[0].equals("null") ? null : Integer.parseInt(split[0]);
        TreeNode root = new TreeNode(firstVal);
        queue.add(root);
        int i = 1;
        while (i < split.length) {
            TreeNode currNode = queue.remove();
            Integer left = split[i].equals("null") ? null : Integer.parseInt(split[i]);
            Integer right = split[i + 1].equals("null") ? null : Integer.parseInt(split[i + 1]);
            currNode.left= left==null?null:new TreeNode(left);
            currNode.right= right==null?null:new TreeNode(right);
            if(left!=null){
                queue.add(currNode.left);
            }
            if(right!=null){
                queue.add(currNode.right);
            }
            i = i + 2;
        }
        return root;
    }
}
