package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:50
 */
public class QkSortV3 extends Sort {
    public static void main(String[] arge){
        QkSortV3 v3 = new QkSortV3();
        Comparable[] arr= new Comparable[100];
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = StdRandom.uniform(1,3);
        }
        v3.show(arr);

        v3.sort(arr);
        v3.show(arr);
        System.out.println(v3.checkSort(arr));







    }

    @Override
    public Comparable[] sort(Comparable[] arr) {

        return sort(arr, 0, arr.length-1);
    }


    private Comparable[] sort(Comparable[] arr, int low, int high) {
        if (high <= low) {
            return arr;
        }
        int lt=low;int gt=high;int index =low;
        Comparable datum = arr[low];
        while (index<=gt){
            if(arr[index].compareTo(datum)<=0){
                datum =arr[index];
                exch(arr,index++,lt++);
            }else {
                index++;
            }
        }
        return arr;
    }


    /**
     * 维护三个指针。low-lt的数据小于datum      gt-high大于v  lt-index =v   index -gt的还未确认
     * 递归处理
     * @Author： phm
     * @Date： 2019/11/7 20:10
     *  * @return : null
     */
    private int getSegmentationPoints(Comparable[] arr, int low, int high) {
        int lt=low;int gt=high;int index =low;
        Comparable datum = arr[low];
        while (index<=gt){
            if(arr[index].compareTo(datum)<0){
                exch(arr,index++,lt++);
            }else if(arr[index].compareTo(datum)>0){
                exch(arr,index,gt--);
            }else {
                index++;
            }
        }
        return lt ;
    }


}
