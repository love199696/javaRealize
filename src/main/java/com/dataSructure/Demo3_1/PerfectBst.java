package com.dataSructure.Demo3_1;

/**
 * 功能描述：
 *
 * @Author： phm
 * @Date： 2019-12-29 09:59
 */
public class PerfectBst {

    public static void main(String[] arge){
        int[] arr = new int[100];
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = i;
        }
        PerfectBst perfectBst = new PerfectBst();
        BSTV2<Integer, Integer> integerIntegerBSTV2 = perfectBst.binarySearch(arr, 0, arr.length - 1, new BSTV2<Integer, Integer>());

        System.out.println(integerIntegerBSTV2);
    }


    public BSTV2<Integer,Integer>  binarySearch(int [] arr,int low ,int hight,BSTV2<Integer,Integer> bstv2){
        if(low >= hight) return bstv2;
        int mid = low + (hight- low )/2;
        bstv2.put(arr[mid],arr[mid]);

        binarySearch(arr,low,mid-1,bstv2);
        binarySearch(arr,mid+1,hight,bstv2);


        return bstv2;
    }

}
