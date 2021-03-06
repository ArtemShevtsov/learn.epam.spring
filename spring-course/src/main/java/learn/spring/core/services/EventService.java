package learn.spring.core.services;

import learn.spring.core.dao.EventDAO;
import learn.spring.core.entity.Auditorium;
import learn.spring.core.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    EventDAO eventDAO;

    @Resource(name="theMartian")
    Event e1;
    @Resource(name="interstellar")
    Event e2;
    @Resource(name="badFilm")
    Event e3;
    @Resource(name="auditorium1")
    Auditorium a1;
    @Resource(name="auditorium2")
    Auditorium a2;

    @PostConstruct
    public void init(){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND  , 0);
        c.set(2017,Calendar.JANUARY,7,11,15);
        assignAuditorium(e1, a1, c.getTime());

        c.set(Calendar.HOUR_OF_DAY, 12);
        c.set(Calendar.MINUTE, 00);
        assignAuditorium(e1, a1, c.getTime());

        c.set(Calendar.HOUR_OF_DAY, 11);
        c.set(Calendar.MINUTE, 16);
        assignAuditorium(e2, a2, c.getTime());

    }

    public Event getByName(String name){
        return eventDAO.getByName(name);
    }

    public Event getById(Integer id){
        return  eventDAO.getById(id);
    }

    public List<Event> getAll(){
        return eventDAO.getAll();
    }

    public void create(Event event){
        eventDAO.create(event);
    }

    // FIXME: 0.5% never used
    public void remove(Event event){
        eventDAO.remove(event);
    }

    // FIXME: 0.5% never used
    // TODO: +1% optional task implemented
    public Set<Event> getForDateRange(Date from, Date to){
        return eventDAO.getForDateRange(from, to);
    }

    // FIXME: 0.5% never used
    // TODO: +1% optional task implemented
    public Set<Event> getNextEvents(Date to){
        return eventDAO.getNextEvents(to);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date){
        eventDAO.assignAuditorium(event, auditorium, date);
    }
}
