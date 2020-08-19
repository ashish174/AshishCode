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
        String serializeTreeStr = serializeDeserialize.serialize(node1);
        System.out.println(serializeTreeStr);
        //[1,2,3,null,null,4,5,null,null,null,null,]
        TreeNode root = serializeDeserialize.deserialize(serializeTreeStr);
        String deserializeTreeStr = serializeDeserialize.serialize(root);
        System.out.println(deserializeTreeStr);

        //TreeNode root = serializeDeserialize.deserialize("[]");


    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if(root==null){
            return "[]";
        }
        queue.add(root);
        return serializeIntoString(queue);

    }

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

    //[1,2,3,null,null,4,5,null,null,null,null,]
    // Decodes your encoded data to tree.
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
