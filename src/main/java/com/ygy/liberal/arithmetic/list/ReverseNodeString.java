package com.ygy.liberal.arithmetic.list;

import com.ygy.liberal.arithmetic.SingleNode;

/**
 * Created by guoyao on 2019/1/15.
 * 回文字符串的问题
 */
public class ReverseNodeString {

    public static void main(String[] args) {
        SingleNode doubleNode=createDoubleNode();
        System.out.println(doubleNode.length());

        System.out.println(checkIsCanReverse(doubleNode));
    }

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



    private static SingleNode createDoubleNode() {
        return new SingleNode(0,
                        new SingleNode(1,
                            new SingleNode(2,
                                        new SingleNode(1,
                                                new SingleNode(0, null)))));
    }
}
