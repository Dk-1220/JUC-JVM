package com.dk.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java8 特性：允许接口有默认实现方法（使用default修饰符）和静态方法
 */
interface Foo {

    default void a() {

    }

    default void b() {

    }

    static void c() {

    }

    static void d() {

    }
}

/**
 * Ticket资源类
 */
class Ticket {
    private int number = 40;
    private Lock lock = new ReentrantLock();

    public void saleTicket() {
        lock.lock();

        try {
            if (number > 0) {
                System.out.println("进程名：" + Thread.currentThread().getName() + "\t\t 卖出第" + (number--) + "张票，剩余" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 卖票线程类
 */
public class SaleTicket {
    public static void main(String[] args) {
        // 定义资源
        Ticket ticket = new Ticket();

        // 定义线程并启动（就绪）
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.saleTicket();
            }
        }, "C").start();
    }
}