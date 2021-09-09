package com.gzzsc.lai.cas;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className ReenTrantLockPseuRandom
 * @Deacription 基于ReenTrantLock实现的数据数生成器
 * @Author laizs
 * @Date 2021/7/8 16:08
 **/
public class ReenTrantLockPseuRandom {
    private final Lock lock=new ReentrantLock();
    private int seed;
    public ReenTrantLockPseuRandom(int seed){
        this.seed=seed;
    }
    public int nextInt(int n){
        lock.lock();
        try {
            int s=seed;
            seed=new Random(seed).nextInt();//随机算法这里只是示意
            return seed;
        }finally {
            lock.unlock();
        }
    }
}
