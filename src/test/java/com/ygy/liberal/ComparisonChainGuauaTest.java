package com.ygy.liberal;

import com.google.common.collect.ComparisonChain;
import org.junit.Test;

/**
 * Created by guoyao on 2018/5/29.
 */
public class ComparisonChainGuauaTest {

    @Test
    public void testCompare() {
        System.out.println(ComparisonChain.start().compare(1, 2).result());
    }
}
