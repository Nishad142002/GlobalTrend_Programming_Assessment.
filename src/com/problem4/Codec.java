package com.problem4;

import java.util.*;

public class Codec {

    // Serialize a binary tree to a string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Deserialize a string back to a binary tree
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(dataArray));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

        // Creating a test binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize the binary tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized tree: " + serialized);

        // Deserialize the string back to a binary tree
        TreeNode deserializedRoot = codec.deserialize(serialized);
        System.out.println("Deserialized tree root value: " + deserializedRoot.val);
    }
}

