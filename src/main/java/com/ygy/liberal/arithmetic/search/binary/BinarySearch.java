package com.ygy.liberal.arithmetic.search.binary;

/**
 * Created by guoyao on 2019/1/27.
 */
public class BinarySearch {


    public int searchTheNum(int[] datas, int theNum) {
        int start = 0;
        int end = datas.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (datas[mid] == theNum) {
                return mid;
            } else if (datas[mid] > theNum) {
                end = mid - 1;
            } else if (datas[mid] < theNum) {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int searchTheNumNeedInsert(int[] datas, int theNum) {
        int start = 0;
        int end = datas.length - 1;
        int mid = start + (end - start) / 2;
        while (start <= end) {
            if (datas[mid] == theNum) {
                return mid + 1;
            } else if (datas[mid] > theNum) {
                end = mid - 1;
            } else if (datas[mid] < theNum) {
                start = mid + 1;
            }
            mid = start + (end - start) / 2;
        }
        return start;
    }
}
