package com.snow.magicapi.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtil {
    /**
     * @param whatDay e.g. DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, ...
     * */
    public static LocalDateTime weekday(DayOfWeek whatDay) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentDay = localDateTime.getDayOfWeek().getValue();
        return localDateTime.plusDays(whatDay.getValue() - currentDay);
    }

    public static void apac2emea() {
        ZonedDateTime apacDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime emeaDateTime = apacDateTime.withZoneSameInstant(ZoneId.of("Europe/London"));
    }

    public static void apac2nam() {
        ZonedDateTime apacDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime namDatetime = apacDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
    }
}
