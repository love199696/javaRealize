package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-05 20:36
 */
public class Node<T extends Comparable> {


         Node<T> next;
         T data;

        public Node() {
        }

        public Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }

        public Node(T data) {
            this.data = data;
        }

}
