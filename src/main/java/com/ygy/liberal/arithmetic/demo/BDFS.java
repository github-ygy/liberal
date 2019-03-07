package com.ygy.liberal.arithmetic.demo;

import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by guoyao on 2019/2/25.
 */
public class BDFS {

    public static void main(String[] args) {
        //prePrint(buildNodeTree());
        //System.out.println("-----------------------");
        //preDfs(buildNodeTree());
        //midPrint(buildNodeTree());
        //System.out.println("--------------");
        //midDfs(buildNodeTree());
        //postPrint(buildNodeTree());
        //System.out.println("--------------");
        //postDfs(buildNodeTree());

        findDepth(buildNodeTree());
    }

    private static void findDepth(Node head) {
        LinkedList<Node> queue=Lists.newLinkedList();
        queue.add(head);
        int depth = 0;
        while (!queue.isEmpty()) {
            int length=queue.size();
            depth++;
            while (length-- > 0) {
                Node poll=queue.poll();
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
        List<Node> treeNodes=Lists.newArrayList();
        Stack<Node> stack=new Stack<Node>();
        treeNodes.add(head);
        stack.add(head);
        while (!stack.isEmpty()) {
            Node pop=stack.pop();
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
        Stack<Node> stack=new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head=head.leftNode;
            }

            if (!stack.isEmpty()) {
                head=stack.pop();
                System.out.println(head);
                head=head.rightNode;
            }

        }
    }

    private static void postDfs(Node head) {
        Stack<Node> stack=new Stack<Node>();
        Node prev=head;
        while (!stack.isEmpty() || head != null) {
            while (head != null) {
                stack.push(head);
                head=head.leftNode;
            }

            if (!stack.isEmpty()) {
                Node temp=stack.peek().rightNode;
                if(temp == null||temp == prev){
                    head = stack.pop();
                    System.out.println(head);
                    prev=head;
                    head = null;
                }else{
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

    private static Node buildNodeTree() {
        Node head=new Node(10);
        head.leftNode=new Node(5, new Node(3, new Node(1), new Node(4)), new Node(8, new Node(7), new Node(9)));
        head.rightNode=new Node(15, new Node(13, new Node(11), new Node(14)), new Node(18, new Node(17), new Node(19)));
        return head;
    }

    private static class Node {

        private int value;

        protected Node leftNode;

        protected Node rightNode;

        public Node(int value) {
            this.value=value;
        }

        public Node(int value, Node leftNode, Node rightNode) {
            this.value=value;
            this.leftNode=leftNode;
            this.rightNode=rightNode;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
