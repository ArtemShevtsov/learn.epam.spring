package learn.spring.aspects;

import learn.spring.dao.aspects.CounterAspectDAO;
import learn.spring.entity.Event;
import learn.spring.entity.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class CounterAspect {
    @Autowired
    CounterAspectDAO counterAspectDAO;

    // FIXME: 10%: no LuckyWinnerAspect

    @Pointcut ("execution(public * learn.spring.services.EventService.getByName(..))")
    private void eventByName(){}

    @Pointcut ("execution(public Double learn.spring.entity.Event.getBasePrice())")
    private void eventGetPrice(){}

    @Pointcut ("execution(* learn.spring.dao.TicketDAO.bookTicket(.., learn.spring.entity.Ticket))")
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
