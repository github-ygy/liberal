package com.ygy.liberal.guaua;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.util.Assert;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by guoyao on 2018/11/14.
 */
public class CacheGuaua {

    public static void main(String[] args) throws  Exception {
        testCacheLoad_loadNull();
        testCache();
    }

    private static void testCacheLoad_loadNull() throws Exception {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return getLoadkey(key);
            }

            private String getLoadkey(String key) {
                if (key.equals("null")) {
                    return null;
                }
                return key + "_value";
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(3).build(loader);

        Assert.isTrue(loadingCache.size() == 0);
        Assert.isTrue(loadingCache.get("test").equals("test_value"));
        Assert.isTrue(loadingCache.getUnchecked("test1").equals("test1_value"));
        loadingCache.put("test2", "test2");
        Assert.isTrue(loadingCache.getUnchecked("test2").equals("test2"));
        Assert.isTrue(loadingCache.size() == 3);
        boolean isException = false;
        try {
            String test3 = loadingCache.get("null");
        } catch (Exception ex) {
            isException = true;
        }
        Assert.isTrue(isException);
        Assert.isTrue(loadingCache.getUnchecked("test3").equals("test3_value"));
        Assert.isTrue(loadingCache.size() == 3);
        ConcurrentMap<String, String> stringStringConcurrentMap = loadingCache.asMap();
        System.out.println(stringStringConcurrentMap.keySet());
    }


    private static void testCache() throws Exception {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build();
        cache.put("test1", "value1");
        Assert.isTrue(cache.getIfPresent("test1").equals("value1"));
        Assert.isTrue(cache.getIfPresent("test2") == null);
    }
}
