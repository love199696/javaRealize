package com.dataSructure.Demo2_2;

import java.math.BigDecimal;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-13 11:07
 */
public class BuildOptimumSortArray  {
    public static void main(String[] arge){

    }



    public static Comparable[] builder(Comparable[] arr){


        return builder(arr,0,arr.length-1);
    }
    
    
    /**
     * 1234
     *
    **/
    private static Comparable[] builder(Comparable[] arr, int low, int high) {
        if(low<high){
            int mid = (low+ high)/2;
            builder(arr,low,mid);
            builder(arr,mid+1,high);
            exch(arr,low,mid);
        }
        return arr;
    }


    protected static boolean exch(Comparable[] arr,int q,int p){
        Comparable temp = arr[q];

        arr[q] = arr[p];
        arr[p] = temp;
        return true;
    }
}
