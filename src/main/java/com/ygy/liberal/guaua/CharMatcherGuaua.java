package com.ygy.liberal.guaua;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import org.springframework.util.StringUtils;

/**
 * Created by guoyao on 2018/5/28.
 * 空白匹配
 */
public final class CharMatcherGuaua {




    public static void main(String[] args) {
        //test1();
        test2();
    }


    //函数式
    private static void test1() {
        Function<String,Integer> function =  new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                Preconditions.checkArgument(!StringUtils.isEmpty(input), "input不能为空");
                return input.length();
            }
        };
        Integer length=function.apply("abc");
        System.out.println(length);
    }

    public static void test2() {
        System.out.println(CharMatcher.JAVA_DIGIT.retainFrom("abc "));
    }

}

