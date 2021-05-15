package com.yzm.base.java8.date;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LocalDateTimeDemo {

    private static void demo01() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        LocalDateTime of = LocalDateTime.of(2020, 9, 10, 12, 30, 45);
        System.out.println("of = " + of);

        LocalDateTime parse = LocalDateTime.parse("2020-08-11 09:23:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("parse = " + parse);

        LocalDateTime ofInstant = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("ofInstant = " + ofInstant);
    }

    private static void demo02() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        System.out.println("年：" + now.getYear());
        System.out.println("月：" + now.getMonthValue());
        System.out.println("日：" + now.getDayOfMonth());
        System.out.println("时：" + now.getHour());
        System.out.println("分：" + now.getMinute());
        System.out.println("秒：" + now.getSecond());
        System.out.println("纳秒：" + now.getNano());
    }

    private static void demo03() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusOfYear = now.minus(2, ChronoUnit.YEARS);
        System.out.println("minusOfYear = " + minusOfYear);

        LocalDateTime plusOfHour = now.plus(3, ChronoUnit.HOURS);
        System.out.println("plusOfHour = " + plusOfHour);

        LocalDateTime withMonth = now.with(ChronoField.DAY_OF_MONTH, 20);
        System.out.println("withMonth = " + withMonth);

        long until = now.until(LocalDateTime.of(2022, 9, 10, 12, 30, 45), ChronoUnit.MONTHS);
        System.out.println("until = " + until);
    }

    private static void demo04() {
        Date date = new Date();
        LocalDateTime now = LocalDateTime.now();

        // Date 转 LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime);
        LocalDateTime localDateTime2 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime2 = " + localDateTime2);

        // LocalDateTime 转 Date
        Date from = Date.from(now.toInstant(ZoneOffset.of("+8")));
        System.out.println("from = " + from);
        Date from2 = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from2 = " + from2);
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
        LocalTime localTime = LocalTime.parse("08:30:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(LocalTime.now().isAfter(localTime));
    }
}
