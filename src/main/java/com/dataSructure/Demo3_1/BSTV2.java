package com.dataSructure.Demo3_1;

import com.mysql.jdbc.log.NullLogger;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdRandom;
import org.apache.poi.ss.formula.ptg.Ref3DPtg;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-23 20:10
 */
public class BSTV2<Key extends Comparable<Key>, Value> implements OrderedMap<Key, Value> {

    private Node<Key, Value> root;


    public static void main(String[] arge) {
        BSTV2<Integer, Integer> bst = new BSTV2<Integer, Integer>();
        for (int i = 0; i <1000 ; i++) {
            bst.put(StdRandom.uniform(1000),i);
        }
        System.out.println();

        System.out.println(bst.isOrdered(bst.root,bst.min(),bst.max()));
        System.out.println(bst.haisNoDupLicates(bst.root));
        System.out.println(bst.checkSelect(bst.root));
        bst.printLevel(bst.root);

    }

    @Override
    public Key min() {

        Node<Key, Value> current = root;
        while (current != null) {
            if (current.left == null) return current.key;

            current = current.left;

        }
        return null;
    }

    public Node<Key, Value> min(Node<Key, Value> root) {

        Node<Key, Value> current = root;
        while (current != null) {
            if (current.left == null) return current;

            current = current.left;

        }
        return null;
    }

    @Override
    public Key max() {
        Node<Key, Value> current = root;
        while (current != null) {
            if (current.right == null) return current.key;

            current = current.right;
        }
        return null;
    }

    @Override
    public Key floor(Key key) {

        Node<Key, Value> current = root;
        Key floor = null;
        while (current != null) {
            int result = key.compareTo(current.key);
            if (result == 0) return current.key;
            else if (result < 0) current = current.left;
            else {
                floor = current.key;
                current = current.right;
            }

        }
        return floor;
    }

    @Override
    public Key ceil(Key key) {
        Node<Key, Value> current = root;
        Key floor = null;
        while (current != null) {
            int result = key.compareTo(current.key);
            if (result == 0) return current.key;
            else if (result > 0) current = current.right;
            else {
                floor = current.key;
                current = current.left;
            }
        }
        return floor;
    }

    @Override
    public int rank(Key key) {
        Node<Key, Value> current = root;
        int size = 0;
        while (current != null) {
            int result = key.compareTo(current.key);
            if (result == 0) {
                size += size(current.left);
                break;
            } else if (result < 0) current = current.left;
            else {
                size += size(current.left) + 1;
                current = current.right;
            }
        }
        return size;
    }

    @Override
    public Key select(int index) {
        Node<Key, Value> current = root;
        while (current != null) {
            int size = size(current.left);
            if (index == size) return current.key;
            else if (index < size) current = current.left;
            else {
                index = index - size - 1;
                current = current.right;
            }
        }
        return null;
    }

    @Override
    public void deleteMin() {
        Node<Key, Value> current = root;
        Node<Key, Value> parent = null;
        while (current != null) {
            if (current.left == null) break;
            parent = current;
            parent.size--;
            current = current.left;

        }
        if (parent == null) root = null;
        else {
            parent.left = parent.left.right;
        }
    }

    public Node<Key, Value> deleteMin(Node<Key, Value> root) {
        Node<Key, Value> current = root;
        Node<Key, Value> parent = null;
        while (current != null) {
            if (current.left == null) break;
            parent = current;
            parent.size--;
            current = current.left;

        }
        if (parent == null){
            root = current.right;
            root.prev =null;
        }
        else {
            parent.left = parent.left.right;
            parent.left.prev =  parent;
        }
        return root;
    }

    @Override
    public void deleteMax() {
        Node<Key, Value> current = root;
        Node<Key, Value> parent = null;
        while (current != null) {
            if (current.right == null) break;
            parent = current;
            parent.size--;
            current = current.right;

        }
        if (parent == null) root = null;
        else {
            parent.right = parent.right.left;
        }
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

        Node<Key, Value> param = null;
        int result = 0;
        Node<Key, Value> current = root;
        while (current != null) {
            param = current;
            result = key.compareTo(current.key);
            if (result == 0) {
                current.value = value;
                return;
            } else if (result > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        Node<Key, Value> keyValueNode = new Node<>(key, value, null, null,param);
        if (param == null) root = keyValueNode;
        else {
            if (result < 0) {
                param.left = keyValueNode;
            } else if (result > 0) {
                param.right = keyValueNode;
            }

            current = root;
            while (current.right != null || current.left != null) {
                current.size++;
                result = key.compareTo(current.key);
                if (result == 0) {
                    break;
                } else if (result > 0) {
                    current = current.right;
                } else {
                    current = current.left;
                }
            }

        }
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> current = root;
        while (current != null) {
            int result = key.compareTo(current.key);
            if (result == 0) return current.value;
            else if (result > 0) current = current.right;
            else current = current.left;
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node<Key, Value> current = root;
        Node<Key, Value> parent = null;
        int result = 0;
        while (current != null) {
            result = key.compareTo(current.key);

            if (result == 0) {
                break;
            } else if (result < 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }

        }
        if (current == null) return;

        Node<Key, Value> left = current.left;
        Node<Key, Value> right = current.right;
        Node<Key, Value> min = min(right);

        if (parent == null) {

            if (left == null) {
                root = root.right;
            } else if (right == null) {
                root = root.left;

            } else {
                root = min;
                min.right = deleteMin(right);

                min.left = left;
                min.size = size(min.left) +size(min.right)+1;
            }
            root.prev =root.left;
            root.succ =root.right;
        } else {
            if (right == null || left == null){
                assignment(current, parent, null);
                resetSize(key, min,false);
            } else {
                min.left = left;
                min.right = deleteMin(right);
                resetSize(key, min,true);

            }
            parent.prev = min;
            min.prev = min.left;
            min.succ = min.right;
            if(min.left != null)  min.left.prev  = min;
            if(min.right != null)  min.left.prev  = min;



        }
    }

    private void resetSize(Key key, Node<Key, Value> min,boolean isModify) {
        int result;
        Node<Key, Value> parent;
        Node<Key, Value> delCurrent = root;
        while (delCurrent != null) {
            delCurrent.size--;
            result = key.compareTo(delCurrent.key);
            if (min == delCurrent) {
                break;
            } else if (result < 0) {
                delCurrent = delCurrent.left;

            } else {
                delCurrent = delCurrent.right;
            }

            parent = delCurrent;
        }
        if(isModify){
            min.size = size(min.left) + size(min.right) + 1;
        }
    }

    private void assignment(Node<Key, Value> current, Node<Key, Value> parent, Node<Key, Value> min) {
        if (parent.left == current) parent.left = min;
        else parent.right = min;
    }

    @Override
    public boolean contains(Key key) {
        Node<Key, Value> current = root;

        while (current != null) {
            int result = key.compareTo(current.key);

            if (result == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return root.size;
    }

    public int size(Node root) {
        return root == null ? 0 : root.size;
    }

    @Override
    public Iterable<Key> keys() {
        return null;
    }


    public boolean  isBinaryTree(Node<Key, Value> root){
        if(root == null) return true;

        int size = size(root.left) +size(root.right) +1;
        if(size != root.size) return false;

        return isBinaryTree(root.left) &&   isBinaryTree(root.right);

    }

    public boolean isOrdered(Node<Key, Value> root, Key min,Key max){
        if (root ==null) return true;
        int minResult = root.key.compareTo(min);
        int maxResult = root.key.compareTo(max);
        if(minResult <0 || maxResult >0){
            return false;
        }



        return isOrdered(root.left,min,max) && isOrdered(root.right,min,max);
    }
    public boolean haisNoDupLicates(Node<Key, Value> root){
        if (root ==null) return true;

        if(compareTo(root,root.left) || compareTo(root,root.right)) return false;



        return  haisNoDupLicates(root.left) && haisNoDupLicates(root.right);
    }

    public boolean compareTo(Node<Key, Value> left,Node<Key, Value> right){
        if (left ==null ) return false;
        if (right ==null ) return false;
        if(left.key.compareTo(right.key) ==0) return true;
        return false;
    }

    public boolean checkSelect(Node<Key, Value> root){
        if(root ==null) return true;
        int rank = size(root.left);

        int rankIndex = rank(select(rank));
        if(rankIndex != rank) return false;

        Key select = select(rank(root.key));
        if(root.key.compareTo(select) !=0) return false;



        return checkSelect(root.left) && checkSelect(root.right);
    }

    public  void printLevel(Node<Key, Value> root){
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);


        while (!queue.isEmpty()){
            Node dequeue = queue.dequeue();
            System.out.print(dequeue.key+" ");
            if(dequeue.left !=null) {
                queue.enqueue(dequeue.left);
            }
            if(dequeue.right !=null) {
                queue.enqueue(dequeue.right);
            }
        }








    }




}
