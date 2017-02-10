package learn.spring.web.controllers;

import learn.spring.core.entity.Event;
import learn.spring.core.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by aftor on 07.01.17.
 */
@Controller
@RequestMapping("events")
public class EventController {
    @Autowired
    EventService eventService;

    @RequestMapping(method = GET)
    public ModelAndView getAllEventsView(){
        ModelAndView mv = new ModelAndView("eventsByAttributeView");
        List<Event> allEvents = eventService.getAll();
        mv.addObject("eventsModel", allEvents);
        return mv;
    }

    @RequestMapping(value = "by-name/{name}", method = GET)
    public ModelAndView getEventsByNameView(@PathVariable String name){
        ModelAndView mv = new ModelAndView("eventsByAttributeView");
        if(name != null && name.trim().length() > 0){
            mv.addObject("attribute", "name");
            mv.addObject("attributeName", name);
            Event event = eventService.getByName(name);
            mv.addObject("eventsModel", Arrays.asList(event));
        }
        return mv;
    }

    @RequestMapping(value = "by-id/{id}", method = GET)
    public ModelAndView getEventByIdView(@PathVariable int id){
        ModelAndView mv = new ModelAndView("eventsByAttributeView");
        if(id >= 0){
            mv.addObject("attribute", "ID");
            mv.addObject("attributeName", id);
            Event event = eventService.getById(id);
            mv.addObject("eventsModel", Arrays.asList(event));
        }
        return mv;
    }
}
