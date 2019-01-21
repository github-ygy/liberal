package com.ygy.liberal.arithmetic.stack;

import com.google.common.base.Preconditions;

/**
 * Created by guoyao on 2019/1/21.
 */
public class TestIStack {

    public static void main(String[] args) {
        testPush();
    }

    public static IStack createArrayStack(int count) {
        return new ArrayStack(count);
    }

    public static void testPop() {
        IStack stack=createArrayStack(4);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        Preconditions.checkArgument(stack.pop() == 4);
        Preconditions.checkArgument(stack.pop() == 3);
        Preconditions.checkArgument(stack.pop() == 2);
        Preconditions.checkArgument(stack.pop() == 1);
        Preconditions.checkArgument(stack.pop() == Integer.MIN_VALUE);

    }

    public static void testPush() {
        IStack stack=createArrayStack(4);
        Preconditions.checkArgument(stack.push(4));
        Preconditions.checkArgument(stack.push(4));
        Preconditions.checkArgument(stack.push(4));
        Preconditions.checkArgument(stack.push(4));
        Preconditions.checkArgument(!stack.push(4));
    }
}
