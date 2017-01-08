package learn.spring.web.controllers;

import learn.spring.core.entity.Event;
import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import learn.spring.core.services.EventService;
import learn.spring.core.services.TicketService;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by aftor on 07.01.17.
 */
@Controller
@RequestMapping("pdf")
public class BookedTicketsController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    @RequestMapping(value = "booked/by-user/{id}", method = GET)
    public ModelAndView getBookedByUserView(@PathVariable int id, @RequestHeader("Accept") String header){
        User user = userService.getUserById(id);
        Set<Ticket> bookedTickets = ticketService.getBookedTicketsByUser(user);
        ModelAndView mv = new ModelAndView();
        if(header.contains("application/pdf")){
            mv.setViewName("bookedTicketsByUserPdf");
        } else {
            mv.setViewName("bookedTicketsByUser.ftl");
        }
        mv.addObject("tickets", bookedTickets);
        mv.addObject("user", user);
        return mv;
    }

    @RequestMapping(value = "booked/by-event/{id}", method = GET)
    public ModelAndView getBookedByEventView(@PathVariable int id, @RequestHeader("Accept") String header){
        Event event = eventService.getById(id);
        Set<Ticket> bookedTickets = ticketService.getBookedTicketsByEvent(event);
        ModelAndView mv = new ModelAndView();
        if(header.contains("application/pdf")){
            mv.setViewName("bookedTicketsByEventPdf");
        } else {
            mv.setViewName("bookedTicketsByEvent");
        }
        mv.addObject("tickets", bookedTickets);
        mv.addObject("event", event);
        return mv;
    }
}
