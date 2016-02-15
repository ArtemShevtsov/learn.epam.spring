package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.entity.Event;
import learn.spring.entity.EventAuditorium;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventAuditoriumDAO {

    public static List<EventAuditorium> eventAuditoriumList = new ArrayList<EventAuditorium>();

    public void assignEventAuditorium(EventAuditorium ea){
        eventAuditoriumList.add(ea);
    }

    static public Set<EventAuditorium> getEventAuditoriumByEvent(Event e){
        Set<EventAuditorium> eventAuditoriums = new HashSet<EventAuditorium>();
        for(EventAuditorium ea: eventAuditoriumList){
            if(ea.getEvent().equals(e)){
                eventAuditoriums.add(ea);
            }
        }
        return eventAuditoriums;
    }

    static public Set<EventAuditorium> getEventAuditoriumByAuditorium(Auditorium a){
        Set<EventAuditorium> eventAuditoriums = new HashSet<EventAuditorium>();
        for(EventAuditorium ea: eventAuditoriumList){
            if(ea.getAuditorium().equals(a)){
                eventAuditoriums.add(ea);
            }
        }
        return eventAuditoriums;
    }
}
