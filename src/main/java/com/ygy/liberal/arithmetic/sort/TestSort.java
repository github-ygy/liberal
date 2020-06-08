package com.ygy.liberal.arithmetic.sort;

import com.ygy.liberal.arithmetic.demo.FindTopN;
import com.ygy.liberal.arithmetic.sort.on2sort.BubbleSort;
import com.ygy.liberal.arithmetic.sort.on2sort.InsertSort;
import com.ygy.liberal.arithmetic.sort.on2sort.SelectSort;
import com.ygy.liberal.arithmetic.sort.onlogsort.MergeSort;
import com.ygy.liberal.arithmetic.sort.onlogsort.QuickSort;
import com.ygy.liberal.arithmetic.sort.onsort.CountSort;

/**
 * Created by guoyao on 2019/1/21.
 */
public class TestSort {


    public static void main(String[] args) {
        print(new BubbleSort().sort(createDatas()));
        print(new BubbleSort().reSort(createDatas()));
        print(new InsertSort().sort(createDatas()));
        print(new InsertSort().reSort(createDatas()));
        print(new SelectSort().sort(createDatas()));
        print(new SelectSort().reSort(createDatas()));
        print(new MergeSort().sort(createDatas()));
        print(new QuickSort().sort(createDatas()));
        print(new CountSort().sort(createDatas()));

        //find top n
        System.out.println(new FindTopN().findTopN(createDatas(), 1));
    }

    private static int[] createDatas() {
        return new int[]{1, 5, 4, 3, 6, 2};
        //return new int[]{1, 2, 3, 4, 5, 6};
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
