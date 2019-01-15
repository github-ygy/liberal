package com.ygy.liberal.arithmetic;

/**
 * Created by guoyao on 2019/1/15.
 */
public class SingleNode {

    private int value ;

    private SingleNode next;

    public SingleNode(int value, SingleNode next) {
        this.value=value;
        this.next=next;
    }

    public SingleNode next() {
        return this.next;
    }

    public void next(SingleNode singleNode) {
        this.next=singleNode;
    }


    public int value() {
        return this.value;
    }

    public int length() {
        int count = 1;
        SingleNode temp = this ;
        while (temp != null && temp.next != null) {
            count++;
            temp=temp.next;
        }
        return count;
    }
}
