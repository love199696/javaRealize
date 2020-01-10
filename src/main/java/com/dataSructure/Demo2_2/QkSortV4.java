package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:50
 */
public class QkSortV4 extends Sort {
    //重排序
    public  void changePosition(Comparable[] arr) {
        int len = arr.length;
        Random random = new Random();
        for(int index=0; index<len; index++) {
            exch(arr,index,random.nextInt(len) );
        }
    }


    public static void main(String[] arge) {
        QkSortV4 v3 = new QkSortV4();
        Comparable[] arr1 = new Comparable[10];
        Comparable[] arr2 = new Comparable[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }
        v3.changePosition(arr1);
        v3.changePosition(arr2);
        v3.sort(arr1,arr2);
        v3.show(arr1);
        v3.show(arr2);
    }

    public Comparable[] sort(Comparable[] arr1,Comparable[] arr2) {

        return sort(arr1, arr2, 0, arr1.length - 1);
    }
    @Override
    public Comparable[] sort(Comparable[] arr) {

        return sort(arr, arr, 0, arr.length - 1);
    }


    private Comparable[] sort(Comparable[] arr1, Comparable[] arr2, int low, int high) {
        if (high <= low) {
            return arr1;
        }
        int index1 = getSegmentationPoints(arr1, arr2[low], low, high);
        int index2 = getSegmentationPoints(arr2, arr1[index1], low, high);
        sort(arr1, arr2, low, index1 - 1);
        sort(arr1, arr2, index1 + 1, high);
        return arr1;
    }


    /**
     * 维护三个指针。low-lt的数据小于datum      gt-high大于v  lt-index =v   index -gt的还未确认
     * 递归处理
     *
     * @Author： phm
     * @Date： 2019/11/7 20:10
     * * @return : null
     */
    private int getSegmentationPoints(Comparable[] arr, Comparable datum, int low, int high) {
        int lt = low;
        int gt = high;
        int index = low;

        while (index <= gt) {
            if (arr[index].compareTo(datum) < 0) {
                exch(arr, index++, lt++);
            } else if (arr[index].compareTo(datum) > 0) {
                exch(arr, index, gt--);
            } else {
                index++;
            }
        }
        return lt;
    }


}
