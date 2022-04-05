package com.dk.jvm;

class CodeBlock01 {
    public CodeBlock01() {
        System.out.println("CodeBlock01构造方法");
    }

    {
        System.out.println("CodeBlock01构造代码块");
    }

    static {
        System.out.println("CodeBlock01静态代码块");
    }
}

/**
 * 1. 静态代码块只在类加载（类只装载一次）时被执行
 * 2. 构造代码块和构造方法在创建对象时都会被执行
 */
public class MyCodeBlock {
    public MyCodeBlock() {
        System.out.println("MyCodeBlock构造方法");
    }

    {
        System.out.println("MyCodeBlock构造代码块");
    }

    static {
        System.out.println("MyCodeBlock静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("====================MyCodeBlock的main方法开始执行");
        new CodeBlock01();
        System.out.println("====================");
        new CodeBlock01();
        System.out.println("====================");
        new MyCodeBlock();
    }
}
