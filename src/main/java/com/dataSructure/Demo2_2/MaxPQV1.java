package com.dataSructure.Demo2_2;

import java.util.Arrays;

/**
 * 功能描述：无序数组
 *
 * @Author： phm
 * @Date： 2019-11-16 14:37
 */
public class MaxPQV1<T extends Comparable> {
    public static void main(String[] arge){
        Integer[] data = new Integer[10];

        MaxPQV1 pq = new MaxPQV1(data);
        pq.insert(1);
        pq.insert(10);
        pq.insert(100);
        pq.insert(1000);
        pq.insert(10000);
        pq.insert(100000);
        pq.insert(1000000);
        pq.insert(10000000);
        pq.insert(100000000);
        pq.insert(1000000000);
        pq.insert(1000000000);
        pq.insert(1000000000);
        pq.insert(1000000000);


        while (!pq.isEmpty()){
            System.out.println(pq.max());
            System.out.println(pq.delMax());
        }




    }

    private T[] data;

    private int len;

    public T[] getData() {
        return data;
    }

    public int getLen() {
        return len;
    }

    public MaxPQV1(T[] data) {
        this.data = data;
    }
    private void editDataLength(int length){
        this.data  =  Arrays.copyOf(data,length);
    }

    private void isSuitable(){
        int length = data.length;
        if(len >= (length *3/4)){
            editDataLength(length*2);
        }
        if(len <= (length /4) &&length>10){
            editDataLength(length/2);
        }
    }




    public void insert(T t){

        isSuitable();
        data[++len]  =t;
        swim(len);
    }

    public T max(){
        checkIndex();
        return data[1];
    }

    public T delMax(){
        checkIndex();
        isSuitable();
        T datum = data[1];
        data[1] =  data[len];
        data[len--] = null;
        sink(1);
        return datum;
    }
    /**
    *二叉树向上浮
    **/
    private void swim(int k){

        while (k>1 && less(data[k/2],data[k])){
            exch(data,k,k/2);
            k /=2;
        }
    }

    private void sink( int k){

        while (2*k <= len ){
            int j = 2*k;
            if(j<len && less(data[j],data[j+1])){
                j++;
            }
            if(!less(data[k],data[j]))break;
            exch(data,k,j);
            k=j;

        }


    }

    public boolean  isEmpty(){
        return len==0;
    }

    private int size(){
        return len;
    }

    private void checkIndex(){
        if(len<1){
            throw  new RuntimeException("优先队列无最大值");
        }
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
}
