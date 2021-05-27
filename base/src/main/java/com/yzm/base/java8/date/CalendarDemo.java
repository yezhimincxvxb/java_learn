package com.yzm.base.java8.date;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

    private static void demo01() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar.getTime());
    }

    private static void demo02() {
        Calendar calendar = Calendar.getInstance();

        System.out.println("年：" + calendar.get(Calendar.YEAR));
        System.out.println("月：" + calendar.get(Calendar.MONTH));
        System.out.println("日：" + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("时：" + calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("分：" + calendar.get(Calendar.MINUTE));
        System.out.println("秒：" + calendar.get(Calendar.SECOND));
    }

    private static void demo03() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, 9, 9, 12, 0, 0);

        System.out.println(calendar.after(calendar2));
        System.out.println(calendar.before(calendar2));
    }

    private static void demo04() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        System.out.println("calendar = " + calendar.getTime());

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        dayOfWeek = dayOfWeek == 0 ? 7 : dayOfWeek;
        System.out.println("星期：" + dayOfWeek);
    }

    private static void demo05() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.NOVEMBER, 1, 12, 0, 0);

        calendar.add(Calendar.YEAR, 10);
        System.out.println(calendar.get(Calendar.YEAR));

        calendar.add(Calendar.MONTH, -9);
        System.out.println(calendar.get(Calendar.MONTH));
    }

    public static void main(String[] args) {
//        demo01();
//        demo02();
//        demo03();
//        demo04();
//        demo05();
    }
}
