package com.snow;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneOffsetTransition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    static void print(List al2) {
        al2.add(2);
        al2 = new ArrayList();
        al2.add(3);
        al2.add(4);
    }

    static void testDate() {
        Calendar calendar = Calendar.getInstance();


        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK);//1
        if (day == Calendar.SUNDAY) {
            day = 8;
        }
        //int first = calendar.getFirstDayOfWeek();//2
        System.out.println(day);
        //System.out.println(first);


        // 计算周五的日期
        System.out.println("before");
        System.out.println(calendar);
        calendar.add(Calendar.DAY_OF_WEEK, Calendar.SUNDAY - day);
        System.out.println("after");
        System.out.println(calendar);

        Date date = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dd = dateFormat.format(date);
        System.out.println(Integer.valueOf(dd));

    }

    static void outDate() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.toString());
        Instant instant = date.toInstant();
        System.out.println(instant.toString());

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String dd = format.format(new Date());
        System.out.println(dd);

        Calendar calendar = Calendar.getInstance();
    }

    static void newDate() {
        Instant instant1 = Instant.now();
        long start = System.currentTimeMillis();

        LocalDateTime dt = LocalDateTime.now();

        System.out.println(dt);
        int year = dt.getYear();
        int month = dt.getMonthValue();
//        Month mon = dt.getMonth();
        int date = dt.getDayOfMonth();
        int day = dt.getDayOfWeek().getValue();
        int hour = dt.getHour();
        int minute = dt.getMinute();
        int second = dt.getSecond();
        System.out.println(year);
        System.out.println(month);
//        System.out.println(mon);
        System.out.println(date);
        System.out.println(day);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);

        ZoneId myid = ZoneId.systemDefault();
        System.out.println(myid);
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);
        ZoneId id2 = ZoneId.of("America/New_York");
        System.out.println("ZoneId: " + id2);
        System.out.println(ZoneId.from(ZonedDateTime.now()));
        System.out.println(ZoneId.from(ZoneOffset.of("+8")));

        LocalDateTime dt2 = dt.plusDays(-3);
        System.out.println(dt2);

        LocalDateTime dt3 = dt.plus(-3, ChronoUnit.DAYS);
        System.out.println(dt3);

        Instant instant2 = Instant.now();
        Duration du = Duration.between(instant1, instant2);

        long end = System.currentTimeMillis();
        System.out.println("instance: running time = " + du.toMillis());
        System.out.println("currentTimeMillis: running time = " + (end - start));

        System.out.println(ZoneId.getAvailableZoneIds());

        ZoneOffset zoneOffset=ZoneOffset.ofHours(8);
        System.out.println(zoneOffset);//+08:00

        System.out.println("transform ============");
        // 默认时区获取当前时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        // 用指定时区获取当前时间,Asia/Shanghai为上海时区

        //withZoneSameInstant为转换时区，参数为ZoneId
        ZonedDateTime zonedDateTime2 = zonedDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime2);

        LocalDateTime localDateTime=LocalDateTime.now();
        ZonedDateTime zonedDateTime3 = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        //ZonedDateTime zonedDateTime4 = localDateTime.atZone(ZoneId.of("America/New_York"));
        System.out.println(localDateTime);
        System.out.println(zonedDateTime3);
        //System.out.println(zonedDateTime4);
    }

    static void thisFriday() {
        System.out.println("========= this friday");
        // DayOfWeek.FRIDAY
        LocalDateTime local = LocalDateTime.now();
        int dayofweek = local.getDayOfWeek().getValue();
        System.out.println(local);
        LocalDateTime local2 = local.plusDays(DayOfWeek.FRIDAY.getValue() - dayofweek);
        System.out.println(local2);
        System.out.println("===========");

        System.out.println("============ zone start ");
        ZonedDateTime apac = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime emea = apac.withZoneSameInstant(ZoneId.of("Europe/London"));
        ZonedDateTime nam = apac.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("apac " + apac);
        System.out.println("emea " + emea);
        System.out.println("nam " + nam);
        System.out.println("=============");
        //========= this friday
        //2022-07-02T19:42:13.869315900
        //2022-07-01T19:42:13.869315900
        //===========
        //============ zone start
        //apac 2022-07-02T19:42:13.872314400+08:00[Asia/Shanghai]
        //emea 2022-07-02T12:42:13.872314400+01:00[Europe/London]
        //nam 2022-07-02T07:42:13.872314400-04:00[America/New_York]
        //=============
    }

    public static void main(String[] args) {
//	    List al1 = new ArrayList();
//	    al1.add(1);
//	    print(al1);
//        System.out.println(al1.get(1));
        //testDate();
        //outDate();
        //newDate();
        thisFriday();
    }
}
