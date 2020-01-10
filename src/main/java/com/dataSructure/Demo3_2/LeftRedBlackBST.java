package com.dataSructure.Demo3_2;

import com.sun.javafx.collections.ObservableFloatArrayImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import org.apache.poi.hssf.util.HSSFColor;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * 功能描述：左倾红黑树
 *
 * @Author： phm
 * @Date： 2020-01-05 12:34
 */
public class LeftRedBlackBST<Key extends Comparable,Value> {

    private static final boolean Red = true;
    private static final boolean Black= false;

    private Node<Key,Value> root;


    public void put(Key key,Value value){
        this.root = put( root,key,value);
        this.root.color =Black;
    }

    public static void main(String[] arge){
        LeftRedBlackBST<Integer,Integer> bst = new LeftRedBlackBST();
        bst.put(256,256);
        bst.put(417,417);
        bst.put(837,837);
        bst.put(704,704);
        bst.put(357,357);
        bst.put(722,722);
        bst.put(552,552);
        bst.put(113,113);
        bst.put(641,641);
        bst.put(106,106);
        bst.put(572,572);
        bst.put(286,286);
        bst.put(588,588);
        bst.put(111,111);
        bst.put(105,105);




        bst.deleteMin();
        bst.deleteMax();
       bst.show();
    }

    public Value delete(Key key) {
        Value value = this.get(key);
        if(!isRed(this.root.left)&&!isRed(this.root.right)){
            root.color =Red;
        }

        this.root = delete(this.root,key);


        this.root.color = Black;
        return value;
    }

    private Node<Key,Value> delete(Node<Key, Value> root, Key key) {
        int result = key.compareTo(root.key);

        if(result<0){
            if(!isRed(root.left) &&!isRed(root.left.left)){
                root =  movaeRedLeft(root);
            }
            root.left = delete(root.left,key);
        }else {
            if(isRed(root.left) ){
                root =  rotateRight(root);
            }
            if(!isRed(root.right)&&!isRed(root.right.right) ){
                root =  movaeRedRight(root);
            }




        }



    }


    public Key min() {
        Node<Key,Value> currentNode  = this.root;
        while (currentNode !=null){
            if(currentNode .left == null ) return currentNode.key;

            currentNode = currentNode.left;
        }
        return null;
    }
    public Key max() {
        Node<Key,Value> currentNode  = this.root;
        while (currentNode !=null){
            if(currentNode .right == null ) return currentNode.key;
            currentNode = currentNode.right;
        }
        return null;
    }
    public  Value deleteMax(){
        Value value = this.get(max());
        if(!isRed(this.root.left)&&!isRed(this.root.right)){
            root =  movaeRedRight(root);
        }
        this.root = deleteMax(this.root);
        this.root.color = Black;
        return value;
    }

    private Node<Key,Value> deleteMax(Node<Key, Value> root) {
        if(isRed(root.left) ){
            root =  rotateRight(root);
        }
        if(root.right ==null ) return null;

        if(!isRed(root.right)&&!isRed(root.right.right) ){
            root =  movaeRedRight(root);
        }
        root.right = deleteMax(root.right);
        return repair(root);
    }

    private Node<Key,Value> movaeRedRight(Node<Key, Value> root) {
        flipColors(root);

        if(isRed(root.left.left)){
            root = rotateRight(root);
        }
        return root;
    }


    public  Value deleteMin(){
        Value value = this.get(min());
        if(!isRed(this.root.left)&&!isRed(this.root.left.left)){
            root =  movaeRedLeft(root);
        }
        this.root = deleteMin(this.root);
        this.root.color = Black;
        return value;
    }

    private Node<Key,Value> movaeRedLeft(Node<Key,Value> root){

        flipColors(root);

        if(isRed(root.right.left)){
           root.right = rotateRight(root.right);
            root =rotateLeft(root);
        }
        return root;
    }

    public Node<Key,Value> deleteMin(Node<Key,Value> root){
        if(root.left ==null ) return null;
        if(!isRed(root.left) &&!isRed(root.left.left)){
           root =  movaeRedLeft(root);
        }
        root.left = deleteMin(root.left);
        return repair(root);
    }

    private Node<Key,Value> repair(Node<Key, Value> root) {
        //这里需要进行验证是否变成了右红链接 如果是进行左旋
        if(isRed(root.right) &&!isRed(root.left) ){
            root= rotateLeft(root);
        }
        if(isRed(root.left) &&isRed(root.left.left) ){
            root= rotateRight(root);
        }
        if(isRed(root.right) &&isRed(root.left) ){
            flipColors(root);
        }
        root.size = size(root.left) + size(root.right) +1;
        return root;
    }


    private void show(){
        Queue<Node<Key,Value>> queue = new LinkedTransferQueue();
        queue.add(this.root);
        while (!queue.isEmpty()){
            Node<Key, Value> poll = queue.poll();

            System.out.println(poll.key + "   "+poll.color);

            if(poll.left !=null) queue.add(poll.left);
            if(poll.right !=null) queue.add(poll.right);
        }



    }

    private Node<Key,Value> put(Node<Key, Value> root, Key key, Value value) {
        //如果root为空 那么返回一个节点回去
        if(root==null ) return new Node<>(key,value,null,null,1, Red);

        int result = key.compareTo(root.key);

        if(result ==0) root.value =value;
        else if(result >0) root.right = put(root.right,key,value);
        else root.left = put(root.left,key,value);
        //这里需要进行验证是否变成了右红链接 如果是进行左旋
        if(isRed(root.right) &&!isRed(root.left) ){
           root= rotateLeft(root);
        }
        if(isRed(root.left) &&isRed(root.left.left) ){
            root= rotateRight(root);
        }
        if(isRed(root.right) &&isRed(root.left) ){
            flipColors(root);
        }
        root.size = size(root.left) + size(root.right) +1;
        return root;
    }
    public Value get(Key key){
        Node<Key, Value> result = get(root, key);
        return result ==null?null:result.value;
    }

    private Node<Key,Value> get(Node<Key, Value> root, Key key) {
        //如果root为空 那么返回一个节点回去
        if(root==null ) return null;

        int result = key.compareTo(root.key);

        if(result ==0) return root;
        else if(result >0) return  get(root.right,key);
        else return get(root.left,key);
    }


    private  boolean isRed(Node<Key,Value> root){

        if (root == null) return false;
        return root.color ==Red;
    }
    /**
     * 功能描述：将红色右链接 旋转成红色的左链接
     * @Author： phm
     * @Date： 2019/12/29 16:16
     * @param root :
     *  * @return : com.dataSructure.Demo3_2.RdeBST<Key,Value>.Node<Key,Value>
     */
    public Node<Key,Value> rotateLeft(Node<Key,Value> root){
        //记录下右边的红色节点
        Node<Key, Value> right = root.right;
        //将根节点的右链接 变成 红链接的左节点
        root.right = right.left;
        //将红链接的 左节点 指向根节点
        right.left = root;
        //此时修改红色节点的颜色为 根节点的颜色 此颜色可能为 红 可能为黑
        right.color = root.color;
        //此时 根节点为红色节点 所以修改根节点的颜色
        root.color = Red;
        right.size = root.size;
        root.size = size(root.left) + size(root.right) +1;
        return right;
    }

    public int size(Node root) {
        return root == null ? 0 : root.size;
    }
    /**
     * 功能描述：将红色右链接 旋转成红色的左链接
     * @Author： phm
     * @Date： 2019/12/29 16:16
     * @param root :
     *  * @return : com.dataSructure.Demo3_2.RdeBST<Key,Value>.Node<Key,Value>
     */
    public Node<Key,Value> rotateRight(Node<Key,Value> root){
        //记录下右边的红色节点
        Node<Key, Value> left = root.left;
        //将根节点的右链接 变成 红链接的左节点
        root.left = left.right;
        //将红链接的 左节点 指向根节点
        left.right = root;
        //此时修改红色节点的颜色为 根节点的颜色 此颜色可能为 红 可能为黑
        left.color = root.color;
        //此时 根节点为红色节点 所以修改根节点的颜色
        root.color = Red;
        root.size = size(root.left) + size(root.right) +1;
        left.size = root.size;
        return left;
    }

    private void flipColors(Node<Key,Value> root){
        root.color =!root.color;
        root.left.color = !root.left.color;
        root.right.color = !root.right.color;
    }

    private  class Node<Key extends Comparable,Value> {

        Key key;
        Value value;
        Node<Key, Value> left,right;
        int size;
        boolean color;

        public Node(Key key, Value value, Node<Key, Value> left, Node<Key, Value> right, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
            this.color = color;
        }
    }
}
