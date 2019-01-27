package com.ygy.liberal.arithmetic.search;

import com.ygy.liberal.arithmetic.search.binary.BinaryFirstOrLastEqualSerch;

/**
 * Created by guoyao on 2019/1/27.
 */
public class TestSearch {

    public static void main(String[] args) {
        //System.out.println(new BinarySearch().searchTheNum(createDatas(), -1));
        //System.out.println(new BinarySearch().searchTheNumNeedInsert(createDatas(), 0));
        //System.out.println(new BinaryFirstOrLastEqualSerch().BinaryFirstEqualSerch(createDatas(), 7));
        //System.out.println(new BinaryFirstOrLastEqualSerch().BinaryFirstEqualSerch(createDatas1(), 7));
        //System.out.println(new BinaryFirstOrLastEqualSerch().BinaryFirstEqualSerch(createDatas2(), 7));
        //System.out.println(new BinaryFirstOrLastEqualSerch().BinaryFirstEqualSerch(createDatas3(), 7));
        //System.out.println(new BinaryFirstOrLastEqualSerch().BinaryFirstEqualSerch(createDatas4(), 7));
        System.out.println(new BinaryFirstOrLastEqualSerch().BinaryLastEqualSerch(createDatas(), 7));
        System.out.println(new BinaryFirstOrLastEqualSerch().BinaryLastEqualSerch(createDatas1(), 7));
        System.out.println(new BinaryFirstOrLastEqualSerch().BinaryLastEqualSerch(createDatas2(), 7));
        System.out.println(new BinaryFirstOrLastEqualSerch().BinaryLastEqualSerch(createDatas3(), 7));
        System.out.println(new BinaryFirstOrLastEqualSerch().BinaryLastEqualSerch(createDatas4(), 7));
    }

    private static int[] createDatas() {
        return new int[]{1,2,7,7,7,88};
    }

    private static int[] createDatas1() {
        return new int[]{1,2,3,7,7,7,88};
    }

    private static int[] createDatas2() {
        return new int[]{1,2,3,4,7,7,7,88};
    }

    private static int[] createDatas3() {
        return new int[]{1,2,3,4,5,7,7,7,88};
    }

    private static int[] createDatas4() {
        return new int[]{1,2,3,4,5,6,7,7,7,88};
    }

}
