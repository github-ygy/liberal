package com.ygy.liberal.arithmetic.sort.on2sort;

/**
 * Created by guoyao on 2019/1/21.
 */
public interface Sort {

    /**
     * 正向排序
     * @param datas
     * @return
     */
    int[] sort(int[] datas);

    /**
     * 反向排序
     * @param datas
     * @return
     */
    int[] reSort(int[] datas);
}
