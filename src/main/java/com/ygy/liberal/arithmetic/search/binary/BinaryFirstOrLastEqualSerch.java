package com.ygy.liberal.arithmetic.search.binary;

/**
 * Created by guoyao on 2019/1/27.
 */
public class BinaryFirstOrLastEqualSerch {


    public int BinaryFirstEqualSerch(int[] datas, int theNum) {
        int start = 0;
        int end = datas.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (datas[mid] == theNum) {
                while (--mid >= 0) {
                    if (datas[mid] != theNum) {
                        mid = mid + 1;
                        break;
                    }
                }
                return mid;
            } else if (datas[mid] > theNum) {
                end = mid - 1;
            } else if (datas[mid] < theNum) {
                start = mid + 1;
            }
        }
        return -1;
    }

    public int BinaryLastEqualSerch(int[] datas, int theNum) {
        int start = 0;
        int end = datas.length - 1;
        while (start <= end) {
            int mid = start + ((end - start) >> 1);
            if (datas[mid] == theNum) {
                while (++mid <= end) {
                    if (datas[mid] != theNum) {
                        mid = mid - 1;
                        break;
                    }
                }
                return mid;
            } else if (datas[mid] > theNum) {
                end = mid - 1;
            } else if (datas[mid] < theNum) {
                start = mid + 1;
            }
        }
        return -1;
    }
}
