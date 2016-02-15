package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.entity.Event;
import learn.spring.entity.EventAuditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class EventDAO {
    @Autowired
    public List<Event> eventsList;

    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;

    public Event getByName(String name){
        for(Event e: eventsList){
            if(e.getName().toLowerCase().equals(name.toLowerCase())){
                return e;
            }
        }
        return null;
    }

    public List<Event> getAll(){
        return eventsList;
    }

    public void create(Event e){
        eventsList.add(e);
    }

    public void remove(Event e){
        Set<EventAuditorium> evensAuditoriums = EventAuditoriumDAO.getEventAuditoriumByEvent(e);
        EventAuditoriumDAO.eventAuditoriumList.removeAll(evensAuditoriums);

        eventsList.remove(e);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date){
        EventAuditorium ea = new EventAuditorium(event, auditorium, date);
        eventAuditoriumDAO.assignEventAuditorium(ea);
    }

    public Set<Event> getForDateRange(Date from, Date to){
        Set<Event> events = new HashSet<Event>();
        for(EventAuditorium ea: EventAuditoriumDAO.eventAuditoriumList){
            if(ea.getDateAndTime().after(from) && ea.getDateAndTime().before(to)){
                events.add(ea.getEvent());
            }
        }
        return events;
    }

    public Set<Event> getNextEvents(Date to){
        Set<Event> events = new HashSet<Event>();
        Date now = new Date();
        for(EventAuditorium ea: EventAuditoriumDAO.eventAuditoriumList){
            if(ea.getDateAndTime().after(now) && ea.getDateAndTime().before(to)){
                events.add(ea.getEvent());
            }
        }
        return events;
    }
}
