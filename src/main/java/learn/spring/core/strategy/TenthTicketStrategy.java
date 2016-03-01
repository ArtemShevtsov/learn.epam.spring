package learn.spring.core.strategy;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import java.util.Date;

public class TenthTicketStrategy implements DiscountStrategy{

    /**
     * Returns percent of discount. 50% for every 10th ticket
     * @param user
     * @param event
     * @param date
     * @return percent of discount (0 or 50)
     */
    public int getDiscountPercent(User user, Event event, Date date){
        int i = 1;
        while (true){
            if (user.getTicketMap().size() < i*10 - 1){
                return 0;
            } else if( ((Ticket) user.getTicketMap().get(i*10 - 1 - 1)).getEventAuditorium().getEvent().equals(event)){
                return 50;
            }
            i++;
        }
    }
}
