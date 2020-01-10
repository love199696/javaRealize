package com.dataSructure.Demo3_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-29 15:50
 */
public class RdeBSTV5<Key extends Comparable,Value>{
    private static final boolean Red = true;
    private static final boolean Black= false;

    private Node<Key,Value> root;
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

        root.size = size(root.left) + size(root.right) +1;left.size = root.size;
        return left;
    }

    private void flipColors(Node<Key,Value> root){

        root.color = Red;
        root.left.color = Black;
        root.right.color = Black;



    }
    public static void main(String[] arge){
        RdeBSTV5<Integer,String> rdeBST = new RdeBSTV5<>();
        rdeBST.put(3,"A");
        rdeBST.put(13,"A");
        rdeBST.put(11,"A");
        rdeBST.put(9,"A");
        rdeBST.put(7,"A");
        rdeBST.put(5,"A");
        rdeBST.put(1,"A");
        System.out.println();


    }

    public void put(Key key,Value value){
        this.root = put(this.root,key,value);


        this.root.color =Black;

    }

    public Node<Key,Value> put (Node<Key,Value> root,Key key,Value value){
        if(root ==null)  return new Node<>(key,value,null,null,1,Red);


        int result = key.compareTo(root.key);
        if(result ==0) {
            root.value =value;

        }else if(result <0){
            root.left = put (root.left,key,value);
        }else {
            root.right =  put (root.right,key,value);
        }

        if(isRed(root.left) && isRed(root.left.left)&&isRed(root.left.left.left)){
            root = rotateRight(root);
        }
        if(isRed(root.right)&& !isRed(root.left) ){
            root =  rotateLeft(root);
        }
        if(isRed(root.left) && isRed(root.right)){
            flipColors(root);
        }
        root.size = size(root.left) +size(root.right ) +1;
        return root;
    }

    public int size(Node root) {
        return root == null ? 0 : root.size;
    }

    private  boolean isRed(Node<Key,Value> root){

        if (root == null) return false;
        return root.color ==Red;
    }


    private  class Node<Key,Value> {

        Key key;
        Value value;
        Node<Key, Value> left,right,prev,succ;
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


