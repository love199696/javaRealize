package com.dataSructure.Demo2_2;





import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-18 11:46
 */
public class IndexMinPQV3<Key extends Comparable<Key>> implements Iterable<Integer> {


    private Integer [] pq;//索引二叉树
    private Integer[]qp;//存储索引在 pq中的位置。 pq[0] = i;  pq[i] =0;
    private Key[] keys;//存储真正数据的数组 乱序
    private Integer len; //优先队列的大小

    public IndexMinPQV3(int len) {
        pq = new Integer[++len];
        qp = new Integer[len];
        for (int i = 0; i < qp.length; i++) {
            qp[i] =-1;
        }

        keys = (Key[]) new Comparable[len ];
        this.len =0;
    }

    public void insert(Key key, int index){
        if(qp[index] !=-1){
            keys[index] = key;
            swim(qp[index]);
            sink(qp[index]);
        }else {
            qp[index] = len;
            pq[len] = index;
            keys[index] = key;
            swim(len);
            len++;
        }
    }
    public Key deleteMax(){
        return delete(pq[0]);
    }
    public Key delete(int i) {
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];//这个元素在堆中的位置

        exch(index, --len);//交换堆中的位置
        Key out = keys[i];
        swim(index);
        sink(index);
        qp[i] = -1;
        return out;
    }

    private boolean contains(int i) {


        return qp[i] !=-1;
    }

    private void show(){
        for (int i = 0; i <this.len ; i++) {
            if(2*(i+1)-1 <this.len &&(keys[pq[i]] .compareTo(keys[pq[2*(i+1)-1]])<0) ||( 2*(i+1)< this.len && keys[pq[i]] .compareTo(keys[pq[2*(i+1)]])<0 ) ){
                System.out.println(false);
                throw  new BasicRuntimeException("错误");
            }
//            System.out.print(keys[pq[i]]+" ");
        }
    }

//    public Key  dequeue(){
////        Key key = keys[pq[0]];
////        keys[pq[0]] =null;
////
////        pq[0] =pq[len-1];
////
////
////
////
////
////
////        return ;
//    }



    public static void main(String[] arge){
        int length = 1000000;
//        IndexMinPQV3<Integer> indexMinPQ = new IndexMinPQV3<>(length);
//
//        indexMinPQ.insert(8,8);
//        indexMinPQ.insert(7,2);
//        indexMinPQ.insert(2,8);
//        indexMinPQ.insert(1,5);
//        indexMinPQ.insert(2,7);
//        indexMinPQ.insert(8,9);
//        indexMinPQ.insert(6,9);
//        indexMinPQ.insert(5,6);
//        indexMinPQ.insert(8,8);
//        indexMinPQ.insert(4,0 );
//        System.out.println();
//        while (length-->1000){
//            IndexMinPQV3<Integer> indexMinPQ = new IndexMinPQV3<>(length);
//            for (int i = 0; i <length; i++) {
//                int key = StdRandom.uniform(length);
//                int index = StdRandom.uniform(length);
////                System.out.print(key+","+index +" ");
//                indexMinPQ.insert(key, index);
//
//            }
//            System.out.println();
//            indexMinPQ.show();
//        }

        test();

    }

    public static void test(){

        IndexMinPQV3<Integer> indexMinPQ = new IndexMinPQV3<>(110);
        indexMinPQ.insert(1,1);
        indexMinPQ.insert(2,2);
        indexMinPQ.insert(3,3);
        indexMinPQ.insert(4,4);
        indexMinPQ.insert(5,5);
        indexMinPQ.insert(6,6);
        indexMinPQ.insert(7,7);
        indexMinPQ.insert(8,9);
        indexMinPQ.insert(8,8);
        indexMinPQ.insert(9,9);
        indexMinPQ.insert(10,10);
        indexMinPQ.insert(11,11);
        indexMinPQ.insert(12,12);
        indexMinPQ.insert(13,13);
        indexMinPQ.insert(14,14);
        indexMinPQ.insert(15,15);
        indexMinPQ.show();

        indexMinPQ.change(15,0);
        indexMinPQ.change(14,0);
        indexMinPQ.change(13,0);
        indexMinPQ.change(1,15);

        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
        System.out.println(indexMinPQ.deleteMax());
    }

    public void change(int index,Key key){
        keys[index] = key;
        Integer integer = qp[index];
        swim(integer);
        sink(integer);
    }

    private void sink(int index) {

        while (2 * (index+1)-1 < this.len) {
            int j = 2 * (index+1)-1;
            if (j+1 < this.len && less(keys,j,j+1)) {
                j++;
            }
            if (less(keys,j,index)) {
                break;
            }
            exch(index, j);
            index = j;
        }
    }
    private void swim(int len) {
        while (len != 0) {
            int j = (len-1) / 2;
            if (!less(keys,j,len)) {
                break;
            }
            exch(j, len);
            len = j;
        }
    }


    /**
     * 交换 q p的位置
     **/
    protected boolean exch(int q, int p) {
        //交换 pq索引二叉树中索引
        int temp = pq[q];
        pq[q] = pq[p];
        pq[p] = temp;
        //更新 qp
        qp[pq[q]] =q;
        qp[pq[p]] =p;
        return true;
    }

    /**
     * 如果q小于p 那么返回true
     **/
    protected boolean less(Comparable[] arr, int q, int p) {
        Comparable qComparable = arr[pq[q]];
        Comparable pComparable = arr[pq[p]];
        return qComparable.compareTo(pComparable) < 0;
    }
    protected boolean lessV1(Comparable[] arr, int q, int p) {
        Comparable qComparable = arr[q];
        Comparable pComparable = arr[p];
        return qComparable.compareTo(pComparable) < 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {

    }

    @Override
    public Spliterator<Integer> spliterator() {
        return null;
    }
}
