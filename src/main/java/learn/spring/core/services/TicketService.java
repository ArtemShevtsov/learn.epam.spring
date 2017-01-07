package learn.spring.core.services;

import learn.spring.core.dao.TicketDAO;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by aftor on 07.01.17.
 */
@Service
public class TicketService {
    @Autowired
    private TicketDAO ticketDAO;

    public Set<Ticket> getBookedTicketsByUser(User user){
        return ticketDAO.getBookedTicketsByUser(user);
    }

    public Set<Ticket> getBookedTicketsByEvent(Event event){
        return ticketDAO.getBookedTicketsByEvent(event);
    }
}
