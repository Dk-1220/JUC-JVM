package com.dk.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer> {

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        } else {
            int middle = (begin + end) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle + 1, end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}

/**
 * 分支合并框架
 * 1. ForkJoinPool
 *      1.1 分支合并线程池
 *      1.2 调用submit 方法提交任务，ForkJoinTask<T> submit(ForkJoinTask<T> task)
 *      1.3 调用shutdown 方法关闭任务
 * 2. ForkJoinTask（分支合并任务）
 *      2.1 调用get 获取线程计算结果
 * 3. RecursiveTask（Recursive：递归）
 *      3.1 调用fork方法相对于调用compute方法
 *      3.2 调用join方法获得计算返回值，类似 FutureTask.get()
 *      3.3 RecursiveTask<V> extends ForkJoinTask<V>，fork 和 join 都继承于 ForkJoinTask
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);// 递交任务
        System.out.println(forkJoinTask.get());  // 获取返回值
        threadPool.shutdown();  // 关闭线程池
    }
}
