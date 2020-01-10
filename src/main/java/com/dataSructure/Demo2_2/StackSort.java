package com.dataSructure.Demo2_2;

import java.util.Stack;

/**
 * 功能描述：
 *2.3.20非递归的快速排序。实现一个非递归的快速排序，使用一个循环来将弹出栈的子数组切分并将结果子数组重新压入栈。注意：先将较大的子数组压入栈，这样就可以保证栈最多只会有lgN个元素。
 * @Author： phm
 * @Date： 2019-11-14 13:52
 */
public class StackSort extends Sort{

    private Stack<SortDto> stack;

    public StackSort() {
        this.stack = new Stack<SortDto>();
    }
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
    @Override
    public Comparable[] sort(Comparable[] arr) {
        if (arr.length<2){
            return arr;
        }
        int low = 0,high = arr.length-1;
        //初始数组有2个或以上元素时入栈
        SortDto subArray=new SortDto();
        subArray.low=low;
        subArray.high=high;
        stack.push(subArray);
         while (!stack.isEmpty()){
             SortDto pop = stack.pop();
             low = pop.low;
             high = pop.high;
             if(low>=high) continue;
             int mid = getSegmentationPoints(arr, low, high);
             if((mid-low)>(high-low)){
                 SortDto big=new SortDto();
                 big.low=low;
                 big.high=mid-1;
                 stack.push(big);

                 SortDto small=new SortDto();
                 small.low=mid+1;
                 small.high=high;
                 stack.push(small);
             }else {
                 SortDto small=new SortDto();
                 small.low=mid+1;
                 small.high=high;
                 stack.push(small);

                 SortDto big=new SortDto();
                 big.low=low;
                 big.high=mid-1;
                 stack.push(big);
             }
         }
        return arr;
    }


    class SortDto {
        private int low;
        private int high;

    }
}
