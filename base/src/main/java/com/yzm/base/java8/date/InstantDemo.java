package com.yzm.base.java8.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.concurrent.TimeUnit;

public class InstantDemo {

    private static void demo01() {
        Instant now = Instant.now();
        System.out.println("now = " + now);

        System.out.println("秒" + now.getEpochSecond());
        System.out.println("毫秒" + now.toEpochMilli());
        System.out.println("毫秒" + System.currentTimeMillis());

        System.out.println(TimeUnit.HOURS.toMillis(8));
    }

    public static void main(String[] args) {
//        demo01();

        LocalDate now = LocalDate.of(2020, 9, 7);
        int i = now.get(ChronoField.DAY_OF_WEEK);
        System.out.println("i = " + i);

        System.out.println(now.isBefore(LocalDate.now()));
    }
}
