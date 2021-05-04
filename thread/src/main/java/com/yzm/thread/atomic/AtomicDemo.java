package com.yzm.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子操作
 */
public class AtomicDemo {

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
        demo04();
    }

    private static final AtomicInteger ai = new AtomicInteger(0);

    /**
     * 原子更新基本类型
     * AtomicBoolean：原子更新布尔类型。
     * AtomicInteger：原子更新整型。
     * AtomicLong：原子更新长整型。
     */
    private static void demo01() {
//        method01();
//        method01_2();
        method01_3();
    }

    /**
     * int addAndGet(int delta)
     * 将内存值增加[delta]数值，返回增加后的值
     */
    private static void method01() {
        System.out.println(ai.get());
        System.out.println(ai.addAndGet(10));
        System.out.println(ai.get());
    }

    /**
     * boolean compareAndSet(int expect, int update)
     * 比较内存值跟预期值[expect]是一致的，则将内存值更新成新增[update]，返回true
     * 比较内存值跟预期值[expect]是不一致的，不做操作，直接返回false
     */
    private static void method01_2() {
        System.out.println(ai.get());
        System.out.println(ai.compareAndSet(0, 11));
        System.out.println(ai.get());
    }

    /**
     * int getAndIncrement()
     * 内存值自增1并返回自增后的值
     */
    private static void method01_3() {
        System.out.println(ai.get());
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }

    //=========================================================================

    private static final int[] is = {1, 2, 3, 4, 5};
    private static final AtomicIntegerArray aia = new AtomicIntegerArray(is);

    /**
     * 原子更新数组
     * AtomicIntegerArray：原子更新整型数组里的元素。
     * AtomicLongArray：原子更新长整型数组里的元素。
     * AtomicReferenceArray：原子更新引用类型数组里的元素。。
     */
    private static void demo02() {
//        method02();
        method02_2();
    }

    /**
     * int addAndGet(int i, int delta)
     * 给指定索引位置[i]的元素增加[delta]数值，返回增加后的值
     */
    private static void method02() {
        System.out.println(aia);
        System.out.println(aia.addAndGet(1, 10));
        System.out.println(aia);
    }

    /**
     * boolean compareAndSet(int i, int expect, int update)
     * 当预期值[expect]跟索引位置[i]的元素一样时，将索引位置[i]的元素更换成新增[update]，返回true
     * 当预期值[expect]跟索引位置[i]的元素一样时，不做操作，直接返回false
     */
    private static void method02_2() {
        System.out.println(aia);
        System.out.println(aia.compareAndSet(2, 3, 13));
        System.out.println(aia);
    }

    //=========================================================================

    private static final AtomicReference<String> ars = new AtomicReference<>("字符串");

    /**
     * 原子更新引用类型
     * AtomicReference：原子更新引用类型。
     * AtomicReferenceFieldUpdater：原子更新引用类型里的字段。
     * AtomicMarkableReference：原子更新带有标记位的引用类型
     */
    private static void demo03() {
        System.out.println(ars.get());
        ars.set("重新设值");
        System.out.println(ars.get());
    }

    //=========================================================================

    private static final AtomicDemo demo = new AtomicDemo();
    private volatile int age = 18;
    private static final AtomicIntegerFieldUpdater<AtomicDemo> aifu = AtomicIntegerFieldUpdater.newUpdater(AtomicDemo.class, "age");

    /**
     * 原子更新属性
     * AtomicIntegerFieldUpdater：原子更新整型的字段的更新器。
     * AtomicLongFieldUpdater：原子更新长整型字段的更新器。
     * AtomicStampedReference：原子更新带有版本号的引用类型。该类将整数值与引用关联起来，可用于原子的更新数据和数据的版本号，可以解决使用 CAS 进行原子更新时可能出现的 ABA 问题。
     */
    private static void demo04() {
        System.out.println(demo.age);
        int andIncrement = aifu.getAndIncrement(demo);
        System.out.println(andIncrement);
        System.out.println(demo.age);
        System.out.println(aifu.get(demo));
    }

}
