package learn.spring.web.controllers.restfull;

import learn.spring.core.entity.*;
import learn.spring.core.entity.Event;
import learn.spring.core.services.TicketService;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by artem_shevtsov on 2/8/17.
 */
@Controller
@RequestMapping("ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(method = GET, produces = APPLICATION_PDF_VALUE)
    public Ticket getTicket(HttpServletResponse response){
        User userById = userService.getUserById(1);
        Set<Ticket> bookedTicketsByUser = ticketService.getBookedTicketsByUser(userById);
        response.setHeader("Content-Type", APPLICATION_PDF_VALUE);
        return bookedTicketsByUser.iterator().next();
    }
}
