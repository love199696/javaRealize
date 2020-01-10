package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:51
 */
public abstract class Sort<T extends Comparable> {

    /**
    *排序抽象类
    **/
    public abstract T[]  sort(T [] arr);

    protected  void show(Comparable[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();

    }

    /**
    *如果q小于p 那么返回true
    **/
    protected boolean less(Comparable q,Comparable p){
        return q.compareTo(p)<0;
    }

    /**
    *交换 q p的位置
    **/
    protected boolean exch(Comparable[] arr,int q,int p){
        Comparable temp = arr[q];

        arr[q] = arr[p];
        arr[p] = temp;
        return true;
    }
    
    /**
    *验证数组是否有序
    **/
    protected boolean checkSort(Comparable[] arr){

        for (int i = 0; i <arr.length ; i++) {
            for (int j = i+1; j <arr.length ; j++) {
                if(less(arr[j],arr[i])){
                    return false;
                }
            }
        }
            return true;
    }





}
