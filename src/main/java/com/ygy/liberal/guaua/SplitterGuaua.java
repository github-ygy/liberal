package com.ygy.liberal.guaua;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.Map;

/**
 * Created by guoyao on 2018/5/28.
 * 字符串分割器
 */
public class SplitterGuaua {

    public static void main(String[] args) {
        Splitter splitter=Splitter.on(",").trimResults();
        test1(splitter);
        test2(splitter);
        //test3(Splitter.on("#").trimResults());
    }

    //分割字符串
    private static void test1(Splitter splitter) {
        List<String> strings=splitter.splitToList("a,b,   ");
        System.out.println(strings);
    }

    //将map转化为字符串
    private static void test2(Splitter splitter) {
        List<String> strings=splitter.omitEmptyStrings().splitToList("a,b,   , ");
        System.out.println(strings);
    }

    //分割后转化成map
    private static void test3(Splitter splitter) {
        Map<String, String> split=splitter.withKeyValueSeparator(",").split("a,b#c,d#    ,e");
        System.out.println(split);

    }
}
