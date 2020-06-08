package com.ygy.liberal.arithmetic.demo;

/**
 * Created by guoyao on 2019/2/16.
 * 最大连续的和
 */
public class SerialMaxSum {
    static int[] datas = {0, 5, -5, 4, -5, 4, -7, 6, -1, 4, 3, 2};

    public static void main(String[] args) {
        getSeialMaxSum(datas);
    }

    private static void getSeialMaxSum(int[] datas) {
        int i;
        int maxSum = 0;
        int curSum = 0;
        for (i = 0; i < datas.length; i++) {
            curSum += datas[i];
            if (curSum > maxSum) {
                maxSum = curSum;
            }
            //如果累加和出现小于0的情况，
            //则和最大的子序列肯定不可能包含前面的元素，
            //这时将累加和置0，从下个元素重新开始累加
            if (curSum < 0) {
                curSum = 0;
            }
        }
        System.out.println(maxSum);
    }
}
