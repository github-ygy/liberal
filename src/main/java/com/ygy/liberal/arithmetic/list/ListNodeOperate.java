package com.ygy.liberal.arithmetic.list;

import com.ygy.liberal.arithmetic.SingleNode;

/**
 * Created by guoyao on 2019/1/15.
 * 链表操作
 */
public class ListNodeOperate {

    public static void main(String[] args) {
        SingleNode doubleNode=createDoubleNode(new int[]{1,2,2,1});
        printNode(doubleNode);
        //校验回文
        //System.out.println(checkIsCanReverse(doubleNode));
        //反转链表
        //printNode(reverseNode(doubleNode));
    }



    private static void printNode(SingleNode singleNode) {
        while (singleNode != null) {
            System.out.print(singleNode.value());
            singleNode=singleNode.next();
        }
        System.out.println();
    }


    /**
     * 校验字符串回文
     * @param doubleNode
     * @return
     */
    private static boolean checkIsCanReverse(SingleNode doubleNode) {
        SingleNode slowNode = doubleNode;
        SingleNode fastNode = doubleNode;
        while (fastNode!=null && fastNode.next()!=null ) {
            slowNode=slowNode.next();
            fastNode=fastNode.next().next();
        }
        slowNode=reverseNode(slowNode);
        fastNode=doubleNode;
        while (slowNode != null) {
            if (fastNode.value() != slowNode.value()) {
                return false;
            }
            slowNode=slowNode.next();
            fastNode=fastNode.next();
        }
        return true;
    }

    /**
     * 反转链表
     * @param baseNode
     * @return
     */
    private static SingleNode reverseNode(SingleNode baseNode) {
        SingleNode head  = baseNode;
        //反转node
        SingleNode temp=null;
        SingleNode prev=null;
        while (head != null) {
            temp=head.next();
            head.next(prev);
            prev=head;
            head=temp;
        }
        return prev;
    }

    private static SingleNode createDoubleNode(int[] ints) {
        SingleNode head= null;
        SingleNode temp= null;
        for (int data : ints) {
            if (head == null) {
                head=new SingleNode(data, null);
                temp=head;
                continue;
            }
            temp.next(new SingleNode(data, null));
            temp=temp.next();
        }
        return head;
    }
}
