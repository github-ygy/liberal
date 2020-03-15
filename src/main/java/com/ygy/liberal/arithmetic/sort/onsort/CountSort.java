package com.ygy.liberal.arithmetic.sort.onsort;

import com.ygy.liberal.arithmetic.sort.Sort;

/**
 * Created by guoyao on 2019/1/25.
 */
public class CountSort implements Sort {

    @Override
    public int[] sort(int[] datas) {
        int maxNum = getMaxNum(datas);

        int[] count = new int[maxNum + 1];
        for (int i = 0; i < maxNum + 1; i++) {
            count[i] = 0;
        }
        //count计数
        for (int i = 0; i < datas.length; i++) {
            count[datas[i]]++;
        }
        //求和数据
        for (int i = 1; i < maxNum + 1; i++) {
            count[i] = count[i - 1] + count[i];
        }
        int[] copyDatas = new int[datas.length];
        for (int i = 0; i < datas.length; i++) {
            copyDatas[count[datas[i]] - 1] = datas[i];
            count[datas[i]]--;
        }
        return copyDatas;
    }

    private int getMaxNum(int[] datas) {
        int max = datas[0];
        for (int i = 1; i < datas.length; i++) {
            if (datas[i] > max) {
                max = datas[i];
            }
        }
        return max;
    }

    @Override
    public int[] reSort(int[] datas) {
        return new int[0];
    }
}
