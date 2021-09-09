package com.gzzsc.lai.cas;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @className CaSConcurrentStack
 * @Deacription 使用cas实现的栈
 * @Author laizs
 * @Date 2021/7/8 16:58
 **/
public class CaSConcurrentStack<E> {
    AtomicReference<Node<E>> top=new AtomicReference<>();//代表指针的作用

    /**
     * 入栈
     * @param item
     */
    public void push(E item){
        Node<E> newHead=new Node<>(item);
        Node<E> oldHead;
        do {
            oldHead=top.get();
            newHead.next=oldHead;
        }while (!top.compareAndSet(oldHead,newHead));
    }

    /**
     * 出栈
     * @return
     */
    public E pop(){
       Node<E> oldHead;
       Node<E> newHead;
       do{
           oldHead=top.get();
           if(oldHead==null){
               return null;
           }
           newHead=oldHead.next;
       }while (!top.compareAndSet(oldHead,newHead));
       return oldHead.item;
    }

    /**
     * 数据节点，包含数据对象和指向下一个节点的指针
     * @param <E>
     */
    private static class Node<E>{
        public final E item;
        public Node<E> next;
        public Node(E item){
            this.item=item;
        }
    }
    //测试
    public static void main(String[] args) {
        CaSConcurrentStack<Integer> stack=new CaSConcurrentStack<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<200;i++){
                    try {
                        Thread.sleep(200);
                        stack.push(i);
                        System.out.println("入栈,值："+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        while (true){
            //出栈操作
          Integer v=  stack.pop();
          System.out.println("出栈操作,值："+v);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
