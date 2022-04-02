package com.dk.juc;

class Person {
    String username;

    public Person() {
    }

    public Person(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class TestTransferValue {
    public static void main(String[] args) {
        TestTransferValue transferValue = new TestTransferValue();

        int i = 10;
        transferValue.changeInt(i);
        System.out.println("-------i：" + i);  // 不改变，基本类型值传递

        Person person = new Person("ddd");
        transferValue.changeObject(person);
        System.out.println("-------username：" + person.getUsername());  // 改变，引用类型传递

        String str = "aaa";
        transferValue.changeString(str);
        System.out.println("-------str："+str);  // 不改变，可理解为String引用类型相对于C语言的指针类型
    }

    public void changeInt(int value) {
        value = -1;
    }

    public void changeObject(Person person) {
        person.setUsername("xxx");
    }

    public void changeString(String str) {
        str = "abc";
    }
}
