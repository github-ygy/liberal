package com.ygy.liberal.guaua;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Callable;

/**
 * Created by guoyao on 2018/11/14.
 */
public class CacheGuaua {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        CacheLoader<String, String> cacheLoader=new CacheLoader<String,String>(){
            @Override
            public String load(String key) {
                return key + "=>cache";
            }
        };

        LoadingCache<String, String> load=
                CacheBuilder.newBuilder().maximumSize(10).build(cacheLoader);

        Cache<String, String> cache = CacheBuilder.newBuilder().build();

        load.put("test1", "value1");

        System.out.println(load.getUnchecked("test1"));
        System.out.println(load.getUnchecked("test2"));
        System.out.println(cache.getIfPresent("test2"));
        try {
            System.out.println("---" + cache.get("test1", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "";
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
