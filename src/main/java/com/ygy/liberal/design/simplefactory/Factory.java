package com.ygy.liberal.design.simplefactory;

/**
 * Created by guoyao on 2018/5/27.
 */
public class Factory {

    public static  Listener createInstance() {
        return new ContextListener();
    }

}
