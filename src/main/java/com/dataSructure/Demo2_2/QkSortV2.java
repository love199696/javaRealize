package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:50
 */
public class QkSortV2 extends Sort {


    @Override
    public Comparable[] sort(Comparable[] arr) {

        return sort(arr, 0, arr.length-1);
    }


    private Comparable[] sort(Comparable[] arr, int low, int high) {
        if (high <= low) {
            return arr;
        }
        int mid = getSegmentationPoints(arr, low, high);
        sort(arr,low,mid-1);
        sort(arr,mid+1,high);
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
