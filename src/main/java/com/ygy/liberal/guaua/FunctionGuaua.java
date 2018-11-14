package com.ygy.liberal.guaua;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import org.springframework.util.StringUtils;

/**
 * Created by guoyao on 2018/5/30.
 */
public final class FunctionGuaua {

    public static Function<String, Long> string2Long=TransferString2LongFunction.INSTANCE;


    public static void main(String[] args) {
        test1();
        test2();
    }

    //函数式
    private static void test1() {
        Function<String, Integer> function=new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                Preconditions.checkArgument(!StringUtils.isEmpty(input), "input不能为空");
                return input.length();
            }
        };
        Integer length=function.apply("haha");
        System.out.println(length);
    }

    private static void test2() {
        System.out.println(getTransferString2LongFunction().apply("123"));
        System.out.println(string2Long.apply("123"));
    }

    private static class TransferString2LongFunction implements Function<String, Long> {
        static final TransferString2LongFunction INSTANCE=new TransferString2LongFunction();

        @Override
        public Long apply(String input) {
            try {
                return Long.valueOf(input);
            } catch (Exception e) {
                return 0L;
            }
        }
    }

    public static Function<String, Long> getTransferString2LongFunction() {
        return TransferString2LongFunction.INSTANCE;
    }
}
