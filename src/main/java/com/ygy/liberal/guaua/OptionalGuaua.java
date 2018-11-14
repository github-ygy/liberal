package com.ygy.liberal.guaua;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

/**
 * Created by guoyao on 2018/11/13.
 */
public class OptionalGuaua {

    public static void main(String[] args) {

        test1();

    }

    public static void test1() {
        Optional<String> str=Optional.absent();
        System.out.println(str.isPresent());
        String nullResult="";
        System.out.println(Optional.fromNullable(Strings.emptyToNull(nullResult)).or("default"));

    }
}
