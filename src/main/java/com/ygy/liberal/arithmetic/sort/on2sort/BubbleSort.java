package com.ygy.liberal.arithmetic.sort.on2sort;

import com.google.common.collect.Lists;
import com.ygy.liberal.arithmetic.sort.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyao on 2019/1/21.
 */
public class BubbleSort implements Sort {


    @Override
    public int[] sort(int[] datas) {
        if (datas.length <= 1) {
            return datas;
        }

        for (int i = 0; i < datas.length; i++) {
            boolean flag = false;
            for (int j = 0; j < datas.length - i - 1; j++) {
                //取较大值交换
                if (datas[j] > datas[j + 1]) {
                    int temp = datas[j + 1];
                    datas[j + 1] = datas[j];
                    datas[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return datas;
    }

    @Override
    public int[] reSort(int[] datas) {
        if (datas.length <= 1) {
            return datas;
        }
        for (int i = 0; i < datas.length; i++) {
            boolean flag = false;
            for (int j = 0; j < datas.length - i - 1; j++) {
                if (datas[j] < datas[j + 1]) {
                    int temp = datas[j + 1];
                    datas[j + 1] = datas[j];
                    datas[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return datas;
    }


    static int[] values = new int[] { 2, 3, 7 };


    private static List<Integer> getMinCountMoney(int total, ArrayList<Integer> result) {

        if (total == 0) {
            return result;
        }

        if (total < 0) {
            return Lists.newArrayList(1, 1, 1, 1, 1, 1 , 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        }

        List<Integer> minLengthResult = Lists.newArrayList();
        int length = 100;
        for (int value : values) {
            ArrayList clone = (ArrayList<Integer>) result.clone();
            List<Integer> minCountMoney = getMinCountMoney(total - value, clone);
            minCountMoney.add(value);

            if (minCountMoney.size() < length) {
                length = minCountMoney.size();
                minLengthResult = minCountMoney;
            }
        }
        return minLengthResult;

        //ArrayList clone1 = (ArrayList<Integer>)result.clone();
        //List<Integer> minCountMoney1 = getMinCountMoney(total - values[0], clone1);
        //minCountMoney1.add(values[0]);
        //
        //ArrayList clone2 = (ArrayList<Integer>)result.clone();
        //List<Integer> minCountMoney2 = getMinCountMoney(total - values[1], clone2);
        //minCountMoney2.add(values[1]);
        //
        //ArrayList clone3 = (ArrayList<Integer>)result.clone();
        //List<Integer> minCountMoney3 = getMinCountMoney(total - values[2], clone3);
        //minCountMoney3.add(values[2]);
    }

    //private static final int[] coins = {2, 5, 9};
    //
    //private static Map<Integer, Integer> hasCount = new HashMap<>();
    //
    //public static Integer getCount(int total, int[] coins) {
    //    if (hasCount.containsKey(total)) {
    //        return hasCount.get(total);
    //    }
    //    if (total < 0) {
    //        return null;
    //    } else if (total == 0) {
    //        return 0;
    //    } else {
    //        int length = coins.length;
    //        Integer[] min = new Integer[length];
    //        for (int i = 0; i < length; i++) {
    //            Integer tmpResult = getCount(total - coins[i], coins);
    //            min[i] = tmpResult != null ? (tmpResult + 1) : null;
    //        }
    //        List<Integer> resultList = Arrays.stream(min).filter(e -> e != null).collect(Collectors.toList());
    //        Integer result = resultList.size() > 0 ? resultList.stream().mapToInt(e -> e).min().getAsInt() : null;
    //        hasCount.put(total, result);
    //        return result;
    //    }
    //}

    public static void main(String[] args) {
    }
}
