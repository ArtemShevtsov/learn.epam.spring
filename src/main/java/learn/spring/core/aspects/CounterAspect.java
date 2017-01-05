package learn.spring.core.aspects;

import learn.spring.core.dao.aspects.CounterAspectDAO;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CounterAspect {
    @Autowired
    CounterAspectDAO counterAspectDAO;

    // FIXME: 10%: no LuckyWinnerAspect

    @Pointcut ("execution(public * learn.spring.core.services.EventService.getByName(..))")
    private void eventByName(){}

    @Pointcut ("execution(public Double learn.spring.core.entity.Event.getBasePrice())")
    private void eventGetPrice(){}

    @Pointcut ("execution(* learn.spring.core.dao.TicketDAO.bookTicket(.., learn.spring.core.entity.Ticket))")
    private void bookTicket(){}

    @AfterReturning(pointcut = "eventByName()", returning = "event")
    public void afterGetByNameCount(Object event){
        Event e = (Event)event;
        int currentCount = counterAspectDAO.getCounterGetByNameVal(e);
        counterAspectDAO.setCounterGetByNameVal(e, currentCount + 1);
    }

    @AfterReturning(pointcut = "eventGetPrice()")
    public void afterGetBasePriceCount(JoinPoint jp){
        Event e = (Event)jp.getTarget();
        int currentCount = counterAspectDAO.getCounterPriceRequested(e);
        counterAspectDAO.setCounterPriceRequested(e, currentCount + 1);
    }


    @AfterReturning(
            pointcut = "bookTicket() && args(.., ticket)",
            returning = "isBooked")
    public void afterBookTicketCount(Ticket ticket, boolean isBooked){
        if(isBooked){
            Event e = ticket.getEventAuditorium().getEvent();
            int currentCount = counterAspectDAO.getCounterBooked(e);
            counterAspectDAO.setCounterBooked(e, currentCount + 1);
        }
    }

}
