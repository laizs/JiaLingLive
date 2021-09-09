package com.hzzsc.lai.search.sort;

import com.alibaba.fastjson.JSONArray;

/**
 * @className InsertSort
 * @Deacription 插入排序
 * @Author laizs
 * @Date 2021/8/17 9:41
 **/
public class InsertSort {
    public static final int[] insertSort(int[] arr){
        if(arr.length<2){
            return arr;
        }
        for(int i=1;i<arr.length;i++){//从第二个元素开始遍历
            int insertValue=arr[i];//新插入的值
            //i前面的数组已经排好序，新插入的值跟前面数组比较，找到适当位置插入
            int index=i-1;//被插入的位置（准备和前一个数进行比较）
            while(index>=0&&arr[index]>insertValue){
                //将arr[index]的位置往后挪动
                arr[index+1]=arr[index];
                index--;
            }
            arr[index+1]=insertValue;//将插入的数据放入适合的位置
        }
        return arr;

    }

    public static void main(String[] args) {
        int []array= new int[]{11,3,5,7,9};
        System.out.println("数组："+ JSONArray.toJSONString(array));
        System.out.println("排序后:"+JSONArray.toJSONString(insertSort(array)));
    }
}
