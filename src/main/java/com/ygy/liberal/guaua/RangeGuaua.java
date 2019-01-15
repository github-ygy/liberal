package com.ygy.liberal.guaua;

import com.google.common.collect.Range;

/**
 * Created by guoyao on 2018/11/13.
 */
public class RangeGuaua {

    public static void main(String[] args) {
        test1();

    }

    private static void test1() {
        System.out.println(Range.open(0D, 100D).contains(44D));
    }
}
