package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-07 19:50
 */
public class ThreeNumSort extends Sort {


    @Override
    public Comparable[] sort(Comparable[] arr) {
        return sort(arr, 0, arr.length - 1);
    }


    private Comparable[] sort(Comparable[] arr, int low, int high) {
        if (high <= low) {
            return arr;
        }
        int mid = partition(arr, low, high);
        sort(arr, low, mid - 1);
        sort(arr, mid + 1, high);

        return arr;
    }
    private  int partition(Comparable[] a,int lo,int hi)
    {
        int i=lo,j=hi+1;
        int newVIndex=ThreeMedianIndex(a,lo,hi);
        exch(a,lo,newVIndex);
        Comparable v=a[lo];
        while(true)
        {
            while(less(a[++i],v)) if(i==hi) break;
            while(less(v,a[--j])) if(j==lo) break;

            if(i>=j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
//    private int getMidComparable(Comparable[] a, int lo, int hi) {
//        //子数组少于3个元素时，第一个元素作为切分元素
//        if((hi-lo+1)<3)  return lo;
//        //子数组有3个或以上元素时，取子数组前三个元素的中位数作为切分元素
//        ////将原数组的前三个元素的索引作为新数组的值
//        int i1 = a[lo].compareTo(a[lo + 1]);
//        int i2 = a[lo].compareTo(a[lo + 2]);
//        int i3 = a[lo+1].compareTo(a[lo + 2]);
//        if(i1*i2<0){
//
//        }
//
//
//        return ;
//    }
    private  int ThreeMedianIndex(Comparable[] a,int lo,int hi)
    {
        //子数组少于3个元素时，第一个元素作为切分元素
        if((hi-lo+1)<3)  return lo;
        //子数组有3个或以上元素时，取子数组前三个元素的中位数作为切分元素
        ////将原数组的前三个元素的索引作为新数组的值
        Integer[] b={lo,lo+1,lo+2};
        ////使用插入排序法排序新数组b,按原数组的值进行排序。排序后的结果是原数组中小中大值对应的索引
        for(int i=0;i<2;i++)
            for(int j=i+1;j>0;j--)
                if (less(a[b[j]],a[b[j-1]])) exch(b,j,j-1);
        return b[1];
    }

    /**
     * 维护三个指针。low-lt的数据小于datum      gt-high大于v  lt-index =v   index -gt的还未确认
     * 递归处理
     *
     * @Author： phm
     * @Date： 2019/11/7 20:10
     * * @return : null
     */
    private int getSegmentationPoints(Comparable[] arr, int low, int high) {

        int i = low, j = high + 1;
//        int midComparable = getMidComparable(arr, low, high);
//        exch(arr,low,midComparable);
        Comparable datum = arr[low];

          while (i < j) {
              while (less(arr[++i], datum)){ if(i==high) break;}
              while (less(datum, arr[--j])){ }
              if (j <= i) {
                  break;
              }
              exch(arr, i, j);
          }
        exch(arr, low, j);
        return j;
    }


}
