package learn.spring.core.dao;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.*;

/**
 * Why Can`t Implement interface?
 * nested exception is java.lang.IllegalArgumentException: Can not set learn.spring.core.dao.TicketDAO field learn.spring.core.services.BookingService.ticketDAO to com.sun.proxy.$Proxy21
 *
 * ticketDAO using in BookingService which using as pointcut in Aspects
 */
@Component
public class TicketDAO/* implements EntityDAO<Ticket>*/ {
    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;

    private static final String BOOK_TICKET = "INSERT INTO booked_tickets(event_auditorium_id, user_id, seat) values (?,?,?);";
    private static final String BOOKED_FOR_USER = "SELECT COUNT(*) from booked_tickets WHERE event_auditorium_id = ? and seat = ?;";
    private static final String SELECT_BOOKED_BY_USER = "SELECT * from booked_tickets WHERE user_id = ?;";
    private static final String SELECT_BOOKED_BY_EVENT = "SELECT bt.* from booked_tickets bt " +
            "join event_auditorium ea on bt.event_auditorium_id = ea.id WHERE ea.event_id = ?;";

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

    public Set<Ticket> getBookedTicketsByUser(User user){
        Object[] params = {user.getId()};
        List<Ticket> query = jdbcTemplateEmbedded.query(SELECT_BOOKED_BY_USER, params, entityRowMapper());
        return new HashSet<>(query);
    }

    public Set<Ticket> getBookedTicketsByEvent(Event event){
        Object[] params = {event.getId()};
        List<Ticket> query = jdbcTemplateEmbedded.query(SELECT_BOOKED_BY_EVENT, params, entityRowMapper());
        return new HashSet<>(query);
    }

    private boolean isBooked(Ticket ticket){
        Object[] params ={eventAuditoriumDAO.getId(ticket.getEventAuditorium()), ticket.getSeat()};
        return jdbcTemplateEmbedded.queryForObject(BOOKED_FOR_USER, params, Integer.class) > 0;
    }

//    @Override
    public RowMapper<Ticket> entityRowMapper() {
        RowMapper<Ticket> rowMapper = (ResultSet rs, int rowNum) -> {
            Ticket ticket = new Ticket(
                    eventAuditoriumDAO.getById(rs.getInt("event_auditorium_id")),
                    rs.getInt("seat")
            );
            ticket.setUser(userDAO.getUserById(rs.getInt("user_id")));
            return ticket;
        };
        return rowMapper;
    }
}
