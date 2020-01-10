package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-28 17:17
 */
public class Node<Key, Value> {

    Key key;
    Value value;
    Node<Key, Value> left,right,prev,succ;
    int size;
    int height;

    public Node() {
    }
    private int size(Node root){
        if(root ==null )return 0;
        return size(root.left) +size(root.right) +1;
    }



    public Node(Key key, Value value, Node left,Node right,Node prev) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.prev = prev;
        this.size=1;
    }
}
