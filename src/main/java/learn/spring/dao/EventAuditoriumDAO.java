package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.entity.Event;
import learn.spring.entity.EventAuditorium;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventAuditoriumDAO {
    public static Map<Event, Set<EventAuditorium>> EventAuditoriumByEvent = new HashMap<Event, Set<EventAuditorium>>();
    public static Map<Auditorium, Set<EventAuditorium>> EventAuditoriumByAuditorium = new HashMap<Auditorium, Set<EventAuditorium>>();
    public static Set<EventAuditorium> EventAuditoriumSet = new HashSet<EventAuditorium>();

    public void assignEventAuditorium(EventAuditorium ea){
        EventAuditoriumSet.add(ea);
        if(EventAuditoriumByEvent.get(ea.getEvent()) == null){
            Set s = new HashSet<EventAuditorium>();
            s.add(ea);
            EventAuditoriumByEvent.put(ea.getEvent(), s);
        } else {
            Set s = EventAuditoriumByEvent.get(ea.getEvent());
            s.add(ea);
            EventAuditoriumByEvent.put(ea.getEvent(), s);
        }

        EventAuditoriumByAuditorium.put(ea.getAuditorium(), EventAuditoriumSet);
        if(EventAuditoriumByAuditorium.get(ea.getAuditorium()) == null){
            Set s = new HashSet<EventAuditorium>();
            s.add(ea);
            EventAuditoriumByAuditorium.put(ea.getAuditorium(), s);
        } else {
            Set s = EventAuditoriumByAuditorium.get(ea.getAuditorium());
            s.add(ea);
            EventAuditoriumByAuditorium.put(ea.getAuditorium(), s);
        }
    }
}
