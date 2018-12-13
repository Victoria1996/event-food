package by.bsu.eventfood.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class EventFoodUtils {
    private final static int RANDOM_MAX_ADDED_DAYS = 3;

    public static Date parseDate(Date date, int hours, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);

        return calendar.getTime();
    }

    public static Date addRandomDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, ThreadLocalRandom.current().nextInt(RANDOM_MAX_ADDED_DAYS));

        return calendar.getTime();
    }

    public static boolean isDateExpired(Date date) {
        return date.after(new Date());
    }
}
