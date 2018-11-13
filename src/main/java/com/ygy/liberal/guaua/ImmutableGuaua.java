package com.ygy.liberal.guaua;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * Created by guoyao on 2018/5/28.
 * 不可变的
 */
public class ImmutableGuaua {

    public static void main(String[] args) {
        //test1();
        test2();

    }

    //不可变集合不能添加元素，只能初始化
    private static void test1() {
        ImmutableSet<String> of=ImmutableSet.copyOf(Sets.newHashSet("a", "b", "c"));
        of.add("ss");
    }

    private static void test2() {
        ImmutableList.Builder<String> builder=ImmutableList.builder();
        builder.add("str1");
        builder.add("str2");
        builder.add("str3");
        ImmutableList<String> list=builder.build();
        System.out.println(list);
    }
}
