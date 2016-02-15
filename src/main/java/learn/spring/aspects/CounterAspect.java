package learn.spring.aspects;

import learn.spring.entity.Event;
import learn.spring.entity.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect {
    private Map<Event, Integer> counterGetByNameEvent = new HashMap<Event, Integer>();
    private Map<Event, Integer> counterPriceRequested = new HashMap<Event, Integer>();
    private Map<Event, Integer> counterBookingEvent = new HashMap<Event, Integer>();

    public Map<Event, Integer> getCounterGetByNameEvent() {
        return counterGetByNameEvent;
    }

    public Map<Event, Integer> getCounterPriceRequested() {
        return counterPriceRequested;
    }

    public Map<Event, Integer> getCounterBookingEvent() {
        return counterBookingEvent;
    }

    @Pointcut ("execution(public * learn.spring.dao.EventDAO.getByName(..))")
    private void eventByName(){}

    @Pointcut ("execution(public Double learn.spring.entity.Event.getBasePrice())")
    private void eventGetPrice(){}

    @Pointcut ("execution(* learn.spring.dao.TicketDAO.bookTicket(.., learn.spring.entity.Ticket))")
    private void bookTicket(){}

    @AfterReturning(pointcut = "eventByName()", returning = "event")
    public void afterGetByNameCount(Object event){
        Event e = (Event)event;
        if(!counterGetByNameEvent.containsKey(e)){
            counterGetByNameEvent.put(e, 0);
        }
        counterGetByNameEvent.put(e, counterGetByNameEvent.get(e) + 1);
    }

    @AfterReturning(pointcut = "eventGetPrice()")
    public void afterGetBasePriceCount(JoinPoint jp){
        Event e = (Event)jp.getTarget();
        if(!counterPriceRequested.containsKey(e)){
            counterPriceRequested.put(e, 0);
        }
        counterPriceRequested.put(e, counterPriceRequested.get(e) + 1);
    }


    @AfterReturning(
            pointcut = "bookTicket() && args(.., ticket)",
            returning = "isBooked")
    public void afterBookTicketCount(Ticket ticket, boolean isBooked){
        if(isBooked){
            Event e = ticket.getEventAuditorium().getEvent();
            if(!counterBookingEvent.containsKey(e)){
                counterBookingEvent.put(e, 0);
            }
            counterBookingEvent.put(e, counterBookingEvent.get(e) + 1);
        }
    }

}
