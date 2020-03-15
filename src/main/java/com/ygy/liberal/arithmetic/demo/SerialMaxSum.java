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
        datas[0] = 0;
        int maxSum = datas[1], leftMinSum = 0;
        for (int i = 1; i < datas.length; i++) {
            //LeftSum
            datas[i] = datas[i] + datas[i - 1];
            if (datas[i] - leftMinSum > maxSum) {
                maxSum = datas[i] - leftMinSum;
            }
            if (datas[i] < leftMinSum) {
                leftMinSum = datas[i];
            }
        }
        System.out.println(maxSum);
        System.out.println(leftMinSum);
        //num[0] = 0;
        //int ans = num[1], lmin = 0;
        //for(int i = 1; i <= N; i++) {
        //    num[i] += num[i - 1];
        //    if(num[i] - lmin > ans)
        //        ans = num[i] - lmin;
        //    if(num[i] < lmin)
        //        lmin = num[i];
        //}

        //num[0] = 0;
        //int ans = num[1];
        //for(int i = 1; i <= N; i++) {
        //    if(num[i - 1] > 0) num[i] += num[i - 1];
        //    else num[i] += 0;
        //    if(num[i] > ans) ans = num[i];
        //}
        //
        //int N, n, s, ans, m = 0;
        //
        //ans = s = n; //把ans初始化为序列中的的第一个数
        //for(int i = 1; i < N; i++) {
        //    if(s < m) m = s;
        //    scanf("%d", &n);
        //    s += n;
        //    if(s - m > ans)
        //        ans = s - m;
        //}
    }
}
