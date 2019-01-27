package com.ygy.liberal.arithmetic.search;

import com.ygy.liberal.arithmetic.search.binary.BinarySearch;

/**
 * Created by guoyao on 2019/1/27.
 */
public class TestSearch {

    public static void main(String[] args) {
        System.out.println(new BinarySearch().searchTheNum(createDatas(), -1));
        System.out.println(new BinarySearch().searchTheNumNeedInsert(createDatas(), 0));
    }

    private static int[] createDatas() {
        return new int[]{1};
    }

}
