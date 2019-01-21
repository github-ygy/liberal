package com.ygy.liberal.arithmetic.stack;

import com.ygy.liberal.arithmetic.SingleNode;

/**
 * Created by guoyao on 2019/1/21.
 */
public class LinkedStack implements IStack {


    private SingleNode head;

    private int count ;

    private int length;

    public LinkedStack (int count){
        this.count=count;
        length = 0;
    }

    @Override
    public boolean push(int data) {
        if (length == count) {
            return false;
        }
        if (head == null) {
            head=new SingleNode(data, null);
        } else {
            head=new SingleNode(data, head);
        }
        length++;
        return true;
    }

    @Override
    public int pop() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        SingleNode temp=head;
        head=head.next();
        return temp.value();
    }
}
