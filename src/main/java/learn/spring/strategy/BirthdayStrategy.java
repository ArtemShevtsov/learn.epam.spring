package learn.spring.strategy;

import learn.spring.entity.Event;
import learn.spring.entity.User;
import learn.spring.utils.CalendarUtils;

import java.util.Calendar;
import java.util.Date;

public class BirthdayStrategy implements  DiscountStrategy {

    public int getDiscountPercent(User user, Event event, Date date){
        Calendar ticketCal = CalendarUtils.getCalendarWithoutTime(date);
        Calendar birthDayCal = CalendarUtils.getCalendarWithoutTime(user.getBirthDay());

        if(ticketCal.equals(birthDayCal)){
            return 5;
        }
        return 0;
    }
}
