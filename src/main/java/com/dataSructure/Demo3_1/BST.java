package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-23 20:10
 */
public class BST <Key extends  Comparable<Key>,Value> implements OrderedMap<Key,Value>{

    private Node<Key,Value> root;


    public static void main(String[] arge){
        BST<Integer,Integer> bst = new BST<Integer,Integer>();
        bst.put(10,9);
        bst.put(7,9);
        bst.put(11,9);
        bst.put(6,9);

        bst.put(13,9);
        System.out.println();
        bst.height(bst.root);


    }



    @Override
    public Key min() {
        Node<Key,Value> currentNode  = this.root;
        while (currentNode !=null){
            if(currentNode .left == null ) return currentNode.key;

            currentNode = currentNode.left;
        }
        return null;
    }

    public Node<Key,Value> min(Node<Key,Value> root ){
        Node<Key,Value> currentNode  = root;
        while (currentNode !=null){
            if(currentNode .left == null ) return currentNode;

            currentNode = currentNode.left;
        }
        return null;
    }

    @Override
    public Key max() {
        Node<Key,Value> currentNode  = this.root;
        while (currentNode !=null){
            if(currentNode .right == null ) return currentNode.key;
            currentNode = currentNode.right;
        }
        return null;
    }
    public Node<Key,Value> max(Node<Key,Value> root ){
        Node<Key,Value> currentNode  = this.root;
        while (currentNode !=null){
            if(currentNode .right == null ) return currentNode;
            currentNode = currentNode.right;
        }
        return null;
    }
    @Override
    public Key floor(Key key) {

        Node<Key, Value> floor = floor(root, key);
        if(floor ==null) return  null;


        return floor.key;
    }
    private  Node<Key,Value> floor(Node<Key,Value> node,Key key) {
        if(node == null )return null;
        int result = key.compareTo(node.key);
        if(result == 0) return node;
        if(result <0) return floor(node.left,key);

        Node<Key, Value> floor = floor(node.right, key);
        if(floor ==null){
            return node;
        }else {
            return floor;
        }


    }



    @Override
    public Key ceil(Key key) {



        Node<Key, Value> ceil = ceil(root, key);
        if(ceil ==null) return  null;

        return ceil.key;
    }

    private Node<Key,Value>  ceil(Node<Key,Value> root ,Key key){

        if(root == null )return null;
        int result = key.compareTo(root.key);
        if(result == 0) return root;
        if(result >0) return ceil(root.right,key);

        Node<Key, Value> ceil = ceil(root.left, key);
        if(ceil ==null){
            return root;
        }else {
            return ceil;
        }
    }

    @Override
    public int rank(Key key) {
        return rank(root,key);
    }
    public int rank(Node<Key,Value> root ,Key key){
        if(root == null )return 0;
        int result = key.compareTo(root.key);

        if(result <0){
            return  rank(root.left,key);
        }else  if(result>0){
            return  size(root.left)+1 +rank(root.right,key);
         }else {

            return size(root.left);

        }


    }


    @Override
    public Key select(int index) {
        Node<Key, Value> select = select(root, index);

        return select==null?null:select.key;
    }
    private Node<Key,Value> select(Node<Key,Value> root,int index){
        if (root == null) return null;
        int size = size(root.left);
        if(size == index) return root;
        else if(size>index) return select(root.left,index);
        else return select(root.right,index-size-1);



    }


    public void deleteMin() {
        deleteMin(root);
    }
    public Node<Key,Value>  deleteMin(Node<Key,Value> root) {
       if(root ==null) return null;
       if(root.left ==null) return root.right;

        root.left= deleteMin(root.left);

        root.size = size(root.left) +size(root.right) +1;

        return root;
    }
    @Override
    public void deleteMax() {
        deleteMax(root);
    }
    public Node<Key,Value>  deleteMax(Node<Key,Value> root) {
        if(root ==null) return null;
        if(root.right ==null) return null;

        root.right= deleteMin(root.right);

        root.size = size(root.left) +size(root.right) +1;

        return root;
    }
    @Override
    public int size(Key lo, Key hi) {
        return 0;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value value) {

       this.root = this.put(root,key,value);
    }

    public Node<Key,Value> put(Node<Key,Value> root ,Key key, Value value) {
        int height=0;
        if(root ==null ) return new Node<>(key,value,null,null);
        else {
            int result = key.compareTo(root.key);
            if(result <0) {
                root.left = put(root.left,key,value);
                if(root.right ==null) height =1;
            }else if(result>0){
                root.right = put(root.right,key,value);
                if(root.left ==null) height =1;
            }else {
                root.value = value;
            }
        }
        root .height = height(root.left)+height(root.right)+height;
        root.size  = size(root.left)+size(root.right)+1;
        return root;
    }

    @Override
    public Value get(Key key) {
        if(this.root == null) return null;
        return get(root,key);
    }
    private Value get(Node<Key,Value> root,Key key){
        if(root == null) return null;
        int result = key.compareTo(root.key);

        if(result <0){
            return get(root.right,key);
        }else if (result>0){
            return get(root.right,key);
        }else {
            return root.value;
        }
    }




    @Override
    public void delete(Key key) {



        root =  delete(root,key);
    }

    public Node<Key,Value> delete(Node<Key,Value> root ,Key key){
        if(root ==null) return null;
        int result = key.compareTo(root.key);

        if(result <0){
            root.left =  delete(root.left,key);
        }else if (result>0){
            root.right =   delete(root.right,key);
        }else {

            if(root.left ==null) return root.right;
            if(root.right ==null) return root.left;

            Node<Key,Value> del = root;
            root = min(root.right);
            root.right = deleteMin(del.right);
            root.left =del.left;


        }
        root.size = size(root.left) +size(root.right) +1;

        return  root;
    }



    @Override
    public boolean contains(Key key) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
       return size(this.root);
    }
    private int size(Node root){
        if(root ==null ) return 0;
        else return root.size;
    }

    public int height(Node root){
        int left = 0;
        int right = 0;
        if(root ==null ) return 0;
        if (root.left != null) left =height(root.left)+1;
        if (root.right != null) right =height(root.right)+1;

        return Math.max(left,right);
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }


    class Node<Key, Value> {

        private Key key;
        private Value value;
        private Node<Key, Value> left,right;
        private int size;
        private int height;

        public Node() {
        }
        private int size(Node root){
            if(root ==null )return 0;
            return size(root.left) +size(root.right) +1;
        }



        public Node(Key key, Value value, Node left,Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.size=1;
        }
    }
}
