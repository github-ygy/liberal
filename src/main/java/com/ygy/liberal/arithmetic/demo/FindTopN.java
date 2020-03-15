package com.ygy.liberal.arithmetic.demo;

import com.ygy.liberal.arithmetic.sort.onlogsort.QuickSort;

/**
 * Created by guoyao on 2019/1/22.
 */
public class FindTopN {


    /**
     * 查找topN
     *
     * @param datas
     * @param n
     * @return
     */
    public int findTopN(int[] datas, int n) {

        QuickSort quickSort = new QuickSort();
        int start = 0;
        int end = datas.length - 1;
        int need = datas.length - n;
        int partIndex = quickSort.getPartIndex(datas, start, end);
        while (partIndex != need) {
            if (partIndex > need) {
                end = partIndex;
                partIndex = quickSort.getPartIndex(datas, start, end);
            } else {
                start = partIndex;
                partIndex = quickSort.getPartIndex(datas, start, end);
            }
        }
        return datas[need];
    }

}
