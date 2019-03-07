package com.ygy.liberal.arithmetic.demo;

import java.util.*;

/**
 * Created by guoyao on 2019/2/21.
 */
public class Hongbao {

    public static void main(String[] args) {
        sum(allocate2(100L, 100));
    }

    private static void sum(Long [] datas) {
        Long sum = 0L;
        for (Long data : datas) {
            sum+=data;
            if (data < 1) {
                System.out.println(" allocate wrong" + data);
            }
            System.out.println(data);
        }
        System.out.println("sum:" + sum);
    }

    private static Long[] allocate(Long amount, int nunmber) {
        if (amount  < nunmber) {
            throw new IllegalArgumentException("分钱红包人数大于了红包金额");
        }
        Long extraAllocateAmount = amount-nunmber;
        Long [] amounts = new Long[nunmber];
        Long alreadyAllocateAmount = 0L;

        for (int i= 0;i< nunmber;i++) {
            Long randomAmount=randomAmount(extraAllocateAmount - alreadyAllocateAmount, nunmber - i );
            amounts[i]=1+randomAmount;
            alreadyAllocateAmount+=randomAmount;
        }

        return amounts;
    }

    private static Long[] allocate2(Long amount, int nunmber) {
        if (amount  < nunmber) {
            throw new IllegalArgumentException("分钱红包人数大于了红包金额");
        }
        Long extraAllocateAmount = amount-nunmber;
        Long [] amounts = new Long[nunmber];
        Long alreadyAllocateAmount=0L;
        //构建红包数的红包桶，预留每个桶有1分
        for (int i= 0;i< nunmber;i++) {
            amounts[i] = 1L;
        }
        Random random=new Random();
        for (int i= 0;i< nunmber*10;i++) {
            //倍值均分获取公平的红包值
            Long randomAmount=randomAmount(extraAllocateAmount - alreadyAllocateAmount, nunmber*10 - i);
            alreadyAllocateAmount+=randomAmount;
            //将获取的公平红包值随机的加到每个红包桶上(理论上每个桶的最大值为amount-nunmber分 最小值为1分)
            amounts[random.nextInt(nunmber)]+=randomAmount;
        }
        return amounts;
    }


    private static Long randomAmount(Long remainMoney, int remainNum) {
        if (remainNum == 1 ||  remainMoney <= 1) {
            return remainMoney;
        }
        int data=new Random().nextInt(100);
        return (remainMoney * 2 * data)/(100 * remainNum) ;
    }

    public static int[] allocate3(int amount, int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("没人分钱");
        }
        if (amount < number) {
            throw new IllegalArgumentException("钱不够分");
        }

        // 一个人直接分完所有钱
        if (1 == number) {
            return new int[] {amount};
        }

        int[] result = new int[number];

        // 将分钱理解为number - 1个隔板插入amount - number + 1个钱币间隙中
        // （因为每个人至少分1分钱所以先刨去number分钱用来分给每个人，剩下的amount - number具有连同头尾的amount - number + 1个间隙）
        amount -= number;

        Random random = new Random();
        // 间隙索引数组
        int[] gapIndex = new int[number - 1];
        for (int i = 0; i < gapIndex.length; i++) {
            // 随机选一个间隙，可重复
            gapIndex[i] = random.nextInt(amount + 1);
        }
        // 对间隙索引数组进行排序
        Arrays.sort(gapIndex);

        // 第一个分到的钱为：第一个间隙前的数量 + 默认分配的1分
        result[0] = gapIndex[0] + 1;

        // 中间人分到的钱为：两个间隙之间的数量 + 默认分配的1分
        for (int i = 0; i < number - 2; i++) {
            result[i + 1] = gapIndex[i + 1] - gapIndex[i] + 1;
        }
        // 最后一个分到的钱为：最后一个间隙后的数量 + 默认分配的1分
        result[number - 1] = amount - gapIndex[number -2] + 1;
        return result;
    }
}
