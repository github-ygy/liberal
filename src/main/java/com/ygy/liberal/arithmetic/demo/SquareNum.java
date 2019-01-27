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

    public double square(double data) {
        double max=data;
        double min=0D;
        if (data < 1) {
            max=1;
        } else {
            min=1D;
        }
        double mid=min + (max - min) / 2;
        while (Math.abs(mid * mid - data) > 0.000001) {
            if (mid * mid > data) {
                max=mid;
            }else {
                min=mid;
            }
            mid=min + (max - min) / 2;
        }
        return mid;
    }
}
