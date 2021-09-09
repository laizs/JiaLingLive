package com.gzzsc.lai;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * @className SynchronousQueueTest
 * @Deacription SynchronousQueue 不存储元素的队列 每个put操作必须等待一个take操作完成
 * 适用于生产者-消费者模式
 * @Author laizs
 * @Date 2021/8/12 15:08
 **/
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> queue=new SynchronousQueue<>();
        new Producter(queue).start();
        new Customer(queue).start();
    }
    static class Producter  extends Thread{
        SynchronousQueue<Integer> queue;
        public Producter(SynchronousQueue<Integer> queue){
            this.queue=queue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    int product = new Random().nextInt(1000);
                    queue.put(product);
                    System.out.println("product a data:" + product);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("queue is empty:"+queue.isEmpty());
            }
        }
    }
    static class Customer extends Thread{
        SynchronousQueue<Integer> queue;
        public Customer(SynchronousQueue<Integer> queue){
            this.queue=queue;
        }

        @Override
        public void run() {
            while (true){
                int data= 0;
                try {
                    data = queue.take();
                    System.out.println("customer a data:"+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
