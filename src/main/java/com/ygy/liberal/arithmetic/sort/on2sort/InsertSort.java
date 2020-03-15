package com.ygy.liberal.arithmetic.sort.on2sort;

import com.ygy.liberal.arithmetic.sort.Sort;

/**
 * Created by guoyao on 2019/1/21.
 * 插入排序
 */
public class InsertSort implements Sort {


    @Override
    public int[] sort(int[] datas) {
        if (datas.length <= 1) {
            return datas;
        }
        //寻找有序数据位置进行前方插入
        for (int i = 1; i < datas.length; i++) {
            int temp = datas[i];
            int j = i - 1;
            while (j >= 0) {
                if (datas[j] > temp) {
                    datas[j + 1] = datas[j];
                    j--;
                    continue;
                }
                break;
            }
            datas[j + 1] = temp;
        }
        return datas;
    }

    @Override
    public int[] reSort(int[] datas) {
        if (datas.length <= 1) {
            return datas;
        }
        for (int i = 1; i < datas.length; i++) {
            int temp = datas[i];
            int j = i - 1;
            while (j >= 0) {
                if (datas[j] < temp) {
                    datas[j + 1] = datas[j];
                    j--;
                    continue;
                }
                break;
            }
            datas[j + 1] = temp;
        }
        return datas;
    }
}
