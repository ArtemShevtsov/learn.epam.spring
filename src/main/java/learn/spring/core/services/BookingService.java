package learn.spring.core.services;


import learn.spring.core.dao.EventAuditoriumDAO;
import learn.spring.core.entity.*;
import learn.spring.exception.NotEnoughMoneyException;
import learn.spring.exception.SessionNotFoundException;
import learn.spring.utils.CalendarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import learn.spring.core.dao.TicketDAO;
import org.springframework.transaction.annotation.Transactional;

import static learn.spring.core.dao.TicketDAO.*;

import java.util.*;

@Service
public class BookingService {

    @Autowired
    DiscountService discountService;
    @Autowired
    UserService userService;

    @Autowired
    TicketDAO ticketDAO;
    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;

    /**
     * Calculate price for each seat and then calculate discount
     * @param event
     * @param date
     * @param seats
     * @param user
     * @return Summary price for all seats
     * @throws SessionNotFoundException
     */
    // FIXME: 0.5% never used
    public Double getTicketPrice(Event event, Date date, int[] seats, User user) throws SessionNotFoundException {
        Double price = event.getBasePrice();
        Double sumPrice = 0.;

        for(int seat: seats){
            Auditorium auditorium = getSessionAuditory(event, date);
            sumPrice += price +
                    (price*getRatingPriceCoefficient(event)) +
                    (price*getSeatPriceCoefficient(auditorium, seat));
        }
        return sumPrice - (sumPrice*discountService.getDiscount(user, event, date).getPercent()/100.);
    }

    @Transactional
    public boolean bookTicket(User user, Ticket ticket) throws SessionNotFoundException, NotEnoughMoneyException, Exception {
        Double ticketPrice = getTicketPrice(
                ticket.getEventAuditorium().getEvent(),
                ticket.getEventAuditorium().getDateAndTime(),
                new int[]{ticket.getSeat()},
                user
        );
        UserAccount userAccount = userService.getUserAccountByUser(user);
        double balance = Double.sum(userAccount.getAvailableMoney(), ticketPrice * -1);
        if(balance < 0){
            throw new NotEnoughMoneyException(String.format("Ticket is too expensive. Price: %,.2f; balance: %,.2f",
                    ticketPrice, userAccount.getAvailableMoney()));
        } else {
            userService.doRefillAccount(userAccount, ticketPrice*-1);
            return ticketDAO.bookTicket(user, ticket);
        }
    }

    public boolean isEventAuditoriumPresent(EventAuditorium ea){
        return eventAuditoriumDAO.isEventAuditoriumPresent(ea);
    }

    // FIXME: 0.5% never used
    public Set<Ticket> getTicketsForEvent(Event event, Date date){
        Calendar c1 = CalendarUtils.getCalendarWithoutTime(date);
        Calendar c2 = Calendar.getInstance();
        Event ticketEvent = null;
        Set<Ticket> resultSet = new HashSet<Ticket>();

        for(Map.Entry<Ticket, User> ticketUserEntry: BookedTickets.entrySet()){
            ticketEvent = ticketUserEntry.getKey().getEventAuditorium().getEvent();
            c2 = CalendarUtils.getCalendarWithoutTime(ticketUserEntry.getKey().getEventAuditorium().getDateAndTime());

            if(ticketEvent.equals(ticketUserEntry) && c1.equals(c2)){
                resultSet.add(ticketUserEntry.getKey());
            }
        }

        return resultSet;
    }

    private double getRatingPriceCoefficient(Event event){
        if(event.getRating().equals(EventRating.HIGHT)){
            return 0.2;
        } else {
            return 0.;
        }
    }

    private Auditorium getSessionAuditory(Event event, Date date) throws SessionNotFoundException {
        Calendar c = Calendar.getInstance();
        Calendar cSession = Calendar.getInstance();
        Auditorium auditorium = null;

        c.setTime(date);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Set<EventAuditorium> eventAuditoriumSet = eventAuditoriumDAO.getEventAuditoriumByEvent(event);
        for (EventAuditorium ea: eventAuditoriumSet){
            cSession.setTime(ea.getDateAndTime());
            cSession.set(Calendar.SECOND, 0);
            cSession.set(Calendar.MILLISECOND, 0);

            if(c.equals(cSession)){
                auditorium = ea.getAuditorium();
                break;
            }
        }

        if(auditorium == null){
            throw new SessionNotFoundException(String.format("Not found session for event \"%s\" and date %s.",
                    event, date));
        }
        return auditorium;
    }

    private double getSeatPriceCoefficient(Auditorium auditorium, int seat) {
        List vipSeatsList = Arrays.asList(auditorium.getVipSeats());
        if(vipSeatsList.size() == 0){
            return 0.;
        }
        if (vipSeatsList.contains(seat)){
            return 1.;
        }
        return 0.;
    }
}
