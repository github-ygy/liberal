package com.ygy.liberal.arithmetic.sort.on2sort;

import com.ygy.liberal.arithmetic.sort.Sort;

/**
 * Created by guoyao on 2019/1/21.
 */
public class SelectSort implements Sort {


    @Override
    public int[] sort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return datas;
        }
        //找最小值换数据
        for (int i = 0; i < datas.length - 1; i++) {
            int minIndex = i;
            int minData = datas[i];
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] < minData) {
                    minIndex = j;
                    minData = datas[j];
                }
            }
            datas[minIndex] = datas[i];
            datas[i] = minData;
        }

        //找到小值就换
        for (int i = 0; i < datas.length - 1; i++) {
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] < datas[i]) {
                    int temp = datas[j];
                    datas[j] = datas[i];
                    datas[i] = temp;
                }
            }
        }
        return datas;
    }

    @Override
    public int[] reSort(int[] datas) {
        if (datas == null || datas.length <= 1) {
            return datas;
        }
        //找最大值换数据
        for (int i = 0; i < datas.length - 1; i++) {
            int minIndex = i;
            int minData = datas[i];
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] > minData) {
                    minIndex = j;
                    minData = datas[j];
                }
            }
            datas[minIndex] = datas[i];
            datas[i] = minData;
        }

        //找到大值就换
        for (int i = 0; i < datas.length - 1; i++) {
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[j] > datas[i]) {
                    int temp = datas[j];
                    datas[j] = datas[i];
                    datas[i] = temp;
                }
            }
        }
        return datas;
    }
}
