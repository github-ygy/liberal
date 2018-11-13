package com.ygy.liberal.guaua;

import com.google.common.base.Strings;

/**
 * Created by guoyao on 2018/5/28.
 * 操作string
 */
public class StringsGuaua {

    public static void main(String[] args) {
        test1("1");
        test1("");
        test2("");
        test3(null);
        test4("123");

    }

    //判断是空或者空白
    private static void test1(String input) {
        boolean nullOrEmpty=Strings.isNullOrEmpty(input);
        System.out.println(nullOrEmpty);
    }

    //如果是空字符串返回空
    private static void test2(String input) {
        input=Strings.emptyToNull(input);
        System.out.println(input);
    }

    //如果为空 返回空字符串
    private static void test3(String input) {
        input=Strings.nullToEmpty(input);
        System.out.println(input + "-");
    }

    //补元素
    private static void test4(String input) {
        input=Strings.padStart(input, 10, '0');
        System.out.println(input);

    }


}
