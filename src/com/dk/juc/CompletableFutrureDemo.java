package com.dk.juc;

import java.util.concurrent.CompletableFuture;

/**
 * 异步调用 + 回调
 * 1. CompletableFuture<Void> runAsync(Runnable runnable)：设置无返回值异步任务
 * 2. <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)：设置有返回值异步任务
 * 3. CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)：设置回调方法，其中 T 为任务返回值，Throwable为抛出的异常信息
 * 4. CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn) ：设置异常处理，fn为异常对象。当发生异常时，该函数式接口不会返回数据
 * 5. T get()：执行异步任务并返回结果值
 */
public class CompletableFutrureDemo {
    public static void main(String[] args) throws Exception {
        // 设置异步无返回值任务
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回，update mysql ok");
        });
        System.out.println(completableFuture.get());  // 此处需要调用get，CompletableFuture任务才会调用

        // 设置异步有返回值任务
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
//            int i = 1 / 0;
            return 1024;
        });

        // 设置回调方法及异常处理方法
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("t：" + t);
            System.out.println("u：" + u);
        }).exceptionally((e) -> {
            System.out.println("exception：" + e.getMessage());
            return 500;
        });
        // 执行任务
        System.out.println(completableFuture2.get());
    }
}
