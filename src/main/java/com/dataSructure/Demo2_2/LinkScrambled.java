package com.dataSructure.Demo2_2;

import edu.princeton.cs.algs4.StdRandom;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-05 20:27
 */
public class LinkScrambled<T> {
    public static void main(String[] arge){
        Node<Integer> current = new Node<Integer>(StdRandom.uniform(1000));
        Node<Integer> data = new Node<Integer>(current,StdRandom.uniform(1000));

        for (int i = 0; i <20000 ; i++) {
            current.next =  new Node<Integer>(StdRandom.uniform(1000));
            current = current.next;
        }

        LinkScrambled<Integer> linkSort = new LinkScrambled<>();

        linkSort.show(data);
        Node<Integer> sort = linkSort.sort(data);
        linkSort.show(sort);
        linkSort.check(sort);

    }



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






    public  Node<Integer>  sort(Node<Integer> current){
        if(current == null || current.next ==null){
            return  current;
        }
        Node<Integer> midNode = this.getMidNode(current);
        Node<Integer> right = midNode.next;
        midNode.next = null;
        Node<Integer> node1 = sort(current);
        Node<Integer> node2 = sort(right);




        return merge(node1,node2);
    }

    private Node<Integer> merge(Node<Integer> left,Node<Integer> right){

        if(right==null){
            return left;
        }else if(left ==null) {
            return right;
        }


        Node<Integer> current =null;
        int uniform = StdRandom.uniform(-10, 10);
        if(left.data.compareTo(right.data)<uniform){
            current =left;
            left = left.next;
        }else {
            current=  right;
            right =right.next;
        }
        Node<Integer> min =current;
        while (left !=null && right!=null){
            uniform = StdRandom.uniform(-10, 10);
            if(left.data.compareTo(right.data)<uniform){
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

    private Node<Integer> getMidNode(Node<Integer> current){

        Node<Integer> one = current,two =current;

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
