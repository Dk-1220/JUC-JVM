package com.dk.jvm;

/**
 * 1. 堆（堆内存在逻辑上分为三部分：新生 + 养老 + 永久，物理上分为两部分：新生 + 养老）
 * 1.1 新生代
 * 1.1.1 伊甸区
 * 1.1.2 幸存0 区
 * 1.1.3 幸存1 区
 * 1.2 老年代
 * 1.3 永久代（元空间）
 * 2. JVM 调优参数
 * 2.1 -Xms10m -Xmx10m -XX: +PrintGCDDetails
 * 2.1.1 -Xms：配置初始化JVM 内存大小
 * 2.1.2 -Xmx：配置JVM 可使用的最大内存大小
 * 2.1.3 +PrintGCDDetails：输出 GC 日志
 * 2.1.4 建议：初始化内存和最大内存大小配置一致，避免 GC 机制内存忽大忽小
 */
public class MyHeap {
    public static void main(String[] args) {
//        long maxMemory = Runtime.getRuntime().maxMemory();  // 返回Java虚拟机试图使用的最大内存量（默认为本地内存的1/4）
//        long totalMemory = Runtime.getRuntime().totalMemory();  // 返回Java虚拟机中的内存总量（默认为本地内存的1/64）
//        System.out.println("-Xmx:MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double) 1024 / 1024) + "MB");
//        System.out.println("-Xmx:TOTAL_MEMORY = " + maxMemory + "（字节）、" + (totalMemory / (double) 1024 / 1024) + "MB");
//        byte[] byt = new byte[40 * 1024 * 1024];  // java.lang.OutOfMemoryError: Java heap space
    }
}
