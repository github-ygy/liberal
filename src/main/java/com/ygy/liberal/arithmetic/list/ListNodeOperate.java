package com.ygy.liberal.arithmetic.list;

import com.ygy.liberal.arithmetic.SingleNode;

/**
 * Created by guoyao on 2019/1/15.
 * 链表操作
 * * 1.单链表反转
 * 2.链表中环的检测
 * 3.两个有序链表的合并
 * 4.删除链表倒数第n个节点
 * 5.求链表的中间节点
 */
public class ListNodeOperate {

    public static void main(String[] args) {
        SingleNode head = createDoubleNode(new int[]{1, 4, 5, 7, 8, 9});
        printNode(head);
        //校验回文
        //System.out.println(checkIsCanReverse(head));
        //1.反转链表
        printNode(reverseNode2(head));
        //2.校验环
        //SingleNode last = getLastNode(head);
        //last.next(head);
        //System.out.println(checkIsRing(head));
        //3.两个有序链表的合并
        //SingleNode head2 = createDoubleNode(new int[]{2, 3, 8});
        //printNode(mergeOrderNode(head, head2));

    }

    private static SingleNode mergeOrderNode(SingleNode firstOrder, SingleNode secondOrder) {
        SingleNode head = new SingleNode(Integer.MIN_VALUE, null);
        SingleNode temp = head;
        while (firstOrder != null && secondOrder != null) {
            if (firstOrder.value() > secondOrder.value()) {
                temp = temp.next(secondOrder);
                secondOrder = secondOrder.next();
            } else {
                temp = temp.next(firstOrder);
                firstOrder = firstOrder.next();
            }
        }
        while (firstOrder != null) {
            temp = temp.next(firstOrder);
            firstOrder = firstOrder.next();
        }
        while (secondOrder != null) {
            temp = temp.next(secondOrder);
            secondOrder = secondOrder.next();
        }
        return head.next();
    }

    private static SingleNode getLastNode(SingleNode doubleNode) {
        SingleNode temp = doubleNode;
        while (temp.next() != null) {
            temp = temp.next();
        }
        return temp;
    }

    private static boolean checkIsRing(SingleNode singleNode) {
        if (singleNode == null) {
            return false;
        }
        if (singleNode == singleNode.next()) {
            return true;
        }
        SingleNode slow = singleNode;
        SingleNode fast = singleNode;
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    /**
     * 反转链表
     *
     * @param baseNode
     * @return
     */
    private static SingleNode reverseNode(SingleNode baseNode) {
        SingleNode head = baseNode;
        //反转node
        SingleNode prev = null;
        while (head != null) {
            SingleNode temp = head.next();
            head.next(prev);
            prev = head;
            head = temp;
        }
        return prev;
    }
    //1234 null
    //234  1
    //34   2 1
    //4    3 2 1
    //null 4 3 2 1

    private static SingleNode reverseNode2(SingleNode baseNode) {
        SingleNode head = baseNode;
        SingleNode temp = null;
        while (head != null) {
            SingleNode next = head.next();
            head.next(temp);
            temp = head;
            head = next;
        }
        return temp;
    }

    private static SingleNode reverseNode1(SingleNode baseNode) {
        SingleNode head = baseNode;
        SingleNode temp = null;
        SingleNode prev = null;
        while (head != null) {
            prev = head.next();
            head.next(temp);
            temp = head;
            head = prev;
        }
        return temp;
    }

    /**
     * 校验字符串回文
     *
     * @param doubleNode
     * @return
     */
    private static boolean checkIsCanReverse(SingleNode doubleNode) {
        SingleNode slowNode = doubleNode;
        SingleNode fastNode = doubleNode;
        while (fastNode != null && fastNode.next() != null) {
            slowNode = slowNode.next();
            fastNode = fastNode.next().next();
        }
        slowNode = reverseNode(slowNode);
        fastNode = doubleNode;
        while (slowNode != null) {
            if (fastNode.value() != slowNode.value()) {
                return false;
            }
            slowNode = slowNode.next();
            fastNode = fastNode.next();
        }
        return true;
    }


    private static SingleNode createDoubleNode(int[] ints) {
        SingleNode head = null;
        SingleNode temp = null;
        for (int data : ints) {
            if (head == null) {
                head = new SingleNode(data, null);
                temp = head;
                continue;
            }
            temp.next(new SingleNode(data, null));
            temp = temp.next();
        }
        return head;
    }

    private static void printNode(SingleNode singleNode) {
        while (singleNode != null) {
            System.out.print(singleNode.value());
            singleNode = singleNode.next();
        }
        System.out.println();
    }
}
