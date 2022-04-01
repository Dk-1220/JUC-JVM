package com.dk.juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("Send Email");
    }

    public /*static*/ synchronized void sendSMS() {
        System.out.println("Send SMS");
    }

    public void hello() {
        System.out.println("hello");
    }
}

/**
 * Java 8 锁
 * 第一种锁：同一对象俩同步方法；结果：sendSMS 需等待 sendEmail
 * 第二种锁：不同对象俩同步方法；结果：sendSMS 无需等待 sendEmail
 * 第三种锁：同一对象一普通方法，一同步方法；结果：普通方法无需等待同步方法
 * 第四种锁：不同对象一普通方法，一同步方法；结果：普通方法无需等待同步方法
 * 第五种锁：同一对象俩静态同步方法；结果：sendSMS 需等待 sendEmail
 * 第六种锁：不同对象俩静态同步方法；结果：sendSMS 需等待 sendEmail
 * 第七种锁：同一对象一同步方法，一静态同步方法；结果：同步方法无需等待同步方法
 * 第八种锁：不同对象一同步方法，一静态同步方法；结果：同步方法无需等待同步方法
 * <p>
 * 结论：
 * 1. 同步静态方法执行前需要加类锁，一个类只有一个锁；
 * 2. 静态同步方法执行前需要加对象锁，一个对象只有一个锁；
 * 3. 非同步方法执行前无需加锁。
 */
public class Lock8 {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        Thread.sleep(100);  // 保证争抢顺序，使其不影响判断是不是同一把锁

        new Thread(() -> {
//            phone.sendSMS();
//            phone1.sendSMS();
//            phone.hello();
//            phone1.hello();
//            phone.sendSMS();
            phone1.sendSMS();
        }, "B").start();
    }
}
