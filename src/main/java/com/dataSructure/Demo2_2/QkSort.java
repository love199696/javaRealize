package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:50
 */
public class QkSort extends Sort {


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
     * 功能描述：这里以low为基准点、取得小于基准点和大于基准点的。
     * 以j为区分在 j和j的左边 小于 j的右边
     * 递归处理
     * @Author： phm
     * @Date： 2019/11/7 20:10
     *  * @return : null
     */
    private int getSegmentationPoints(Comparable[] arr, int low, int high) {
        int i = low, j = high + 1;
        Comparable value = arr[low];

        while (i < j) {
            while (less(arr[++i], value)){ if(i==high) break;}
            while (less(value, arr[--j])){if(j==low) break;}
            if (j <= i) {
                break;
            }
            exch(arr, i, j);
        }
        exch(arr, low, j);
        return j;
    }


}
