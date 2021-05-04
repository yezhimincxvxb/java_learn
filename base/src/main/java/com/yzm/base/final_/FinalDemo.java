package com.yzm.base.final_;

/**
 * 被final修饰的类不可以被继承
 * 被final修饰的方法不可以被重写
 * 被final修饰的变量不可以被改变，被final修饰不可变的是变量的引用，而不是引用指向的内容，引用指向的内容是可以改变的
 */
public class FinalDemo {

    /**
     * final finally finalize区别
     * final可以修饰类、变量、方法，修饰类表示该类不能被继承、修饰方法表示该方法不能被重写、修饰变量表
     *      示该变量是一个常量不能被重新赋值。
     * finally一般作用在try-catch代码块中，在处理异常的时候，通常我们将一定要执行的代码方法finally代码块
     *      中，表示不管是否出现异常，该代码块都会执行，一般用来存放一些关闭资源的代码。
     * finalize是一个方法，属于Object类的一个方法，而Object类是所有类的父类，该方法一般由垃圾回收器来调用，
     *      当我们调用System.gc() 方法的时候，由垃圾回收器调用finalize()，回收垃圾，一个对象是否可回收的最后判断。
     */
    private static void demo01() {

    }
}
