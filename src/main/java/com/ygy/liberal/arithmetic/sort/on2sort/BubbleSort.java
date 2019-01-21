package com.ygy.liberal.arithmetic.sort.on2sort;

/**
 * Created by guoyao on 2019/1/21.
 */
public class BubbleSort implements Sort{


    @Override
    public int[] sort(int[] datas) {
        if (datas.length <= 1) {
            return datas;
        }
        boolean flag=false;
        for (int i=0; i < datas.length; i++) {
            for (int j = 0; j< datas.length -i -1;j++) {
                //取较大值交换
                if (datas[j] > datas[j + 1]) {
                    int temp=datas[j + 1];
                    datas[j + 1]=datas[j];
                    datas[j]=temp;
                    flag=true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return datas;
    }
}
