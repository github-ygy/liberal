package com.ygy.liberal.guaua;

import com.google.common.collect.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by guoyao on 2018/11/13.
 */
public class MultiGuaua {

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }



    //multiSet 统计单集合key
    private static void test1() {
        System.out.println("test1: ------------");
        Multiset<String> multiset=HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        for (String str : multiset.elementSet()) {
            System.out.println(str);
            System.out.println(multiset.count(str));
        }
    }

    //multiMap 多key MapList
    private static void test2() {
        System.out.println("test2: ------------");
        HashMultimap<String, Integer> strLongMultiMap=HashMultimap.create();
        strLongMultiMap.put("a", 1);
        strLongMultiMap.put("a", 2);
        strLongMultiMap.put("a", 3);
        strLongMultiMap.put("b", 4);
        strLongMultiMap.put("b", 5);
        System.out.println(strLongMultiMap);
        System.out.println(strLongMultiMap.get("a"));
        Set<Integer> hashSet=strLongMultiMap.get("a");
        System.out.println(hashSet);
    }

    //biMap key value 反转且都必须唯一
    private static void test3() {
        System.out.println("test3：--------------");
        HashBiMap<String, Integer> hashBiMap=HashBiMap.create();
        hashBiMap.put("b", 2);
        hashBiMap.put("c", 3);
        hashBiMap.put("a", 4);
        hashBiMap.put("d", 1);
        System.out.println(hashBiMap);
        System.out.println(hashBiMap.inverse().get(2));
        System.out.println(hashBiMap.inverse().inverse().get("a"));
    }

    //table Map<k,Map<r,v>> 结构数据
    private static void test4() {
        System.out.println("test4:-------------------");
        HashBasedTable<String, String, Integer> strStrInttable=HashBasedTable.create();
        strStrInttable.put("a", "a", 1);
        strStrInttable.put("a", "b", 2);
        strStrInttable.put("a", "c", 3);
        strStrInttable.put("a", "d", 4);
        System.out.println(strStrInttable);
        strStrInttable.put("haha", "c", 1);
        strStrInttable.put("haha", "d", 2);
        strStrInttable.put("haha", "e", 4);
        System.out.println(strStrInttable);
        Map<String, Map<String, Integer>> stringMapMap=strStrInttable.rowMap();
        System.out.println(stringMapMap);
        Set<String> strings=strStrInttable.rowKeySet();
        System.out.println(strings);
        Set<String> strings1=strStrInttable.columnKeySet();
        System.out.println(strings1);
        Map<String, Map<String, Integer>> stringMapMap1=strStrInttable.columnMap();
        System.out.println(stringMapMap1);
    }



}
