package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import lombok.extern.log4j.Log4j;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 20:12
 */

public class SortTest { 
    
    public static void main(String[] arge){
        SortTest test = new SortTest();
        ThreeNumSort qkSort =new ThreeNumSort();
        test.sortTest(qkSort);
    }
    

    public void sortTest(Sort sort){

        System.out.println("测试类为:"+sort.getClass().getName());
        int len = 10;

        while (len <1000000){
            Comparable<Integer>[] comparable = new Comparable[len];
            for (int i = 0; i <len ; i++) {
                comparable[i] = StdRandom.uniform(len);
            }

            Comparable[] sort1 = sort.sort(comparable);

            boolean b = sort.checkSort(sort1);
            System.out.println( "len ="+len+"排序结果为："+b);
            len *=2;
        }
    }



}
