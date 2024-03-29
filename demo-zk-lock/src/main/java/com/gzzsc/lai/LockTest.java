package com.gzzsc.lai;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @className LockTest
 * @Deacription 测试类
 * @Author laizs
 * @Date 2021/11/24 17:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {
    /**
     * 表示开启多个线程并行执行
     */
    private static final int THREAD_COUNT = 100;
    /**
     * 用来实现具体逻辑，对该计数器加1
     */
    private int count;

    @Autowired
    private ShardReentrantLockComponent lockComponent;
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
     * 使用 zookeeper 加锁实现多个线程同时对 count 执行 ++ 操作
     * @throws Exception
     */
    @Test
    public void acquireLockTest() throws Exception {
        //要加锁节点的路径
        String path = "/path/test";
        //初始化一个拥有 100 个线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        //使用 CountDownLatch 实现线程的协调
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for(int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            //提交线程
            executorService.submit(() -> {
                //name 表示该线程的名称
                String name = "client" + (index + 1);
                //result 获取执行完业务逻辑后返回值
                String result = null;
                while (result == null) {
                    //result 为 null 表示没有的锁，会继续执行while循环去竞争获取锁
                    result = lockComponent.acquireLock(new BaseLockHandler<String>(path) {

                        //执行具体的业务逻辑
                        @Override
                        public String handler() {
                            //执行 count++
                            count++;
                            try {
                                //睡眠 50ms ,使测试结果更明显
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //打印各个线程执行结果
                            System.out.println(name + "    执行业务方法，对count++ : " + count);

                            //执行成功后不要返回null，如果返回null，会继续执行while去竞争获取锁
                            return this.getPath();
                        }
                    });
                }
                //调用countDown方法，表示该线程执行完毕
                countDownLatch.countDown();
            });
        }
        //使该方法阻塞住，不然看不到效果
        countDownLatch.await();
    }


}
