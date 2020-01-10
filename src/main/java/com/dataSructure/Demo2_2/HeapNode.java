package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiPredicate;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-25 13:14
 */
public class HeapNode<T extends Comparable> {

    private Node<T> max;
    private Node<T> last;
    private int len;
    private BiPredicate<Comparable, Comparable> predicate ;
    public HeapNode(Boolean isMax) {
        if (isMax) {

            this.predicate = (value1, value2) -> value1.compareTo(value2) < 0;
        } else {
            this.predicate = (value1, value2) -> value1.compareTo(value2) > 0;

        }
    }

    public static void main(String[] arge) {
        HeapNode<Integer> heapNode = new HeapNode<>(false);
        for (int i = 0; i <100 ; i++) {
            heapNode.insert(StdRandom.uniform(1000));
        }
        for (int i = 0; i <100 ; i++) {
            System.out.print(heapNode.delMax()+" ");
        }



    }


    public HeapNode(T data) {
        this.max = max;
    }

    public HeapNode() {

    }


    public void insert(T t) {
        Node<T> realNode = insert(last, t);
        len++;
        swim(realNode);
    }





    public void show() {
        Queue<Node> queue = new LinkedBlockingQueue<>();
        if(max ==null){
            return;
        }
        queue.add(max);
        while (!queue.isEmpty()){
            Node first = queue.poll();
            if(first.left!=null){
                queue.add(first.left);
            }
            if(first.right!=null){
                queue.add(first.right);
            }
            System.out.print(first.Data+" ");
        }
    }

    /**
     * 1.向上回溯一层，判断回溯方向：若为自左路回溯，则右路为后一位结点；若为右路回溯，转为第2步。
     * 2.继续向上回溯，直到回溯方向为左路，然向向右下一层，再沿左路下到底。
     * 3.若向上回溯一直为右路，则会回到顶结点，自顶结点一路向左下到底即可。
     **/
    public Node insert(Node<T> last,  T data) {
        if (last == null){
            this.last = new Node<T>(data,null);
            return this.last;
        }
        Node<T> parent =last.parent;
        if (parent == null){
           this. last =   insertLeft(data,last);
            return this.last;
        }

        if (parent.left == last) {
            if (parent.right == null) {
                parent.right = new Node<T>(data, parent);
                this.last = parent.right;
            } else {
                this. last =       insertLeft(data, parent.right);
            }
        } else {
            insert(parent, data);
        }
        return this.last;
    }

    private void insertRightLeft(T data, Node<T> parent) {
        Node<T> current = parent.right;
        while (current.left != null) {
            current = current.left;
        }
        current.left = new Node<T>(data, parent);
        this.last = current.left;
    }

    private  Node insertLeft(T data, Node<T> parent) {
        Node<T> current = parent;
        while (current.left != null) {
            current = current.left;
        }
        current.left = new Node<T>(data, current);
        return current.left;
    }



    public T max() {
        checkIndex();
        return max.Data;
    }

    public T delMax() {
        checkIndex();
        T data = this.max.Data;
        if(this.last == this.max) {
            this.last =null;
            this.max=null;
            return data;
        }
        Node   newLast = this.last;
        Node<T> parent = this.last.parent;
        if (parent.right  == this.last){
            newLast =parent.left;
        }else {
            while (newLast != this.max && newLast == newLast.parent.left) {
                newLast = newLast.parent;
            }
            if (newLast == this.max)
            {
                // 一路向右，回到上一层。
                while (newLast.right != null)
                    newLast = newLast.right;
            }else {
                newLast = newLast.parent.left;
                while (newLast.right != null)
                    newLast = newLast.right;
            }
        }
        // 删除最后一个结点。
        if (parent.left == this.last)
            parent.left = null;
        else
            parent.right = null;
        // 指向新的最后一个结点。
        this.max.Data = this.last.Data;
        this.last = newLast;

        len --;
        sink(max);
        return data;
    }


    /**
     * 二叉树向上浮
     **/
    private void swim(Node<T> last) {
        if (last.parent == null) {
            max = last;
            return;
        }
        T data = last.Data;
        T parentData = last.parent.Data;

        if (less(last,last.parent)) {
            last.Data = parentData;
            last.parent.Data = data;
            swim(last.parent);
        }
    }

    private void sink(Node<T> max) {
        if (max == null) return;
        if (max.left == null) return;
        Node<T> father = max;

        while (father.left != null) {

            Node<T> bigSon = father.left;

            if (father.right != null) {
                bigSon = less(bigSon,father.right) ? bigSon : father.right;
            }

            if (less( bigSon,father)) {
                exch(father, bigSon);
            }
            father =bigSon;
        }
    }

    private void exch(Node<T> father, Node<T> bigSon) {
        T data = father.Data;
        father.Data = bigSon.Data;
        bigSon.Data = data;
    }

    private boolean less(Node<T> node1, Node<T> node2) {
        return predicate.test(node1.Data, node2.Data);
    }

    public boolean isEmpty() {
        return len == 0;
    }

    private int size() {
        return len;
    }


    private void checkIndex() {
        if (len < 1) {
            throw new RuntimeException("优先队列无最大值");
        }
    }


    private class Node<T> {
        /**
         * 数据源
         **/
        private T Data;

        private Node<T> parent;

        private Node<T> left;

        private Node<T> right;

        public Node(T data, Node<T> parent, Node<T> left, Node<T> right) {
            Data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public Node(T data, Node<T> parent) {
            Data = data;
            this.parent = parent;
        }
    }

}
