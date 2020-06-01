package com.yonyou.base.java8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Time8Test {
	
	public static long differentDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new RuntimeException("日期不能为空");
        }
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return localDate1.until(localDate2, ChronoUnit.DAYS);
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }
}
