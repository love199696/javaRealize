package com.dataSructure.Demo2_2;

import java.util.Iterator;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-18 19:32
 */
public class IndexMinPQV2 <Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN; //索引优先队列中元素的最大个数
    private int N; //当前索引优先队列中元素的个数
    private int[] pq;//使用一级索引的二叉堆
    private int[] qp;//pq的对称映射 qp[pq[i]] = pq[qp[i]] = i，用于映射key索引对应pq二叉堆中的位置
    private Key[] keys; //keys[i] = priority of i

    public IndexMinPQV2(int maxN) {
        this.maxN = maxN;
        this.N = 0;
        keys = (Key[] )new Object[maxN];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for (int i = 0; i < pq.length; i++) {
            pq[i]=-1;
        }
        for (int i = 0; i < qp.length; i++) {
            qp[i]=-1;
        }
    }
    //返回索引优先队列
    public int size(){
        return N;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return N==0;
    }

    public void insert(int n,Key key){
        if(n<0 || n>=maxN)
            throw new IllegalArgumentException("index i out of boundary.");
        N++;
        pq[N] =n;
        qp[n] =N;
        keys[n] =key;
        adjustUp(N);


    }

    private void adjustUp(int n) {



    }


    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
}
