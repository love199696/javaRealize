package com.dataSructure.Demo3_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-29 15:50
 */
public class RdeBSTV3<Key extends Comparable,Value>{
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


        if(root !=null) {
            Node<Key, Value> right = root.right;
            if(fatherOf(root) != null){
                if(leftOf(fatherOf(root)) == root) this.fatherOf(root).left = right;
                else this.fatherOf(root).right = right;
            }else {
                this.root =right;
            }
            //记录下右边的红色节点

            //将根节点的右链接 变成 红链接的左节点
            root.right = right.left;
            this.setFather(right.left,root);
            //将红链接的 左节点 指向根节点
            right.left = root;
            this.setFather(right,root.father);
            this.setFather(root,right);
            //此时修改红色节点的颜色为 根节点的颜色 此颜色可能为 红 可能为黑
            right.color = root.color;
            //此时 根节点为红色节点 所以修改根节点的颜色
            root.color = Red;
            right.size = root.size;

            root.size = size(root.left) + size(root.right) +1;
            return  right;
        }



            return null;

    }

    private Node<Key,Value> fatherOf(Node<Key,Value> root){

        return root== null ?null :root.father;
    }
    private Node<Key,Value> leftOf(Node<Key,Value> root){

        return root== null ?null :root.left;
    }

    /**
     * 功能描述：将红色右链接 旋转成红色的左链接
     * @Author： phm
     * @Date： 2019/12/29 16:16
     * @param root :
     *  * @return : com.dataSructure.Demo3_2.RdeBST<Key,Value>.Node<Key,Value>
     */
    public Node<Key,Value>  rotateRight(Node<Key,Value> root){
        if(root != null) {

            //记录下右边的红色节点
            Node<Key, Value> left = root.left;
            if(fatherOf(root) != null){
                if(leftOf(fatherOf(root)) == root) this.fatherOf(root).left = left;
                else this.fatherOf(root).right = left;
            }else {
                this.root =left;
            }

            //将根节点的右链接 变成 红链接的左节点
            root.left = left.right;
            this.setFather(left.right,root);
            //将红链接的 左节点 指向根节点
            left.right = root;
            this.setFather(left,root.father);
            //此时修改红色节点的颜色为 根节点的颜色 此颜色可能为 红 可能为黑
            this.setFather(root,left);

            left.color = root.color;
            //此时 根节点为红色节点 所以修改根节点的颜色
            root.color = Red;
            root.size = size(root.left) + size(root.right) +1;left.size = root.size;
            return left;
        }
        return null;
    }
    private void setFather(Node<Key,Value> son,Node<Key,Value> father){
        if(son != null ){
            son.father = father;
        }
    }

    private void flipColors(Node<Key,Value> root){

        root.color = Red;
        root.left.color = Black;
        root.right.color = Black;



    }
    public static void main(String[] arge){
        RdeBSTV3<Integer,String> rdeBST = new RdeBSTV3<>();
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
     put(this.root,key,value);


        this.root.color =Black;

    }

    public void put (Node<Key,Value> root,Key key,Value value){
        if(root ==null) {
            this.root = new Node<>(key,value,null,null,null,1,Red);
            return;
        }
        Node<Key,Value> current = root;
        Node<Key,Value> father = null;
        int result = 0;
        while (current != null){
            if(isRed(current.left) && isRed(current.left.left)){
                current= rotateRight(current);
                flipColors(current);
            }
            father =current;
            result=  key.compareTo(current.key);
            if(result ==0) {
                current.value =value;
                return;
            }else if(result <0){
                current =current.left;
            }else {
                current =current.right;
            }
        }
        Node<Key, Value> son = new Node<>(key, value, father, null, null, 1, Red);
        if(result >0){
            father.right = son;
        }else {
            father.left = son;
        }
        if(isRed(father.right)&& !isRed(father.left) ){
          rotateLeft(father);
        }
        if(isRed(father.left) && isRed(father.right)){
            flipColors(father);
        }

        root.size = size(root.left) +size(root.right ) +1;

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
        Node<Key, Value> left,right;
        Node<Key, Value> father;

        int size;
        boolean color;

        public Node(Key key, Value value,Node<Key, Value> father, Node<Key, Value> left, Node<Key, Value> right, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size = size;
            this.father = father;
            this.color = color;
        }
    }
}


