package com.hzzsc.lai.search.sort;

import com.alibaba.fastjson.JSONArray;

/**
 * @className BubbleSort
 * @Deacription 冒泡排序法
 * @Author laizs
 * @Date 2021/8/16 16:10
 **/
public class BubbleSort {
    public static final int[] bubbleSort(int[] arr){
        if(arr.length<1){
            return arr;
        }
        for(int i=0;i<arr.length;i++){
            for(int j=0;j< arr.length-1-i;j++){
                    if(arr[j+1]>arr[j]){
                        int tmp=arr[j];
                        arr[j]=arr[j+1];
                        arr[j+1]=tmp;
                    }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int []array= new int[]{11,3,5,7,9};
        System.out.println("数组："+ JSONArray.toJSONString(array));
        System.out.println("排序后:"+JSONArray.toJSONString(bubbleSort(array)));
    }
}
