package com.dataSructure.Demo2_2;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-11-06 13:23
 */
public class MoreLineSort {

    public int[] sort(int[] arr) {


        return sort(arr, 0, arr.length);
    }

    private int[] sort(int[] arr, int low, int high) {

        if ((high - low) / 3 == 0) {
            return arr;
        }
        int mid = (high - low) / 3;


        sort(arr, low, mid);
        sort(arr, mid + 1, high - mid - 1);
        sort(arr, high - mid, high);


        return null;
    }

    public int[] merge(int[] arr, int[] aux, int low, int mid, int high) {

        int i = low, j = mid + 1, k = high - mid;
        int index = low;
        List<Integer> list = new ArrayList<>();
        if(aux[i]<aux[j]){
            list.add(aux[i]);
        }else {
            list.add(aux[j]);
        }

        while (index <=high) {








            index++;
        }
        return arr;
    }

}
