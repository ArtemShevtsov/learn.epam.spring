package learn.spring.core.strategy;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.User;
import learn.spring.utils.CalendarUtils;

import java.util.Calendar;
import java.util.Date;

public class BirthdayStrategy implements  DiscountStrategy {

    public int getDiscountPercent(User user, Event event, Date date){
        Calendar ticketCal = CalendarUtils.getCalendarWithoutTime(date);
        Calendar birthDayCal = CalendarUtils.getCalendarWithoutTime(user.getBirthDay());
        birthDayCal.set(Calendar.YEAR, ticketCal.get(Calendar.YEAR));

        if(ticketCal.get(Calendar.DAY_OF_MONTH) == birthDayCal.get(Calendar.DAY_OF_MONTH) &&
                ticketCal.get(Calendar.MONTH) == birthDayCal.get(Calendar.MONTH)){
            return 5;
        }
        return 0;
    }
}
