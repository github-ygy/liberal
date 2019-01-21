package com.ygy.liberal.arithmetic.stack;

/**
 * Created by guoyao on 2019/1/21.
 */
public class ArrayStack implements IStack {
    private final int DEFAULT_SIZE = 10;

    private int[] datas = null;

    private int index ;

    private int count;

    public ArrayStack(int count) {
        if (count <= 0) {
            count=DEFAULT_SIZE;
        }
        this.datas=new int[count];
        this.index = 0;
        this.count=count;
    }

    @Override
    public boolean push(int data) {
        if (index == count) {
            return false;
        }
        datas[index++]=data;
        return true;
    }

    @Override
    public int pop() {
        if(index ==0) return Integer.MIN_VALUE;
        int data=datas[index - 1];
        index--;
        return data;
    }



}
