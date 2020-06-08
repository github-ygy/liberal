package com.ygy.liberal.arithmetic.structure.heap;

/**
 * Created by guoyao on 2019/2/12.
 */
public class Heap {

    private int[] datas;

    private int size;

    public Heap(int length) {
        this.size = 0;
        datas = new int[length];
    }

    public Heap(int[] datas) {
        if (datas != null && datas.length > 0) {
            this.size = datas.length - 1;
            this.datas = datas;
            buildHeap();
        } else {
            this.size = 0;
            this.datas = new int[0];
        }
    }


    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(10);
        heap.insert(30);
        heap.insert(20);
        heap.insert(40);
        heap.insert(25);
        heap.insert(28);
        heap.insert(27);
        heap.insert(19);
        heap.insert(24);
        heap.print();
        heap.delete();
        heap.print();
        Heap heap2 = new Heap(new int[]{0, 1, 2, 54, 56, 23, 13, 43, 48});
        heap2.print();
    }

    /**
     * 0 40 30 28 24 25 20 27 10 19
     * 0 30 25 28 24 19 20 27 10 40
     * 0 54 2 12 56 23 12 43
     *
     * @param data
     */

    private void insert(int data) {
        datas[++size] = data;
        if (size > 1) {
            heapUp(size, data);
        }
    }

    private void delete() {
        if (size == 1) {
            datas[size] = 0;
            return;
        }
        if (size > 1) {
            int data = datas[size];
            int delete = datas[1];
            datas[1] = data;
            heapDown(1, data);
            datas[size] = delete;
            size--;
        }
    }


    private void heapDown(int index, int data) {
        while (index << 1 <= size) {
            //左节点
            int sonIndex = index << 1;
            int son = datas[sonIndex];
            //如果右节点比左节点要大,则需要比较的是有节点
            if (sonIndex + 1 <= size && datas[sonIndex + 1] > son) {
                //获取有节点的信息
                son = datas[sonIndex + 1];
                sonIndex = sonIndex + 1;
            }
            //比较父节点和子节点的数据
            if (data > son) {
                break;
            }
            //如果需要替换,则将父节点数据和子节点替换,同时将新的子节点和下面继续比较
            datas[index] = son;
            index = sonIndex;
        }
        //赋值数据
        datas[index] = data;

    }


    private void heapUp(int index, int data) {
        while (index > 1) {
            int parant = index >> 1;
            if (datas[parant] > data) {
                break;
            }
            datas[index] = datas[parant];
            datas[parant] = data;
            index = parant;
        }
    }

    /**
     *
     *      50
     *  78      62
     *
      54 67   74  61

     *      50
     *  78      74
     *
     54 67   62  61

     *      50
     *  78      74
     *
     54 67   62  61

     *      78
     *  50      74
     *
     54 67   62  61
     *
     */
    private void buildHeap() {
        //构建堆
        for (int i = size >> 1; i >= 1; i--) {

            heapDown(i, datas[i]);
        }
    }

    public void print() {
        if (datas.length <= 0) {
            return;
        }

        for (int data : datas) {
            System.out.print(data + " ");
        }
        System.out.println();
    }
}
