package com.dk.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的使用：ArrayBlockingQueue<>(int capacity)，其中capacity 表示队列容量
 * 3 种API 方式调用（有三种方法，插入，移除（删除队头元素），检查（返回队头元素），3 种方式方法名及结果各不相同）
 * 1. 抛出异常方法
 * 插入：void add(e)    异常：java.lang.IllegalStateException: Queue full
 * 移除：E remove()     异常：java.util.NoSuchElementException
 * 检查：E element()    异常：java.util.NoSuchElementException
 * <p>
 * 2. 特殊值方法
 * 插入：boolean offer(E e)  结果：成功为true，失败为false
 * 移除：E poll()            结果：成功返回元素，失败返回null
 * 检查：E peek()            结果：成功返回队头元素，失败返回null
 * <p>
 * 3. 阻塞方法
 * 插入：void put(E e)       结果：失败将会处于一直阻塞状态
 * 移除：E take()            结果：失败将会处于一直阻塞状态
 * 检查：不可用
 * <p>
 * 4. 超时方法
 * 插入：boolean offer(E e, long timeout, TimeUnit unit)    结果：失败后经过指定超时时间后返回false
 * 移除：E poll(long timeout, TimeUnit unit)                结果：失败后经过指定超时时间后返回null
 * 检查：不可用
 *
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws Exception{
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 抛出异常方法
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("a"));
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.element());

        // 特殊值方法
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.offer("a"));
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.peek());

        // 阻塞方法
//        queue.put("a");
//        queue.put("a");
//        queue.put("a");
//        queue.put("a");
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());

        // 超时方法
        System.out.println(queue.offer("a", 3L, TimeUnit.SECONDS));
        System.out.println(queue.offer("a", 3L, TimeUnit.SECONDS));
        System.out.println(queue.offer("a", 3L, TimeUnit.SECONDS));
//        System.out.println(queue.offer("a", 3L, TimeUnit.SECONDS));

        System.out.println(queue.poll(3L, TimeUnit.SECONDS));
        System.out.println(queue.poll(3L, TimeUnit.SECONDS));
        System.out.println(queue.poll(3L, TimeUnit.SECONDS));
        System.out.println(queue.poll(3L, TimeUnit.SECONDS));
    }
}
