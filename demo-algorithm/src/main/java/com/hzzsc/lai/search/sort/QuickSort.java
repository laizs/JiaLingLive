package com.hzzsc.lai.search.sort;

import com.alibaba.fastjson.JSONArray;

/**
 * @className QuickSort
 * @Deacription 快速排序
 * https://blog.csdn.net/jianyuerensheng/article/details/51258374
 * @Author laizs
 * @Date 2021/8/17 11:21
 **/
public class QuickSort {
    /**
     * 快速排序，递归算法
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static void quickSort(int arr[],int low,int high){
        if(low>=high){
            return;//递归结束条件
        }
        //记录原始的low high
        int tmp_low=low;
        int tmp_high=high;
        int key=arr[low];//选择的基准比较值，一般是第一个

        while(low<high){//进行一趟排序结束的条件是low>high
            //hign从右向前移动，比较、交换
            while (low<high && arr[high]>=key){
                high--;//直接移动指针
            }
            //进行到此步，表示从high往左搜索，找到比key小的位置
            //交换low和high位置的值
            int tmp=arr[low];
            arr[low]=arr[high];
            arr[high]=tmp;
            //low从做向右移动，比较、交换
            //low从左向右移动，比较、交换
            while (low<high&&arr[low]<=key){
                low++;//直接移动指针
            }
            //进行到此步，表示从low从左向右搜索，找到比key大的位置
            //交换low和high位置的值
            tmp=arr[low];
            arr[low]=arr[high];
            arr[high]=tmp;
        }
        //进行完整的一趟比较后，对基准值划分出的两端序列进行递归调用比较,此时，一定是low=high
        //左边子序列
        quickSort(arr,tmp_low,low-1);
        //右边子序列
        quickSort(arr,low+1,tmp_high);
    }

    public static void main(String[] args) {
        int []array= new int[]{11,3,5,7,9,8,12};
        System.out.println("数组："+ JSONArray.toJSONString(array));
        quickSort(array,0,array.length-1);
        System.out.println("排序后:"+JSONArray.toJSONString(array));
    }
}
