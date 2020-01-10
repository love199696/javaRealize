package com.dataSructure.Demo3_1;


import com.dataSructure.Demo2_2.Node;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-17 13:03
 */
public class OrderedSequentialSearchSt<Key extends  Comparable,Value> implements MyMap<Key,Value>, Iterable {

    private Node<Key,Value> root;

    private Node<Key,Value> last;

    private int len;

    public static void main(String[] arge){
        OrderedSequentialSearchSt<Integer,Integer> st = new OrderedSequentialSearchSt();
        for (int i = 0; i <100 ; i++) {
            st.put(i,i);
        }
        System.out.println(st.size(0,10));
        st.deleteMin();
        st.deleteMin();
        st.deleteMin();
        st.deleteMin();
        st.deleteMin();


        st.deleteMax();
        st.deleteMax();
        st.deleteMax();
        st.deleteMax();
        st.deleteMax();


        Iterator<Integer> keys = st.keys(10, 20);

        while (keys.hasNext()){
            System.out.print(keys.next() + " ");
        }

        System.out.println(st.floor(99));
        System.out.println(st.ceil(0));

        System.out.println(st.rank(10));
        System.out.println(st.select(st.rank(10)));

    }

    @Override
    public void put(Key key, Value value) {
        Node<Key,Value> current = root;
        Node<Key,Value> father = null;
        while (current != null){
            int result = current.key.compareTo(key);
            if (result ==0){
                current.value =  value;
                return;
            }else if(result<0){
                father = current;
                current = current .next;
            }else {
                if(father ==null){
                    root = new Node<>(key,value,root);
                }else {
                    father.next = new Node(key,value,current);
                }
                len++;
                return;
            }

        }

        if(last ==null){
            root = new Node<Key,Value>(key,value,null);
            last =root;
        }else {
            last.next= new Node<Key,Value>(key,value,null);
            last= last.next;
        }
        len++;
    }

    @Override
    public Value get(Key key) {
        Node<Key,Value> current = root;
        while (current != null){
            if (current.key.equals(key)){
                return current.value;
            }
            current = current .next;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node<Key,Value> current = root;
        Node<Key,Value> father = null;

        while (current != null){
            if (current.key.equals(key)){
                if(last ==current  ){
                    last =father;
                }
                if(father ==null){
                    root = root.next;
                }else {
                    father.next = current.next;
                }
                len--;
                return;
            }
            father = current;
            current = current .next;
        }
    }

    @Override
    public boolean contains(Key key) {
        Node<Key,Value> current = root;
        while (current != null){
            if (current.key.equals(key)){
                return true;
            }
            current = current .next;
        }
        return false;
    }



    public Iterator<Value> keys(Key lo,Key hi) {
        PriorityQueue<Value> queue = new PriorityQueue<Value>();

        Node<Key,Value> min = root;

        while (min !=null){
            if (min.key.compareTo(lo)<0){
                min = min.next;
            }else if(min.key.compareTo(hi)>0){
                break;
            }else {
                queue.add(min.value);
                min = min.next;
            }
        }
        Iterator<Value> iterator = new Iterator() {

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public Value next() {
                return queue.poll();
            }
        };
        return iterator;
    }
    @Override
    public boolean isEmpty() {
        return len==0;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }


    @Override
    public Iterator<Key> iterator() {
        Queue<Value> queue = new PriorityQueue();

        return null;
    }





    class Node<Key extends Comparable,Value> {

        private Key key;
        private Value value;
        private Node next;

        public Node() {
        }

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            return key != null ? key.compareTo(node.key)==0 : node.key == null;
        }

    }



    public void deleteMin(){
        if (root !=null){
            root = root.next;
            len--;
        }
    }
    public void deleteMax(){
        Node<Key ,Value> current = root;
        while (current!=null && current.next !=last){
            current =current.next;

        }
        if (current !=null&& current.next ==last ){
            current.next =null;
            last  = current;
            len--;
        }
    }

    public int size(Key lo,Key hi){
        Node min = root;

        int size =0;
        while (min !=null){
            if (min.key.compareTo(lo)<0){
                min = min.next;
            }else if(min.key.compareTo(hi)>0){
                break;
            }else {
                size++;
                min = min.next;
            }
        }
        return size;
    }



    public Key floor(Key key){

        Node<Key ,Value> current = root;

        Key floor = null;
        while (current !=null){

            if (current.key.compareTo(key)>0){
                break;
            }
            floor =current.key;
            current = current.next;
        }
        return floor;
    }
    public Key ceil(Key key){

        Node<Key ,Value> current = root;

        Key ceil = null;
        while (current !=null){

            if (current.key.compareTo(key)>=0){
                ceil =current.key;
                break;
            }

        }
        return ceil;
    }

    public int rank(Key key){

        Node<Key ,Value> current = root;

        int rank = 0;
        while (current !=null){

            if (current.key.compareTo(key)<0){
                rank++;
                current =current.next;
            }else {
                break;
            }

        }
        return rank;
    }

    public Key select(int rank ){

        Node<Key ,Value> current = root;


        while (rank-->0){
            current = current.next;

        }
        return current.key;
    }

}
