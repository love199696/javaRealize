package com.dataSructure.Demo2_2;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-18 20:07
 */
public class HeapSortV4<Key extends Comparable> extends Sort {
    private Comparable[] data;

    public static void main(String[] arge) {
        Integer[] integer = {1, 2, 3, 5, 67, 1, 2, 4};
        HeapSortV4 sort = new HeapSortV4();
        sort.sort(integer);

        System.out.println(integer);

    }


    @Override
    public Comparable[] sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = length/2; i >0; i--) {
            sink(arr, i,length);
        }

        while (length>0) {
            exch(arr,1,length--);
            int sink = sinkV2(arr, 1, length);

            swim(arr,sink);
        }
        return arr;
    }
    private void sink(Comparable[] arr, int i,int length) {
        while (2 * i <= length) {
            int j = 2 * i;
            if (j < length && less(arr,j,j+1)) {
                j++;
            }
            if (!less(arr,i,j)) {
                break;
            }
            exch(arr, i, j);
            i = j;
        }
    }
    private int sinkV2(Comparable[] arr, int i,int length) {
        while (2 * i <= length) {
            int j = 2 * i;
            if (j < length && less(arr,j,j+1)) {
                j++;
            }
            exch(arr, i, j);
            i = j;
        }
        return i;
    }

    private void swim(Comparable[] arr, int i) {
        while (i / 2 > 0) {
            int j = i / 2;
            if (!less(arr,j,i)) {
                break;
            }
            exch(arr, j, i);
            i = j;
        }
    }

    /**
     * 交换 q p的位置
     **/
    protected boolean exch(Comparable[] arr, int q, int p) {
        Comparable temp = arr[q - 1];

        arr[q - 1] = arr[p - 1];
        arr[p - 1] = temp;
        return true;
    }

    /**
     * 如果q小于p 那么返回true
     **/
    protected boolean less(Comparable[] arr, int q, int p) {
        Comparable qComparable = arr[q-1];
        Comparable pComparable = arr[p-1];
        return qComparable.compareTo(pComparable) < 0;
    }
}
