package com.dataSructure.Demo2_2;



/**
 * 功能描述：2.2.17链表的归并排序
 *
 * @Author： phm
 * @Date： 2019-11-05 18:46
 */
public class LinkSort<T extends Comparable> {


    public void check(Node<Integer> data){
        int n1 =data.data,n2;
        data =data.next;


        while (data !=null){
            n2 =data.data;
            if(n1>n2){
                System.out.println("排序错误"+n1+"*----"+n2);
            }
            n1 =n2 ;
            data =data.next;

        }

    }

    public void show(Node<Integer> data){

        while (data !=null){
            System.out.print(data.data+" ");
            data =data.next;
        }
        System.out.println();
    }

    
    
    
    

    public  Node<T>  sort(Node<T> current){
        if(current == null || current.next ==null){
            return  current;
        }
        Node<T> midNode = this.getMidNode(current);
        Node<T> right = midNode.next;
        midNode.next = null;
        Node<T> node1 = sort(current);
        Node<T> node2 = sort(right);




    return merge(node1,node2);
    }

    private Node<T> merge(Node<T> left,Node<T> right){

        if(right==null){
            return left;
        }else if(left ==null) {
            return right;
        }

        Node<T> current =null;
        if(left.data.compareTo(right.data)<0){
            current =left;
            left = left.next;
        }else {
            current=  right;
            right =right.next;
        }
        Node<T> min =current;
        while (left !=null && right!=null){
            if(left.data.compareTo(right.data)<0){
                current.next =left;
                left = left.next;
            }else {
                current.next=  right;
                right =right.next;
            }
            current = current.next;
        }
        if(left==null){
            current.next=right;
        }
        if(right==null){
            current.next = left;
        }
        return min;
    }

    private Node<T> getMidNode(Node<T> current){

        Node<T> one = current,two =current;

        while (two !=null){

            two =two.next;
            if(two !=null &&two.next!=null){
                two = two.next;
                one = one.next;
            }
        }
        return one;
    }









}
