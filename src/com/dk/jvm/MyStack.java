package com.dk.jvm;

/**
 * 1. 【stack】
 * 1.1 概念：
 * 栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命是跟随线程中的生命期，线程结束栈内存也就释放，对于栈来说不存在垃圾回收问题，
 * 只要线程一结束该栈就 Over，生命周期和线程一致，是线程私有的。
 *
 * 1.2 作用：
 * 栈管运行，堆管数据
 *
 * 1.3 栈保存的内容（栈中的数据都是以栈帧格式存在，栈帧是一个内存区块，是一个数据集）：
 * 8 种基本类型的变量 + 对象的引用变量 + 实例方法都是在方法的栈内存中分配
 *
 * 1.4 栈帧（Java方法进栈之后称为栈帧）保存的内容：
 * 本地变量（Local Variables）：输入参数和输出参数以及方法内的变量；
 * 栈操作（Operand Stack）：记录出栈、入栈的操作；
 * 栈帧数据（Frame Data）：包含类文件、方法等
 *
 * 1.5 Exception in thread "main" java.lang.StackOverflowError：栈溢出错误（Error ）
 *
 */
public class MyStack {

    public static void m1(){
        // Exception in thread "main" java.lang.StackOverflowError
//        m1();
        System.out.println("******m1");
    }

    public static void main(String[] args) {
        System.out.println("1111");
        m1();
        System.out.println("2222");
    }
}