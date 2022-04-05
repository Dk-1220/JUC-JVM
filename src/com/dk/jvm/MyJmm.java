package com.dk.jvm;

import java.util.concurrent.TimeUnit;

class MyNum {
    volatile int a = 10;

    public void changeA() {
        this.a = 100;
    }
}

/**
 * JMM：Java内存模型
 * <p>
 * 作用：
 * 当主内存共享变量发生变化时，由JMM 控制通知线程进行同步修改
 * <p>
 * JVM 内存模型：
 * 由于JVM 运行程序的实体是线程，而每个线程创建时JVM 都会为其创建一个工作内存（也称栈空间），工作内存是每个线程的私有数据区域，
 * 而Java 内存模型中规定所有变量都存储在主内存，主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作（读取赋值等）必须在工作内存中进行，
 * 首先要将变量从主内存拷贝到线程自己的工作内存空间，然后对变量进行操作，操作完成之后再将变量写回主内存，不能直接操作主内存中的变量，各个线程中的工作
 * 内存中存储着主内存中的变量副本拷贝，因此不同的线程间无法访问对方的工作内存，线程间的通信（传值）必须通过主内存来完成，所以需要JMM 控制通知线程同步修改。
 * <p>
 * 如何使用：在变量前面加修饰符 volatile
 */
public class MyJmm {
    public static void main(String[] args) {
        MyNum myNum = new MyNum();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "**********come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNum.changeA();
            System.out.println(Thread.currentThread().getName() + "\t new a：" + myNum.a);
        }, "A").start();

        while (myNum.a == 10) {
            // 其他线程修改之后，需要JMM 通知main线程变量已修改
        }

        System.out.println(Thread.currentThread().getName() + "\t over");
    }
}
