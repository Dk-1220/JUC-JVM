package com.dk.juc;

import com.sun.deploy.security.ruleset.RunRule;

import java.util.concurrent.*;

/**
 * 线程池：
 * 1. ExecutorService newFixedThreadPool(int nThreads)：线程池中有固定 nThreads 个线程
 * 2. ExecutorService newSingleThreadExecutor()：线程池中只有一个线程
 * 3. ExecutorService newCachedThreadPool()：根据实际需求，增加或缩减线程池中的线程数
 * <p>
 * 自定义线程池：
 * ThreadPoolExecutor(
 * int corePoolSize,                   // 核心（常驻）线程数
 * int maximumPoolSize,                // 最大线程数
 * long keepAliveTime,                 // 线程存活时间（非核心线程）
 * TimeUnit unit,                      // 时间单位
 * BlockingQueue<Runnable> workQueue,  // 阻塞队列
 * ThreadFactory threadFactory,        // 线程工厂
 * RejectedExecutionHandler handler)   // 拒绝策略（当线程池和阻塞队列均满了之后无法容纳新线程时采取的策略
 * ）
 * 常用的策略：
 * 1. AbortPolicy：直接抛出java.util.concurrent.RejectedExecutionException 异常阻止系统正常运行
 * 2. CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
 * 3. DiscardPolicy：该策略默默抛弃无法处理的任务，不予任何处理也不抛出异常。如果允许任务丢失，这是最好的一种策略。
 * 4. DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中，尝试再次提交当前任务。
 * 最大线程数计算方式：
 * 1. CPU 密集型：CPU 核数 + 1，可以通过 Runtime.getRuntime().availableProcessors() 获取
 * 2. IO 密集型：CPU 核数 / 阻塞系数，阻塞系数：线程花在系统IO 上的时间与CPU 密集任务所耗的时间比值
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 自定义线程池参数
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        try {
            for (int i = 0; i < 9; i++) {
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void initPool() {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);  // 一个池固定5个工作线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一个池1个工作线程
        ExecutorService threadPool = Executors.newCachedThreadPool();// 一个池N个工作线程，根据需求扩增

        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
