package com.ygy.liberal.arithmetic.sort.onlogsort;

import com.ygy.liberal.arithmetic.sort.on2sort.Sort;

/**
 * Created by guoyao on 2019/1/24.
 */
public class QuickSort implements Sort {


    @Override
    public int[] sort(int[] datas) {
        quickSort(datas, 0, datas.length-1);
        return datas;
    }

    private void quickSort(int[] datas,int start,int end) {
        if (start >= end) {
            return;
        }
        int part = getPartIndex(datas, start, end);
        quickSort(datas, start, part -1 );
        quickSort(datas, part + 1, end);
    }

    public int getPartIndex(int[] datas, int start, int end) {
        int temp=datas[end];
        int partIndex =start;
        for (int i = start;i < end;i++) {
            if (datas[i] < temp) {
                int value=datas[partIndex];
                datas[partIndex]=datas[i];
                datas[i]=value;
                partIndex++;
            }
        }
        int value=datas[partIndex];
        datas[partIndex]=datas[end];
        datas[end]=value;
        return partIndex;
    }


    @Override
    public int[] reSort(int[] datas) {
        return new int[0];
    }
}
