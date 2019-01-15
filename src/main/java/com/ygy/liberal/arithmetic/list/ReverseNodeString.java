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
        int index = 0;
        SingleNode slowNode = doubleNode;
        SingleNode fastNode = doubleNode;
        while (index < doubleNode.length()/2) {
            slowNode=slowNode.next();
            fastNode=fastNode.next().next();
            index++;
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
        //反转node
        SingleNode temp = null;
        SingleNode prev = null;
        while (baseNode != null) {
            temp=baseNode.next();
            baseNode.next(prev);
            prev=baseNode;
            baseNode = temp;
        }
        return prev;
    }



    private static SingleNode createDoubleNode() {
        return new SingleNode(0,
                        new SingleNode(2,
                                        new SingleNode(1,
                                                new SingleNode(0, null))));
    }
}
