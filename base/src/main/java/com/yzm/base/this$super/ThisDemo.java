package com.yzm.base.this$super;

import lombok.Data;

public class ThisDemo {

    public static void main(String[] args) {
        Student student = new Student();
        student.method01();
    }

}

@Data
class Person {
    private Integer id;
    private String username;
}

@Data
class Student extends Person {
    private Integer id;
    private String username;

    public Student() {
        //3.引用本类的构造函数
        this(1, "aaa");
    }

    public Student(Integer id, String username) {
        //2.形参与成员名字重名，用this来区分：
        this.id = id;
        this.username = username;
    }

    public void method01() {
        System.out.println("调用method01方法 ==> " + this.id);
        //1.普通的直接引用，this相当于是指向当前对象本身。
        this.method02();
    }

    public void method02() {
        System.out.println("调用method02方法 ==> " + this.getUsername());
    }

}
