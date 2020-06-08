package com.ygy.liberal.arithmetic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guoyao on 2019/1/15.
 * 链表
 */
public class TreeNode {

    private int value ;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int value, TreeNode left,TreeNode right) {
        this.value=value;
        this.left=left;
        this.right=right;
    }

    public static void print(TreeNode head) {
        if (head == null) {
            return;
        }
        print(head.left);
        System.out.println(head.value);
        print(head.right);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6, 8, 9, 10};
        TreeNode head = buildTreeNode(arr, 0, arr.length - 1);
        print(head);


        SingleNode singleNode1 = new SingleNode(1, null);
        SingleNode singleNode2 = new SingleNode(3, null);
        SingleNode singleNode3 = new SingleNode(4, null);
        SingleNode singleNode4 = new SingleNode(6, null);
        SingleNode singleNode5 = new SingleNode(8, null);
        SingleNode singleNode6 = new SingleNode(9, null);
        SingleNode singleNode7 = new SingleNode(10, null);
        singleNode1.next(singleNode2);
        singleNode2.next(singleNode3);
        singleNode3.next(singleNode4);
        singleNode4.next(singleNode5);
        singleNode5.next(singleNode6);
        singleNode6.next(singleNode7);
        buildTreeNode3(singleNode6);
        print(head);
    }

    private static TreeNode buildTreeNode3(SingleNode node) {
        if (node == null) {
            return null;
        }
        SingleNode middle = findMiddle(node);

        TreeNode head = new TreeNode(middle.value(), null, null);

        if (node == middle) {
            return head;
        }

        head.left = buildTreeNode3(node);
        head.right = buildTreeNode3(middle.next());
        return head;
    }

    private static SingleNode findMiddle(SingleNode node) {
        SingleNode slow = node;
        SingleNode fast = node;
        SingleNode pre = null;
        while (fast != null && fast.next() != null) {
            pre = slow;
            slow = slow.next();
            fast = fast.next().next();
        }

        if (pre != null) {
            pre.next(null);
        }
        return slow;
    }


    /**
     * 前序 中序 构建二叉树
     */
    private static Map<Integer, Integer> inOrderIndexMap = new HashMap<>();
    private static int rootIndex = 0;
    private static TreeNode buildTreeNode2(int[] inOrder, int[] preOrder, int left, int right) {
        if (left == right) {
            return null;
        }
        int inOrderIndex = inOrderIndexMap.get(preOrder[rootIndex]);

        TreeNode root = new TreeNode(preOrder[rootIndex], null, null);

        rootIndex++;

        root.left = buildTreeNode2(inOrder, preOrder, left, inOrderIndex);
        root.right = buildTreeNode2(inOrder, preOrder, inOrderIndex + 1, right);
        return root;
    }

    //int afterRootIndex =
    /**
     * 中序 后序构建二叉树
     */
    static int afterRootIndex = 0;
    private static TreeNode buildTreeNode4(int[] inOrder, int[] afterOrder, int left, int right) {
        if (left == right) {
            return null;
        }
        int inOrderIndex = inOrderIndexMap.get(afterOrder[rootIndex]);

        TreeNode root = new TreeNode(afterOrder[rootIndex], null, null);
        afterRootIndex--;


        root.left = buildTreeNode4(inOrder, afterOrder, left, inOrderIndex);
        root.right = buildTreeNode4(inOrder, afterOrder, inOrderIndex + 1, right);
        return root;
    }

    private static TreeNode buildTreeNode(int[] inOrder, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) >> 1;
        TreeNode head = new TreeNode(inOrder[mid], null, null);
        head.left = buildTreeNode(inOrder, left, mid - 1);
        head.right = buildTreeNode(inOrder, mid + 1, right);
        return head;
    }




    int pre_idx = 0;
    int[] preorder;
    int[] inorder;
    int[] afterOrder;
    HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();
    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right) {
            return null;
        }

        // pick up pre_idx element as a root
        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val, null, null);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        pre_idx++;
        // build left subtree
        root.left = helper(in_left, index);
        // build right subtree
        root.right = helper(index + 1, in_right);
        return root;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }
        return helper(0, inorder.length);
    }

    int afterIndex = 0;
    public TreeNode buildTree2(int[] afterOrder, int[] inorder) {
        this.afterOrder = afterOrder;
        this.inorder = inorder;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder) {
            idx_map.put(val, idx++);
        }
        afterIndex = afterOrder.length - 1;
        return helper2(0, inorder.length);
    }

    public TreeNode helper2(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left == in_right) {
            return null;
        }

        // pick up pre_idx element as a root
        int root_val = afterOrder[afterIndex];
        TreeNode root = new TreeNode(root_val, null, null);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        afterIndex --;
        // build right subtree
        root.right = helper(index + 1, in_right);
        // build left subtree
        root.left = helper(in_left, index - 1);
        return root;
    }


}
