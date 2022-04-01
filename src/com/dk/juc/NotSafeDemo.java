package com.dk.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类是不安全的（ArrayList、Set、Map）
 * 1. 故障现象：
 * java.util.ConcurrentModificationException
 * 2. 解决方案：
 * 【ArrayList】
 * 1. Vector
 * 2. Collections.synchronizedList(new ArrayList<>())
 * 3. CopyOnWriteArrayList
 * 4. ArrayList扩容量为以前的一半
 * 【Set】
 * 1. HashSet底层是HashMap
 *      public HashSet() {
 *          map = new HashMap<>();
 *      }
 * 2. Collections.synchronizedSet(new HashSet<>())
 * 3. CopyOnWriteArraySet
 * 【Map】
 * 1. HashMap初始容量是16，负载因子0.75，16*0.75=12，即达到容量达到12 时自动扩容，扩容时翻倍（容量：2 的 x 次幂）
 * 2. Collections.synchronizedMap(new HashMap<>())
 * 3. ConcurrentHashMap
 */
public class NotSafeDemo {
    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();
    }


    public static void listNotSafe() {
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
        System.out.println();
    }

    public static void setNotSafe() {
//         Set<Object> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
         CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println("Thread：" + Thread.currentThread().getName() + "\t\t" + set);
            }, String.valueOf(i)).start();
        }
    }

    public static void mapNotSafe() {
//         Map<String, String> map = new HashMap<>();
//         Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();

        }
    }
}
