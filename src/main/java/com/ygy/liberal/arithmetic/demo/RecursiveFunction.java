package com.ygy.liberal.arithmetic.demo;

import java.util.ArrayList;

/**
 * 递归函数
 * @author guoyao
 * @date 2020-03-17
 */
public class RecursiveFunction {


    public static void main(String[] args) {
        //一个整数可以被分解为多个整数的乘积，例如，6 可以分解为 2x3。
        // 请使用递归编程的方法，为给定的整数 n，找到所有可能的分解（1 在解中最多只能出现 1 次）。
        // 例如，输入 8，输出是可以是 1x8, 8x1, 2x4, 4x2, 1x2x2x2, 1x2x4, ……
        //find(8, new ArrayList<Integer>());

        //假设有四种面额的钱币，1 元、2 元、5 元和 10 元，而您一共给我 10 元，
        // 那您可以奖赏我 1 张 10 元，或者 10 张 1 元，或者 5 张 1 元外加 1 张 5 元等等。
        // 如果考虑每次奖赏的金额和先后顺序，那么最终一共有多少种不同的奖赏方式呢？”
        find2(0, 10, new ArrayList<Integer>());
        find3(10, new ArrayList<Integer>());

    }

    public static int[] rewards = {1, 2, 5, 10}; // 四种面额的纸币

    private static void find2(int total, int expect, ArrayList<Integer> result) {
        if (total == expect) {
            System.out.println(result);
        } else if (total > expect) {
            //
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Integer> resultList = (ArrayList<Integer>) result.clone();
                resultList.add(rewards[i]);
                find2(total + rewards[i], expect, resultList);
            }
        }

    }


    public static void find3(int totalReward, ArrayList<Integer> result) {

        // 当totalReward = 0时，证明它是满足条件的解，结束嵌套调用，输出解
        if (totalReward == 0) {
            System.out.println(result);
            return;
        }
        // 当totalReward < 0时，证明它不是满足条件的解，不输出
        else if (totalReward < 0) {
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Integer> newResult = (ArrayList<Integer>) (result.clone());  // 由于有4种情况，需要clone当前的解并传入被调用的函数
                newResult.add(rewards[i]);            // 记录当前的选择，解决一点问题
                find3(totalReward - rewards[i], newResult);    // 剩下的问题，留给嵌套调用去解决
            }
        }

    }


    private static void find(int num, ArrayList<Integer> result) {

        if (num == 1) {
            if (!result.contains(1)) {
                result.add(1);
            }
            System.out.println(result);
        } else {
            // 考虑1 ~ num的整数
            for (int i = 1; i <= num; i++) {
                if (i == 1 && result.contains(1)) {
                    continue;
                }
                ArrayList<Integer> resultList = (ArrayList<Integer>) result.clone();
                //判断是否能取模
                if (num % i != 0) {
                    continue;
                }
                resultList.add(i);
                //寻找迭代的情况
                find(num / i, resultList);
            }
        }
    }
}
