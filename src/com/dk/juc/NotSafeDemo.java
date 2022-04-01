package com.dk.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类是不安全的
 * 1. 故障现象：
 *      java.util.ConcurrentModificationException
 * 2. 解决方案：
 *      2.1 Vector
 *      2.2 Collections.synchronizedList(new ArrayList<>())
 *      2.3 CopyOnWriteArrayList
 */
public class NotSafeDemo {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();  //不安全
//        Vector<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println("Thread：" + Thread.currentThread().getName() + "\t\t" + list);
            }, String.valueOf(i)).start();
        }
    }
}
