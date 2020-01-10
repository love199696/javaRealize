package com.dataSructure.Demo2_2;


import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.ss.formula.functions.T;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-18 11:46
 */
public class IndexMinPQ <Key extends Comparable<Key>> implements Iterable<Integer> {


    private Integer [] pq;//索引二叉树
    private Integer[]qp;//存储索引在 pq中的位置。 pq[0] = i;  pq[i] =0;
    private Key[] keys;//存储真正数据的数组 乱序
    private Integer len; //优先队列的大小

    public IndexMinPQ(int len) {
        pq = new Integer[++len];
        qp = new Integer[len];
        keys = (Key[]) new Comparable[len ];
        this.len =0;
    }

    public void insert(Key key, int index){
        if(qp[index] !=null){
            keys[index] = key;
            swim(qp[index]);
            sink(qp[index]);
        }else {
            qp[index] = len;
            pq[len] = index;
            keys[index] = key;
            swim(len);
            len++;
        }
    }
    private void show(){
        for (int i = 0; i <this.len ; i++) {
            if(2*(i+1)-1 <this.len &&(keys[pq[i]] .compareTo(keys[pq[2*(i+1)-1]])<0) ||( 2*(i+1)< this.len && keys[pq[i]] .compareTo(keys[pq[2*(i+1)]])<0 ) ){
                System.out.println(false);
                return;
            }
            System.out.print(keys[pq[i]]+" ");
        }
    }

//    public Key  dequeue(){
////        Key key = keys[pq[0]];
////        keys[pq[0]] =null;
////
////        pq[0] =pq[len-1];
////
////
////
////
////
////
////        return ;
//    }



    public static void main(String[] arge){
        IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<>(100);
        for (int i = 0; i <100; i++) {
            indexMinPQ.insert(StdRandom.uniform(100),StdRandom.uniform(100));
        }

        indexMinPQ.show();
    }



    private void sink(int index) {

        while (2 * (index+1) < this.len) {
            int j = 2 * (index+1);
            if (j < this.len && lessV1(keys,pq[j],pq[j-1])) {
                j--;
            }
            if (!lessV1(keys,pq[j],pq[index])) {
                break;
            }
            if(index >=this.len||j >=this.len){
                System.out.println();
            }
            exch(index, j);
            index = j;
        }
    }
    private void swim(int len) {
        len ++;
        while (len / 2 > 0) {
            int j = len / 2;
            if (!less(keys,j,len)) {
                break;
            }
            if(len >=this.len+1||j >=this.len+1){
                System.out.println();
            }
            exch(j, len);
            len = j;
        }
    }


    /**
     * 交换 q p的位置
     **/
    protected boolean exch(int q, int p) {
        q--;p--;
        //交换 pq索引二叉树中索引
        int temp = pq[q];
        pq[q] = pq[p];
        pq[p] = temp;
        //更新 qp
        qp[pq[q]] =q;
        qp[pq[p]] =p;
        return true;
    }

    /**
     * 如果q小于p 那么返回true
     **/
    protected boolean less(Comparable[] arr, int q, int p) {
        Comparable qComparable = arr[pq[q-1]];
        Comparable pComparable = arr[pq[p-1]];
        return qComparable.compareTo(pComparable) < 0;
    }
    protected boolean lessV1(Comparable[] arr, int q, int p) {
        Comparable qComparable = arr[q];
        Comparable pComparable = arr[p];
        return qComparable.compareTo(pComparable) < 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {

    }

    @Override
    public Spliterator<Integer> spliterator() {
        return null;
    }
}
