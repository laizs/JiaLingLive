package com.gzzsc.lai.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.RedissonRedLock;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @className RedisLockTest
 * @Deacription 单元测试
 * @Author laizs
 * @Date 2021/11/25 10:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisLockTest {
    @Autowired
    private RedissonClient redissonClient;
    /**
     * 用来实现具体逻辑，对该计数器加1
     */
    private int count;
    private int THREAD_COUNT=100;
    @Test
    public void noLocktest() throws InterruptedException {
        //初始化一个拥有 100 个线程的线程池
        ExecutorService executorService= Executors.newFixedThreadPool(THREAD_COUNT);
        //使用 CountDownLatch 实现线程的协调
        CountDownLatch countDownLatch=new CountDownLatch(THREAD_COUNT);
        for(int i=0;i<THREAD_COUNT;i++){
            final  int index=i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //name 表示该线程的名称
                    String name="client"+(index+1);
                    //执行 count++
                    count++;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //打印各个线程执行结果
                    System.out.println(name + "    执行业务方法，对 count 执行 ++ 操作后 count 的值为 : " + count);
                    //调用countDown方法，表示该线程执行完毕
                    countDownLatch.countDown();
                }
            });
        }
        //使该方法阻塞住，不然看不到效果
        countDownLatch.await();
    }
    /**
     * 测试单实例模式下redis分布式锁
     */
    @Test
    public void testSingleLock() throws InterruptedException {
        //初始化一个拥有 100 个线程的线程池
        ExecutorService executorService= Executors.newFixedThreadPool(THREAD_COUNT);
        //使用 CountDownLatch 实现线程的协调
        CountDownLatch countDownLatch=new CountDownLatch(THREAD_COUNT);
        for(int i=0;i<THREAD_COUNT;i++){
            final  int index=i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    RLock rLock=redissonClient.getLock("mytest");
                    boolean isLock=false;
                    try {
                        //200尝试加锁时间
                        //2000是锁生效时间
                        isLock= rLock.tryLock(2000,20000, TimeUnit.MILLISECONDS);
                        if(isLock){
                            //获取成功
                            //name 表示该线程的名称
                            String name="client"+(index+1);
                            //执行 count++
                            count++;
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //打印各个线程执行结果
                            System.out.println(name + "    执行业务方法，对 count 执行 ++ 操作后 count 的值为 : " + count);
                        }else{
                            //加锁失败
                            System.out.println("!!加锁失败");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        if(isLock){
                            rLock.unlock();
                        }

                    }

                    //调用countDown方法，表示该线程执行完毕
                    countDownLatch.countDown();
                }
            });
        }
        //使该方法阻塞住，不然看不到效果123456

        countDownLatch.await();

    }

    public void redLockTest(){
        RedissonRedLock redLock=new RedissonRedLock();
    }

    /**
     * 布隆过滤器
     */
    @Test
    public void bloomfilter(){
        RBloomFilter<String> bloomFilter= redissonClient.getBloomFilter("phoneList");
        bloomFilter.tryInit(10000l,0.03);//预计元素总数，误差率3%
        //布隆器添加数据
        for(int i=0;i<1000;i++){
            bloomFilter.add("135"+i);
        }
        System.out.println("135100是否存在:"+bloomFilter.contains("135100"));
        System.out.println("135100x是否存在:"+bloomFilter.contains("135100x"));
    }
}
