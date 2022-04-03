package com.dk.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池：
 *      1. ExecutorService newFixedThreadPool(int nThreads)：线程池中有固定 nThreads 个线程
 *      2. ExecutorService newSingleThreadExecutor()：线程池中只有一个线程
 *      3. ExecutorService newCachedThreadPool()：根据实际需求，增加或缩减线程池中的线程数
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);  // 一个池固定5个工作线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一个池1个工作线程
        ExecutorService threadPool = Executors.newCachedThreadPool();// 一个池N个工作线程，根据需求扩增

        try{
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.submit(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
