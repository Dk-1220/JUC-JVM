package com.dk.jvm;

/**
 * 1. 【类装载器】
 * 1.1 启动类加载器（Bootstrap）C++ ：负责加载Object等java.lang包下的类，类加载路径：$Java_HOME/jre/lib/rt.jar
 * 1.2 扩展类加载器（Extension）Java ：负责加载Java 1.0 之后的扩展类，类加载路径：$Java_HOME/jre/lib/ext/*.jar
 * 1.3 应用程序类加载器（AppClassLoader）：也叫系统类加载器，加载当前应用的classpath 的所有类，类加载路径：$CLASSPATH
 *
 * 2. 【双亲委派机制】
 * 2.1 运行类之前需要做类加载，类加载时先从顶层类加载器开始找，即 Bootstrap -> Extension -> AppClassLoader，若顶层找到即不会往下面找。
 *
 * 2.2 原理：
 * 当一个类收到了类加载请求，他首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，每一个层次类加载器都是如此，
 * 因此所有的加载请求都应该传送到启动类加载其中，只有当父类加载器反馈自己无法完成这个请求的时候（在它的加载路径下没有找到所需加载的Class），
 * 子类加载器才会尝试自己去加载。
 *
 * 2.3 好处：
 * 采用双亲委派的一个好处是比如加载位于 rt.jar 包中的类java.lang.Object，不管是哪个加载器加载这个类，最终都是委派给顶层的启动类加载器进行
 * 加载，这样就保证了使用不同的类加载器最终得到的都是同样一个Object对象。
 *
 *
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());  // 获取Object对象的模板类的类加载器，结果为null（实际为Bootstrap）

        System.out.println("--------------------");

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());  // null
        System.out.println(myObject.getClass().getClassLoader().getParent());  // ExtClassLoader
        System.out.println(myObject.getClass().getClassLoader());  // AppClassLoader
    }
}
