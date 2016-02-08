package learn.spring.dao;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TicketDAO {
    public static Map<Ticket, User> BookedTickets = new HashMap<Ticket, User>();

    public boolean bookTicket(User user, Ticket ticket){
        if(BookedTickets.get(ticket) == null){
            BookedTickets.put(ticket, user);
            user.bookTicket(ticket);
            return true;
        } else{
            return false;
        }
    }
}
