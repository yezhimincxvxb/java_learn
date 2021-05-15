package com.yzm.base.java8.date;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class LocalTimeDemo {

    private static void demo01() {
        // 创建对象
        LocalTime now = LocalTime.now();
        System.out.println("now = " + now);

        LocalTime of = LocalTime.of(13, 50, 30);
        System.out.println("of = " + of);

        LocalTime parse = LocalTime.parse("12:30:03", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("parse = " + parse);
    }

    private static void demo02() {
        // 获取时分秒
        LocalTime now = LocalTime.now();
        System.out.println("时(24小时制)：" + now.getHour());
        System.out.println("时(24小时制)：" + now.get(ChronoField.HOUR_OF_DAY));
        System.out.println("时(12小时制)：" + now.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("分：" + now.getMinute());
        System.out.println("分：" + now.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println("秒：" + now.getSecond());
        System.out.println("秒：" + now.get(ChronoField.SECOND_OF_MINUTE));
    }

    private static void demo03() {
        LocalTime now = LocalTime.now();
        // 比较
        System.out.println(now.isAfter(LocalTime.of(20, 12, 30)));
        System.out.println(now.isBefore(LocalTime.of(20, 12, 30)));
        // 格式化
        String format = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("format = " + format);
        // 增减
        LocalTime minus = now.minus(30, ChronoUnit.MINUTES);
        System.out.println("minus = " + minus);
        LocalTime plus = now.plus(2, ChronoUnit.HOURS);
        System.out.println("plus = " + plus);
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
        demo03();
    }
}
