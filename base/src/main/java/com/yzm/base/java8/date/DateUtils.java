package com.yzm.base.java8.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    private DateUtils() {
    }

    /**
     * 当前时间，是否在某时间段内
     *
     * @param startDateTime 开始时间
     * @param endDateTime   结束时间
     */
    public static boolean currentBetween(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startDateTime) && now.isBefore(endDateTime);
    }

    public static boolean currentBetween(Date startDate, Date endDate) {
        LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime endDateTime = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        return currentBetween(startDateTime, endDateTime);
    }

    public static boolean currentBetween(String startStr, String endStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime startDateTime = LocalDateTime.parse(startStr, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endStr, formatter);
        return currentBetween(startDateTime,endDateTime);
    }

    public static void main(String[] args) {
        System.out.println(currentBetween("2020/09/01 12:30:45", "2020/10/01 12:30:45"));
    }

}
