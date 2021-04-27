package com.yzm.thread.expiringMap;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class ExpiringMapDemo {

    private static final ExpiringMap<String, Object> map;

    static {
        //初始化存储容器
        map = ExpiringMap.builder().variableExpiration()
                .variableExpiration()
                .expiration(5, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .build();
    }

    public static ExpiringMap<String, Object> getMap() {
        return map;
    }

    public static void main(String[] args) throws InterruptedException {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
        demo05();
    }

    /**
     * 设置过期时间
     */
    private static void demo01() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                // 可变到期
                .variableExpiration()
                // 过期时间：3000，单位毫秒
                .expiration(3000, TimeUnit.MILLISECONDS)
                .build();

        map.put("key", "value");
        System.out.println("key = " + map.get("key"));
        Thread.sleep(1000);
        System.out.println("key = " + map.get("key"));
        Thread.sleep(2001);
        // 超过过期时间，自动删除，key对应的value为null
        System.out.println("key = " + map.get("key"));
    }

    /**
     * 设置过期策略
     * 有两种过期策略
     * ExpirationPolicy.ACCESSED：访问元素时，清零过期时间(即过期时间重新从零计算)
     * ExpirationPolicy.CREATED：创建或更新元素时，清零过期时间
     */
    private static void demo02() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                // 可变到期
                .variableExpiration()
                // 过期时间：3000，单位毫秒
                .expiration(3000, TimeUnit.MILLISECONDS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .build();

        map.put("name", "abc");
        System.out.println("第一次访问name = " + map.get("name"));
        Thread.sleep(1000);
        System.out.println("第二次访问name = " + map.get("name"));
        Thread.sleep(2000);
        System.out.println("第三次访问name = " + map.get("name"));
        Thread.sleep(2999);
        System.out.println("第四次访问name = " + map.get("name"));
        map.replace("name", "ab");
        System.out.println("第五次访问name = " + map.get("name"));
        Thread.sleep(2999);
        System.out.println("第六次访问name = " + map.get("name"));
        // 每次get、put、replace都会重置过期时间
        Thread.sleep(3001);
        System.out.println("第七次访问name = " + map.get("name"));
    }

    /**
     * 设置过期策略
     * 有两种过期策略
     * ExpirationPolicy.ACCESSED：访问元素时，清零过期时间(即过期时间重新从零计算)
     * ExpirationPolicy.CREATED：创建或更新元素时，清零过期时间
     */
    private static void demo03() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                // 可变到期
                .variableExpiration()
                // 过期时间：3000，单位毫秒
                .expiration(3000, TimeUnit.MILLISECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .build();

        map.put("name", "aaa");
        System.out.println("第一次访问name = " + map.get("name"));
        Thread.sleep(1000);
        System.out.println("第二次访问name = " + map.get("name"));
        Thread.sleep(1500);
        System.out.println("第三次访问name = " + map.get("name"));
        Thread.sleep(500);
        // 只是get访问不会重置过期时间
        System.out.println("第四次访问name = " + map.get("name"));
        // 重新测试
        map.put("name", "ab");
        System.out.println("第五次访问name = " + map.get("name"));
        Thread.sleep(1000);
        System.out.println("第六次访问name = " + map.get("name"));
        Thread.sleep(1999);
        System.out.println("第七次访问name = " + map.get("name"));
        // 重新赋值
        map.replace("name", "aba");
        System.out.println("第八次访问name = " + map.get("name"));
        Thread.sleep(1000);
        System.out.println("第九次访问name = " + map.get("name"));
        // 每次put、replace都会重置过期时间，但get不会
        Thread.sleep(2001);
        System.out.println("第十次访问name = " + map.get("name"));
    }

    /**
     * 设置存储容器最大值
     * 但存储元素大于最大值时，最先进入容器的元素被移除
     */
    private static void demo04() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                .maxSize(5)
                .build();

        map.put("name", "abc");
        map.put("name2", "abc2");
        map.put("name3", "abc3");
        map.put("name4", "abc4");
        // 小于容器最大值时
        map.forEach((key, value) -> System.out.println(key + "=" + value));
        System.out.println("=================");
        Thread.sleep(1000);
        // 等于容器最大值时
        map.put("name5", "abc5");
        map.forEach((key, value) -> System.out.println(key + "=" + value));
        System.out.println("=================");
        Thread.sleep(1000);
        // 大于容器最大值时
        map.put("name6", "abc6");
        map.forEach((key, value) -> System.out.println(key + "=" + value));

    }

    /**
     * 设置过期侦听器
     * 元素过期时调用
     */
    private static void demo05() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                .expiration(3000, TimeUnit.MILLISECONDS)
                // 同步通知
                //.expirationListener(((key, value) -> System.out.println(key + "=" + value)))
                // 异步通知
                .asyncExpirationListener(((key, value) -> System.out.println(key + "=" + value)))
                .build();

        map.put("name", "abc");
        System.out.println("等待3秒");
        Thread.sleep(3000);
        System.out.println("3秒结束");
    }

    /**
     * 设置单个元素
     * 元素过期时调用
     */
    private static void demo06() throws InterruptedException {
        ExpiringMap<String, Object> map = ExpiringMap.builder()
                .build();

        map.put("key", "value", ExpirationPolicy.CREATED, 3, TimeUnit.SECONDS);
    }

}
