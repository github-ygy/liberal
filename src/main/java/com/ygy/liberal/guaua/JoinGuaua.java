package com.ygy.liberal.guaua;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by guoyao on 2018/5/28.
 * 字符串连接器
 */
public class JoinGuaua {

    public static void main(String[] args) {
        //以-为分割同时过滤null元素
        //Joiner joiner=Joiner.on("-").skipNulls();
        //null元素展示为空、
        Joiner joiner=Joiner.on("-").useForNull("nullKey");
        //test1(joiner);
        //test2(joiner);
        test3();
    }

    //拼接字符串
    private static void test1(Joiner joiner) {
        StringBuilder builder=joiner.appendTo(new StringBuilder(), "a", null, "b");
        System.out.println(builder);
    }

    //将map转化为字符串
    private static void test2(Joiner joiner) {
        Map<String, String> map=Maps.newHashMap();
        map.put("a", "abc");
        map.put("b", "bc");
        map.put(null, "cbc");
        String mapString=joiner.withKeyValueSeparator(":").join(map);
        System.out.println(mapString);
    }

    private static void test3() {
        ArrayList<String> strings=Lists.newArrayList("a", "b", "c");
        String join=Joiner.on(";").join(strings);
        System.out.println(join);
    }
}
