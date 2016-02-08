package learn.spring.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtils {
    public static Calendar getCalendarWithoutTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }
}
