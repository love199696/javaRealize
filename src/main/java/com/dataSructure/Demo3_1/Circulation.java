package com.dataSructure.Demo3_1;


import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-28 17:02
 */
public class Circulation<Key extends Comparable,Value> {


    public static <Key extends Comparable,Value>    Node<Key ,Value> execute(Consumer<Node<Key ,Value>> consumer , Node<Key ,Value>  root){


       Node<Key ,Value> current = root;
        while (current != null){
            consumer.accept(current);
        }
        return current;
    }
}
