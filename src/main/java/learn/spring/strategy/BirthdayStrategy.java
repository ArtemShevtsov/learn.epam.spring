package learn.spring.strategy;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import java.util.Calendar;

public class BirthdayStrategy implements  DiscountStrategy {

    public int getDiscountPercent(Ticket ticket, User user){
        Calendar ticketCal = Calendar.getInstance();
        Calendar birthDayCal = Calendar.getInstance();

        ticketCal.setTime(ticket.getEventAuditorium().getDateAndTime());
        ticketCal.set(Calendar.HOUR_OF_DAY, 0);
        ticketCal.set(Calendar.MINUTE, 0);
        ticketCal.set(Calendar.MILLISECOND, 0);

        birthDayCal.setTime(user.getBirthDay());
        birthDayCal.set(Calendar.HOUR_OF_DAY, 0);
        birthDayCal.set(Calendar.MINUTE, 0);
        birthDayCal.set(Calendar.MILLISECOND, 0);

        if(ticketCal.equals(birthDayCal)){
            return 5;
        }
        return 0;
    }
}
