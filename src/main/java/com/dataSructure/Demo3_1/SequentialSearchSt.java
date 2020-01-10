package com.dataSructure.Demo3_1;

import edu.princeton.cs.algs4.In;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-18 18:57
 */
public class SequentialSearchSt<Key, Value> implements MyMap<Key, Value> {


    private Node<Key, Value> root;



    private int len;
    public static void main(String[] arge){

        SequentialSearchSt<Integer,Integer> sequentialSearchSt = new SequentialSearchSt();
        for (int i = 0; i <10 ; i++) {
            sequentialSearchSt.put(i,i);
        }
        System.out.println(sequentialSearchSt.size());
        System.out.println(sequentialSearchSt.get(5));
        System.out.println(sequentialSearchSt.get(6));
        System.out.println(sequentialSearchSt.get(7));
        System.out.println(sequentialSearchSt.isEmpty());

        System.out.println(sequentialSearchSt.contains(7));
        sequentialSearchSt.delete(5);
        sequentialSearchSt.delete(4);
        sequentialSearchSt.delete(3);

        System.out.println(sequentialSearchSt.size());


    }

    


    @Override
    public void put(Key key, Value value) {
        if (root != null) {
            Node<Key, Value> current = root;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
        }
        root = new Node<>(key, value, root);
        len++;
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> current = root;
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        put(key,null);
        len --;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) !=null;
    }

    @Override
    public boolean isEmpty() {
        return len ==0;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public Iterable<Key> keys() {



        return null;
    }


    class Node<Key, Value> {

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


    }
}
