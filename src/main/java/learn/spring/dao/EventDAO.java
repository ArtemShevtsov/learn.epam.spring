package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.entity.Event;
import learn.spring.entity.EventAuditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class EventDAO {
    public static List<Event> Events;
    public static Map<String, Event> EventsByName;

    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        Events = new ArrayList<Event>();
        EventsByName = new HashMap<String, Event>();
        Map<String, Event> eventBeans = applicationContext.getBeansOfType(Event.class);
        for (Map.Entry e: eventBeans.entrySet()) {
            create((Event) e.getValue());
        }
    }

    public Event getByName(String name){
        return EventsByName.get(name.toLowerCase());
    }

    public List<Event> getAll(){
        return Events;
    }

    public void create(Event e){
        Events.add(e);
        EventsByName.put(e.getName().toLowerCase(), e);
    }

    public void remove(Event e){
        Set evensAuditoriums = EventAuditoriumDAO.EventAuditoriumByEvent.get(e);
        EventAuditoriumDAO.EventAuditoriumByEvent.remove(e);
        EventAuditoriumDAO.EventAuditoriumSet.removeAll(evensAuditoriums);

        Events.remove(e);
        EventsByName.remove(e.getName().toLowerCase());
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date){
        EventAuditorium ea = new EventAuditorium(event, auditorium, date);
        eventAuditoriumDAO.assignEventAuditorium(ea);
    }

    public Set<Event> getForDateRange(Date from, Date to){
        Set<Event> events = new HashSet<Event>();
        for(EventAuditorium ea: EventAuditoriumDAO.EventAuditoriumSet){
            if(ea.getDateAndTime().after(from) && ea.getDateAndTime().before(to)){
                events.add(ea.getEvent());
            }
        }
        return events;
    }

    public Set<Event> getNextEvents(Date to){
        Set<Event> events = new HashSet<Event>();
        for(EventAuditorium ea: EventAuditoriumDAO.EventAuditoriumSet){
            if(ea.getDateAndTime().after(to)){
                events.add(ea.getEvent());
            }
        }
        return events;
    }
}
