package com.dataSructure.Demo2_2;

import java.util.function.BiPredicate;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-18 20:07
 */
public class HeapSort<Key extends Comparable> extends Sort {
    private Comparable[] data;
    private BiPredicate<Comparable, Comparable> predicate = (value1, value2) -> value1.compareTo(value2) < 0;

    public HeapSort(Boolean isMax) {
        if (isMax) {
            this.predicate = (value1, value2) -> value1.compareTo(value2) < 0;
        } else {
            this.predicate = (value1, value2) -> value1.compareTo(value2) > 0;
        }
    }

    public static void main(String[] arge) {
        Integer[] integer = {1, 2, 3, 5, 67, 1, 2, 4};
        HeapSort sort = new HeapSort(true);
        sort.sort(integer);

        System.out.println(integer);

    }


    @Override
    public Comparable[] sort(Comparable[] arr) {
        int length = arr.length;
        for (int i = 1; i <= length; i++) {
            swim(arr, i);
        }

        while (length > 1) {

            exch(arr, 1, length--);
            sink(arr, 1, length);
        }
        return arr;
    }

    private void sink(Comparable[] arr, int i, int length) {
        while (2 * i <= length) {
            int j = 2 * i;
            if (j < length && less(arr, j, j + 1)) {
                j++;
            }
            if (!less(arr, i, j)) {
                break;
            }
            exch(arr, i, j);
            i = j;
        }
    }

    private void swim(Comparable[] arr, int i) {
        while (i / 2 > 0) {
            int j = i / 2;
            if (!less(arr, j, i)) {
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
        Comparable qComparable = arr[--q];
        Comparable pComparable = arr[--p];
        return predicate.test(qComparable, pComparable);
    }
}
