package com.yzm.base.java8.date;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateDemo {

    private static void demo01() {
        Date date = new Date();
        System.out.println("date = " + date);

        Date date2 = new Date(System.currentTimeMillis() + 2000);
        System.out.println("date2 = " + date2);

        System.out.println(date.after(date2));
        System.out.println(date.before(date2));
        System.out.println(new Date().getTime() == System.currentTimeMillis());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
    }

    private static void demo02() {
        Date from = Date.from(Instant.now());
        System.out.println("from = " + from);

        Date from2 = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("from2 = " + from2);

        Date from3 = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        System.out.println("from3 = " + from3);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("localDateTime = " + localDateTime);
    }

    public static void main(String[] args) {
//        demo01();
        demo02();
    }
}
