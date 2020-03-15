package com.ygy.liberal.arithmetic.demo;

/**
 * Created by guoyao on 2019/1/27.
 * 开平方根
 */
public class SquareNum {


    public static void main(String[] args) {
        System.out.println(new SquareNum().square(0.09D));
        System.out.println(new SquareNum().square(2D));
    }

    private double square(double data) {
        double max = data;
        double min = 0D;
        if (data < 1) {
            max = 1;
        } else {
            min = 1D;
        }
        double mid = min + (max - min) / 2;
        while (Math.abs(mid * mid - data) > 0.000001) {
            if (mid * mid > data) {
                max = mid;
            } else {
                min = mid;
            }
            mid = min + (max - min) / 2;
        }
        return mid;
    }

    private int findXPosition(int[] arr, int x) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + end / 2;
            if (arr[mid] > x) {
                end = mid - 1;
            }
            if (arr[mid] < x) {
                start = mid + 1;
            }
            if (arr[mid] == x) {
                return mid;
            }
        }
        return -1;
    }
}
