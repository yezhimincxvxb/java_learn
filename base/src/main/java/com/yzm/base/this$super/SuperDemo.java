package com.yzm.base.this$super;

import lombok.Data;

public class SuperDemo {

    public static void main(String[] args) {
        Student2 student = new Student2();
        student.method01();
    }

}

@Data
class Person2 {
    public Integer id;
    public String username;

    public Person2() {

    }

    public Person2(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public void method02() {
        System.out.println("父类method02方法 ==> " + this.username);
    }

}

@Data
class Student2 extends Person2 {
    private Integer id;
    private String username;

    public Student2() {
        //3.引用父类构造函数
        super(1, "aaa");
        this.id = 2;
        this.username = "bbb";
    }

    public void method01() {
        //2.子类中的成员变量或方法与父类中的成员变量或方法同名时，用super进行区分
        System.out.println("子类id ==> " + this.id);
        System.out.println("父类id ==> " + super.id);
        //1.普通的直接引用
        super.method02();
        this.method02();
    }

    public void method02() {
        System.out.println("子类method02方法 ==> " + this.username);
    }
}