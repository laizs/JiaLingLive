package com.hzzsc.lai.search;

import com.alibaba.fastjson.JSONArray;

/**
 * @className BinarySearch
 * @Deacription 二分查找法
 * @Author laizs
 * @Date 2021/8/16 15:05
 **/
public class BinarySearch {
    public static final int binarySearch(int  []array,int key){
        int low=0;//最小值指针
        int heigh=array.length-1;//最大指针
        int mid=0;//中间指针
        while (low<=heigh){
            mid=(heigh-low)/2+low;
            if(key==array[mid]){//如果刚好找到
                return mid;
            }else if(key<array[mid]){//如果key小于mid为值，折半查找小段
                heigh=mid-1;
            }else if(key>array[mid]){//如果key大于mid为值，折半查找大段
                low=mid+1;
            }
        }
        return -1;//找不到
    }

    public static void main(String[] args) {
        int []array= new int[]{1,3,5,7,9};
        int key=9;
        System.out.println("数组："+ JSONArray.toJSONString(array));
        System.out.println("查找："+key);
        System.out.println("位置:"+binarySearch(array,key));
    }
}
