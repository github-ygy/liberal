package com.ygy.liberal.arithmetic.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.ygy.liberal.design.simplefactory.Factory;

import java.sql.Time;
import java.util.*;

/**
 * Created by guoyao on 2019/2/25.
 */
public class BDFS {

    public static void main(String[] args) {
        //System.out.println(levelOrder(buildNodeTree()));
        System.out.println(zhiLevelPrint(buildNodeTree()));
        //System.out.println(getMaxDepth(buildNodeTree()));
        //System.out.println(hasPathSum(buildNodeTree(),49));
        //System.out.println(hasPathSumDfs(buildNodeTree(),49));
        //System.out.println(printNode(Convert(buildNodeTree())));
        //System.out.println(isSymmetrical(buildNodeTree()));
        //System.out.println(mergeTrees(buildNodeTree(), buildNodeTree()));
        //prePrint(mirror(buildNodeTree()));
        System.out.println(isValidBst(buildNodeTree()));
        System.out.println(isValidBstDFS(buildNodeTree()));
        //prePrint(buildNodeTree());
        //preDfsV3(buildNodeTree());
        //System.out.println("-----------------------");
        //preDfs(buildNodeTree());
        //System.out.println("-----------------------");
        //prePrintDFS(buildNodeTree());
        //System.out.println("--------------");
        //midPrint(buildNodeTree());
        //System.out.println("--------------");
        //midPrintDFS(buildNodeTree());
        //midDfs(buildNodeTree());
        //System.out.println("--------------");
        //postPrint(buildNodeTree());
        //System.out.println("--------------");
        //postDfs(buildNodeTree());
        //postPrintDFS(buildNodeTree());
        //findDepth(buildNodeTree());
        //findMinDepth(buildNodeTree());
        //findMaxDepth(buildNodeTree());
    }

    private static List<Integer> printNode(Node convert) {
        List<Integer> rightValues = Lists.newArrayList();
        rightValues.add(convert.value);
        while (convert.rightNode != null) {
            rightValues.add(convert.rightNode.value);
            convert = convert.rightNode;
        }
        return rightValues;
    }

    /**
     *              10
     *      5                15
     *   3     8        13        18
     * 1  4  7   9   11   14   17  19
     *
     * 前序遍历 10 5 3 1 4 8 7 9 15 13 11 14 18 17 19
     * 中序遍历 1 3 4 5 7 8 9 10 11 13 14 15 17 18 19
     * 后序遍历 1 4 3 7 9 8 5 11 14 13 17 19 18 15 10
     * @return
     */
    private static Node buildNodeTree() {
        Node head = new Node(10);
        head.leftNode = new Node(5, new Node(3, new Node(1), new Node(4)), new Node(8, new Node(7), new Node(9)));
        head.rightNode = new Node(15, new Node(13, new Node(11), new Node(14)), new Node(18, new Node(17), new Node(19)));
        return head;
    }
    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     */
    public static Node mirror(Node pRoot) {
        if (pRoot != null) {
            Node right = pRoot.rightNode;
            Node left = pRoot.leftNode;
            pRoot.leftNode = mirror(right);
            pRoot.rightNode = mirror(left);
        }
        return pRoot;
    }

    public static Node mirrorV2(Node pRoot) {
        return null;
    }

    /**
     * 给定一个二叉树根节点，请你判断这棵树是不是二叉搜索树。
     *
     * 二叉搜索树满足每个节点的左子树上的所有节点均小于当前节点且右子树上的所有节点均大于当前节点。
     * @param pRoot
     * @return
     */
    public static boolean isValidBst(Node pRoot) {
        return false;
    }



    public static boolean isValidBstDFS(Node pRoot) {
        if (pRoot == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!stack.isEmpty() || pRoot != null) {
            while (pRoot != null) {
                stack.push(pRoot);
                pRoot = pRoot.leftNode;
            }
            if (!stack.isEmpty()) {
                Node pop = stack.pop();
                if (pre != null && pop.value < pre.value) {
                    return false;
                }
                pre = pop;
                pRoot = pop.rightNode;
            }
        }
        return true;
    }

    /**
     * 已知两颗二叉树，将它们合并成一颗二叉树。合并规则是：都存在的结点，就将结点值加起来，否则空的位置就由另一个树的结点来代替
     * @param t1
     * @param t2
     * @return
     */
    public static Node mergeTrees (Node t1, Node t2) {
        // write code here
        if (t1 == null && t2 != null) {
            return t2;
        }
        if (t1 != null && t2 == null) {
            return t1;
        }
        if (t1 != null) {
            t1.value = t1.value + t2.value;
            t1.leftNode = mergeTrees(t1.leftNode, t2.leftNode);
            t1.rightNode = mergeTrees(t1.rightNode, t2.rightNode);
            return t1;
        }
        return null;
    }


    public static boolean isSymmetricalV2 (Node pRoot) {
        // write code here
        if (pRoot == null) {
            return true;
        }
        Stack<Node> leftStack = new Stack<>();
        Stack<Node> rightStack = new Stack<>();
        Node left = pRoot;
        Node right = pRoot;
        while ((!leftStack.isEmpty() && !rightStack.isEmpty()) || (left != null && right != null)) {
            while (left != null) {
                leftStack.push(left);
                left = left.leftNode;
            }
            while (right != null) {
                rightStack.push(right);
                right = right.rightNode;
            }
            Node leftPop = leftStack.pop();
            Node rightPop = rightStack.pop();
            if (leftPop.value != rightPop.value) {
                return false;
            }
            left = leftPop.rightNode;
            right = rightPop.leftNode;
        }
        if (!leftStack.isEmpty() || !rightStack.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 判断是否是镜像
     * @param pRoot
     * @return
     */
    public static boolean isSymmetrical (Node pRoot) {
        // write code here
        if (pRoot == null) {
            return true;
        }
        return isEqual(pRoot.leftNode, pRoot.rightNode);
    }

    public static boolean isEqual(Node left, Node right) {
        if (left == null || right == null) {
            if (left != right) {
                return false;
            }else {
                return true;
            }
        }
        if (left.value != right.value) {
            return false;
        }
        return isEqual(left.leftNode, right.rightNode) && isEqual(left.rightNode, right.leftNode);
    }

    /**
     *注意:
     * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
     * 2.返回链表中的第一个节点的指针
     * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
     * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出
     * @param pRootOfTree
     * @return
     */
    public static Node Convert(Node pRootOfTree) {
        Stack<Node> stack = new Stack<>();
        Node prev = null;
        Node first = null;
        while (!stack.isEmpty() || pRootOfTree != null) {
            while (pRootOfTree != null) {
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.leftNode;
            }
            if (!stack.isEmpty()) {
                pRootOfTree = stack.pop();
                if (prev == null) {
                    pRootOfTree.leftNode = null;
                    prev = pRootOfTree;
                    first = pRootOfTree;
                }else {
                    pRootOfTree.leftNode = prev;
                    prev.rightNode = pRootOfTree;
                    prev = pRootOfTree;
                }
                pRootOfTree = pRootOfTree.rightNode;
            }
        }
        return first;
    }

    public boolean hasPathSumV2 (Node root, int sum) {
        // write code here
        if(root == null) return false;
        if(root.leftNode == null && root.rightNode == null && root.value == sum) return true;
        return hasPathSum(root.leftNode,sum - root.value) || hasPathSum(root.rightNode,sum - root.value);
    }

    public static boolean hasPathSumDfs(Node root, int sum) {
        // write code here
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            if (pop.leftNode == null && pop.rightNode == null && sum == pop.value) {
                return true;
            }
            if (pop.rightNode != null) {
                pop.rightNode.value = pop.value + pop.rightNode.value;
                stack.push(pop.rightNode);
            }
            if (pop.leftNode != null) {
                pop.leftNode.value = pop.value + pop.leftNode.value;
                stack.push(pop.leftNode);
            }
        }
        return false;
    }


    public static boolean hasPathSum (Node root, int sum) {
        // write code here
        if (root == null) {
            return sum == 0;
        }
        // write code here
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        Set<Integer> sums = new HashSet<>();
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            while (size-- > 0) {
                Node first = nodes.removeFirst();
                sums.add(first.value);
                if (first.leftNode != null) {
                    first.leftNode.value = first.value + first.leftNode.value;
                    nodes.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    first.rightNode.value = first.value + first.rightNode.value;
                    nodes.add(first.rightNode);
                }
            }
        }
        return sums.contains(sum);
    }

    public static int getMaxDepth (Node root) {
        if (root == null) {
            return 0;
        }
        // write code here
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(root);
        int dept = 0;
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            dept++;
            while (size-- > 0) {
                Node first = nodes.removeFirst();
                if (first.leftNode != null) {
                    nodes.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    nodes.add(first.rightNode);
                }
            }
        }
        return dept;
    }

    //层序遍历
    public static ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        ArrayList<ArrayList<Integer>> resultArray = new ArrayList<>();
        if (root == null) {
            return resultArray;
        }
        LinkedList<Node> linkedList = Lists.newLinkedList();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            ArrayList<Integer> levelArray = new ArrayList<>();
            resultArray.add(levelArray);
            while (size-- > 0) {
                Node first = linkedList.removeFirst();
                levelArray.add(first.value);
                if (first.leftNode != null) {
                    linkedList.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    linkedList.add(first.rightNode);
                }
            }
        }
        return resultArray;
    }

    /**
     * 之字排列
     *
     * @param pRoot
     * @return
     */
    public static ArrayList<ArrayList<Integer>> zhiLevelPrint (Node pRoot) {
        // write code here
        ArrayList<ArrayList<Integer>> resultArray = new ArrayList<>();
        if (pRoot == null) {
            return resultArray;
        }
        LinkedList<Node> linkedList = Lists.newLinkedList();
        linkedList.add(pRoot);
        int idx = 0;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            ArrayList<Integer> levelArray = new ArrayList<>();
            resultArray.add(levelArray);
            idx++;
            while (size-- > 0) {
                Node first = linkedList.removeFirst();
                if (first.leftNode != null) {
                    linkedList.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    linkedList.add(first.rightNode);
                }
                if (idx % 2 == 0) {
                    levelArray.add(0, first.value);
                }else {
                    levelArray.add(first.value);
                }
            }
        }
        return resultArray;
    }

    private static void preDfsV3(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.rightNode != null) {
                stack.push(pop.rightNode);
            }
            if (pop.leftNode != null) {
                stack.push(pop.leftNode);
            }
        }
    }

    private static void prePrintDFS(Node root){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.rightNode != null) {
                stack.push(pop.rightNode);
            }
            if (pop.leftNode != null) {
                stack.push(pop.leftNode);
            }
        }
    }

    private static void midPrintDFS(Node root){
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            while (root != null) {
                stack.push(root);
                root = root.leftNode;
            }

            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println(root.value);
                root = root.rightNode;
            }
        }
    }

    private static void postPrintDFS(Node root){
        Stack<Node> stack = new Stack<>();
        Node prev = root;
        while (!stack.isEmpty() || root != null){
            while (root != null) {
                stack.push(root);
                root = root.leftNode;
            }

            if (!stack.isEmpty()) {
                Node temp = stack.peek().rightNode;
                if (temp == null || prev == temp) {
                    root = stack.pop();
                    System.out.println(root.value);
                    prev = root;
                    root = null;
                }else {
                    root = temp;
                }

            }
        }
    }

    private static void findMaxDepth(Node node) {
        int depth = 0;
        if (node == null) {
            return ;
        }
        LinkedList<Node> linkedList = Lists.newLinkedList();
        linkedList.add(node);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            depth++;
            while (size > 0) {
                Node first = linkedList.removeFirst();
                if (first.leftNode != null) {
                    linkedList.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    linkedList.add(first.rightNode);
                }
                size--;
            }
        }
        System.out.println(depth);
    }

    private static void findMinDepth(Node node) {
        int depth = 0;
        if (node == null) {
            return ;
        }
        LinkedList<Node> linkedList = Lists.newLinkedList();
        linkedList.add(node);
        boolean isFind = false;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            depth++;
            while (size-- > 0) {
                Node first = linkedList.removeFirst();
                if (first.leftNode == null && first.rightNode == null) {
                    isFind = true;
                    break;
                }
                if (first.leftNode != null) {
                    linkedList.add(first.leftNode);
                }
                if (first.rightNode != null) {
                    linkedList.add(first.rightNode);
                }
            }
            if (isFind) {
                break;
            }
        }
        System.out.println(depth);
    }

    private static void findDepth(Node head) {
        LinkedList<Node> queue = Lists.newLinkedList();
        queue.add(head);
        int depth = 0;
        while (!queue.isEmpty()) {
            int length = queue.size();
            depth++;
            while (length-- > 0) {
                Node poll = queue.poll();
                if (poll.leftNode != null) {
                    queue.add(poll.leftNode);
                }
                if (poll.rightNode != null) {
                    queue.add(poll.rightNode);
                }
            }
        }
        System.out.println(depth);
    }

    private static void preDfs(Node head) {
        List<Node> treeNodes = Lists.newArrayList();
        Stack<Node> stack = new Stack<Node>();
        treeNodes.add(head);
        stack.add(head);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop);
            //左节点不为null
            if (pop.rightNode != null) {
                stack.push(pop.rightNode);
            }
            if (pop.leftNode != null) {
                stack.push(pop.leftNode);
            }
        }
    }

    private static void midDfs(Node head) {
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.leftNode;
            }
            if (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head);
                head = head.rightNode;
            }
        }
    }

    private static void postDfs(Node head) {
        Stack<Node> stack = new Stack<>();
        Node prev = head;
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head = head.leftNode;
            }
            if (!stack.isEmpty()) {
                Node temp = stack.peek().rightNode;
                if (temp == null || temp == prev) {
                    head = stack.pop();
                    System.out.println(head);
                    prev = head;
                    head = null;
                } else {
                    head = temp;
                }
            }
        }
    }




    private static void prePrint(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head);
        prePrint(head.leftNode);
        prePrint(head.rightNode);
    }

    private static void midPrint(Node head) {
        if (head == null) {
            return;
        }
        midPrint(head.leftNode);
        System.out.println(head);
        midPrint(head.rightNode);
    }

    private static void postPrint(Node head) {
        if (head == null) {
            return;
        }
        postPrint(head.leftNode);
        postPrint(head.rightNode);
        System.out.println(head);
    }



    private static class Node {

        private int value;

        protected Node leftNode;

        protected Node rightNode;

        public Node(int value) {
            this.value = value;
        }

        public Node(int value, Node leftNode, Node rightNode) {
            this.value = value;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
