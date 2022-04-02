package com.dk.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("****** come in here");
        return 1024;
    }
}

/**
 * 多线程中，第 3 种获得多线程的方式
 * 1. FutureTask实现了接口RunnableFuture，而RunnableFuture接口继承了Runnable接口，
 * FutureTask中提供FutureTask（Callable c）构造方法，相对于适配器模式
 * 则可以通过FutureTask 实现Callable 转为 Runnable 类型，满足了Thread （Runnable run，String threadName）
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();
        // 获取返回值
        System.out.println(futureTask.get());
    }
}
