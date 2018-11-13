package com.ygy.liberal.guaua;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Created by guoyao on 2018/5/28.
 * 对象工具
 */
public class ObjectsGuaua {

    public static void main(String[] args) {
        test1();
        test2();
    }

    //拼接字符串
    private static void test1() {
        boolean equal=Objects.equal("test", "test");
        System.out.println(equal);
    }

    //将map转化为字符串
    private static void test2() {
        String test1=MoreObjects.firstNonNull(null, "test");
        System.out.println(test1);
        Long test2=MoreObjects.firstNonNull(null, 2L);
        System.out.println(test2);

    }

}
