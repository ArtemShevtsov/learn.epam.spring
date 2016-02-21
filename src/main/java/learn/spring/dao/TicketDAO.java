package learn.spring.dao;

import learn.spring.entity.EventAuditorium;
import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Why Can`t Implement interface?
 * nested exception is java.lang.IllegalArgumentException: Can not set learn.spring.dao.TicketDAO field learn.spring.services.BookingService.ticketDAO to com.sun.proxy.$Proxy21
 *
 * ticketDAO using in BookingService which using as pointcut in Aspects
 */
@Component
public class TicketDAO/* implements EntityDAO<Ticket>*/ {
    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;

    private static final String BOOK_TICKET = "INSERT INTO booked_tickets(event_auditorium_id, user_id, seat) values (?,?,?);";
    private static final String BOOKED_FOR_USER = "SELECT COUNT(*) from booked_tickets WHERE event_auditorium_id = ? and seat = ?;";


    public static Map<Ticket, User> BookedTickets = new HashMap<Ticket, User>();

    public boolean bookTicket(User user, Ticket ticket){
        if(!isBooked(ticket)){
            Object[] params ={eventAuditoriumDAO.getId(ticket.getEventAuditorium()), user.getId(), ticket.getSeat()};
            jdbcTemplateEmbedded.update(BOOK_TICKET, params);
            return true;
        } else{
            return false;
        }
    }

    private boolean isBooked(Ticket ticket){
        Object[] params ={eventAuditoriumDAO.getId(ticket.getEventAuditorium()), ticket.getSeat()};
        return jdbcTemplateEmbedded.queryForObject(BOOKED_FOR_USER, params, Integer.class) > 0;
    }

//    @Override
    public RowMapper<Ticket> entityRowMapper() {
        return (rs, rowNum) -> new Ticket(
                eventAuditoriumDAO.getById(rs.getInt("event_auditorium_id")),
                rs.getInt("seat")
        );
    }
}
