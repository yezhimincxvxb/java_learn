package com.yzm.base.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateDemo {

    private static void demo01() {
        // 获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
        // 获取指定日期对象
        LocalDate localDate1 = LocalDate.of(2019, 9, 10);
        System.out.println("localDate1 = " + localDate1);
        // 将日期字符串，根据格式化转换成日期对象
        LocalDate parse = LocalDate.parse("2020&10&04", DateTimeFormatter.ofPattern("yyyy&MM&dd"));
        System.out.println("parse = " + parse);
    }

    private static void demo02() {
        LocalDate now = LocalDate.now();
        System.out.println("年 = " + now.getYear());
        System.out.println("年 = " + now.get(ChronoField.YEAR));
        System.out.println("月份对应的英文名称大写 = " + now.getMonth());
        System.out.println("月份 = " + now.getMonthValue());
        System.out.println("月份 = " + now.get(ChronoField.MONTH_OF_YEAR));
        System.out.println("今天几号 = " + now.getDayOfMonth());
        System.out.println("今天几号 = " + now.get(ChronoField.DAY_OF_MONTH));
        System.out.println("今天星期几对应的英文名称大写 = " + now.getDayOfWeek());
        System.out.println("今天星期几 = " + now.getDayOfWeek().getValue());
        System.out.println("今天星期几 = " + now.get(ChronoField.DAY_OF_WEEK));
        System.out.println("今天是第几天 = " + now.getDayOfYear());
    }

    private static void demo03() {
        // 日期格式化
        LocalDate now = LocalDate.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println("format = " + format);
        // 判断日期早晚
        System.out.println(now.isAfter(LocalDate.of(2020, 9, 11)));
        System.out.println(now.isBefore(LocalDate.of(2020, 9, 11)));
        // 日期加减
        LocalDate minusOfDay = now.minus(3, ChronoUnit.DAYS);
        System.out.println("minus = " + minusOfDay);
        LocalDate minusOfMonth = now.minus(2, ChronoUnit.MONTHS);
        System.out.println("minusOfMonth = " + minusOfMonth);
        LocalDate minusOfYear = now.minus(12, ChronoUnit.YEARS);
        System.out.println("minusOfYear = " + minusOfYear);
        LocalDate plusOfWeek = now.plus(2, ChronoUnit.WEEKS);
        System.out.println("plusOfWeek = " + plusOfWeek);
    }

    private static void demo04() {
        // 日期相差多少
        LocalDate now = LocalDate.now();
        long until = now.until(LocalDate.of(2020, 10, 1), ChronoUnit.DAYS);
        System.out.println("until = " + until);
        // 修改日期值
        LocalDate with = now.with(ChronoField.DAY_OF_MONTH, 19);
        System.out.println("with = " + with);

        // 日期转日期时间
        LocalDateTime localDateTime = now.atTime(LocalTime.now());
        System.out.println("localDateTime = " + localDateTime);
        // Date对象转LocalDate
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate = " + localDate);

        Date from = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from = " + from);
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
        demo04();
    }
}
