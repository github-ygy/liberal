package com.ygy.liberal.arithmetic.sort.onlogsort;

import com.ygy.liberal.arithmetic.sort.on2sort.Sort;
import com.ygy.liberal.arithmetic.sort.on2sort.TestSort;

/**
 * Created by guoyao on 2019/1/23.
 */
public class MergeSort implements Sort {


    @Override
    public int[] sort(int[] datas) {
        return merge(datas, 0, datas.length - 1);
    }

    private int[] merge(int[] datas, int start, int end) {
        if (start >= end) {
            return datas;
        }
        int mid=(start + end + 1) / 2;
        merge(datas, start, mid -1 );
        merge(datas, mid, end);
        mergeDatas(datas, start, end);
        return datas;
    }

    private void mergeDatas(int[] datas, int start, int end) {
        int[] copy=new int[end - start + 1];
        int mid =(start + end + 1) / 2;
        int temp = mid;
        int index = 0;
        int starter=start;
        while (start <= temp - 1 && mid <= end) {
            if (datas[start] < datas[mid]) {
                copy[index++]=datas[start++];
                continue;
            }
            copy[index++]=datas[mid++];
        }
        while (start <= temp -1) {
            copy[index++]=datas[start++];
        }
        while (mid <= end) {
            copy[index++]=datas[mid++];
        }
        for (int i= 0;i<copy.length;i++) {
            datas[starter++]=copy[i];
        }
        copy=null;
    }

    @Override
    public int[] reSort(int[] datas) {
        return new int[0];
    }
}
