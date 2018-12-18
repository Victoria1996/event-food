package by.bsu.eventfood.util;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class EventFoodUtils {
    private final static int RANDOM_MAX_ADDED_DAYS = 3;
    private final static int SHORT_TEXT_LENGTH = 300;
    private final static String SHORT_TEX_ENDS_POSTIFX = "...";

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

    public static Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY,hours);

        return calendar.getTime();
    }


    public static boolean isDateExpired(Date date) {
        return date.after(new Date());
    }

    public static String shortText(String text) {
        if (text == null) {
            return null;
        }

        if (text.length() < SHORT_TEXT_LENGTH) {
            return text;
        }

        return text.substring(0, Math.min(text.length(), SHORT_TEXT_LENGTH)) + SHORT_TEX_ENDS_POSTIFX;
    }
}
