package com.ygy.liberal.arithmetic.sort.on2sort;

/**
 * Created by guoyao on 2019/1/21.
 */
public class TestSort {


    public static void main(String[] args) {
        int[] datas={1, 5, 4, 3, 6, 2};
        print(new BubbleSort().sort(datas));
        print(new InsertSort().sort(datas));
        print(new InsertSort().reSort(datas));
    }

    public static void print(int[] datas) {
        if (datas.length <= 0) {
            return;
        }

        for (int data : datas) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
}
