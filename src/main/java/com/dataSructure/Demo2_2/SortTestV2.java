package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-13 20:30
 */
public class SortTestV2 {
    public static void main(String[] arge) throws Exception {

        Class[] names = {HeapSortV4.class,HeapSort.class,HeapSortV2.class};


        sortTest(100000000,names);



    }
    public static void sortTest(int N,Class[] names) throws Exception {

        int len =10;

        while (len <N){
            for (Class name : names) {
                Sort sort = (Sort) name.getConstructor().newInstance();
                Comparable[] comparables = builderComparable(len);
                double v = sortTest(comparables, sort);
                System.out.println(sort.getClass().getName()+"len="+len+"耗时"+v);
            }
            System.out.println("------------------------------------------------------------------------------------------------");
            len *=2;
        }
    }
    private static Comparable[] builderComparable(int len){
        Comparable[] comparables = new Comparable[len];
        for (int i = 0; i <len ; i++) {
            comparables[i]= StdRandom.uniform(1000000);
        }



        return comparables;
    }



    public static double sortTest(Comparable[] arr, Sort sort) {
        Stopwatch timer=new Stopwatch();
        sort.sort(arr);
        double v = timer.elapsedTime();
        isSorted(arr);
        return v;
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i=1;i<a.length;i++)
            if(less(a[i],a[i-1])) throw  new RuntimeException("乱序");
        return true;
    }
    /**
     *如果q小于p 那么返回true
     **/
    protected static boolean less(Comparable q,Comparable p){
        return q.compareTo(p)<0;
    }
}
