package learn.spring.web.controllers;

import learn.spring.core.dao.EventAuditoriumDAO;
import learn.spring.core.entity.*;
import learn.spring.core.services.AuditoriumService;
import learn.spring.core.services.BookingService;
import learn.spring.core.services.EventService;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by aftor on 08.01.17.
 */
@Controller
@RequestMapping("book-ticket")
public class BookTicketController {

    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    BookingService bookingService;

    @RequestMapping(method = GET)
    public ModelAndView showBookTicketPageView(){
        ModelAndView mv = new ModelAndView("bookTicket");
        List<User> allUsers = userService.getAllUsers();
        List<Event> allEvents = eventService.getAll();
        List<Auditorium> allAuditoriums = auditoriumService.getAuditoriums();

        mv
                .addObject("users", allUsers)
                .addObject("events", allEvents)
                .addObject("auditoriums", allAuditoriums);
        return mv;
    }

    @RequestMapping(method = POST)
    public ModelAndView bookTicket(@RequestParam Integer userId, @RequestParam Integer eventId,
                                   @RequestParam Integer auditoriumId, @RequestParam Integer seat,
                                   @RequestParam Date date){
        Event event = eventService.getById(eventId);
        Auditorium auditorium = auditoriumService.getById(auditoriumId);
        User user = userService.getUserById(userId);
        EventAuditorium ea = new EventAuditorium(event, auditorium, date);
        boolean isEventAuditoriumPresent = bookingService.isEventAuditoriumPresent(ea);
        if(!isEventAuditoriumPresent){
            eventService.assignAuditorium(event, auditorium, date);
        }
        Ticket ticket = new Ticket(ea, seat);
        bookingService.bookTicket(user, ticket);

        ModelAndView mv = new ModelAndView("successBookedPage");
        return mv;
    }

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        CustomDateEditor customDateEditor = new CustomDateEditor(simpleDateFormat, false);
        binder.registerCustomEditor(Date.class, customDateEditor);
    }
}
