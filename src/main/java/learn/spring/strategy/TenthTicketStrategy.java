package learn.spring.strategy;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;

import java.text.ParseException;

public class TenthTicketStrategy implements DiscountStrategy{
    public int getDiscountPercent(Ticket ticket, User user) throws ParseException {
        int i = 1;
        while (true){
            if (user.getBookedTickets().size() < i*10){
                return 0;
            } else if(user.getBookedTickets().get(i*10 - 1).equals(ticket)){
                return 50;
            }
            i++;
        }
    }
}
