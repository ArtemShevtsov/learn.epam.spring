package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.entity.Event;
import learn.spring.entity.EventAuditorium;
import learn.spring.services.AuditoriumService;
import learn.spring.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventAuditoriumDAO implements EntityDAO<EventAuditorium> {
    @Autowired
    EventService eventService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;

    private static final String ASSIGN_AUDITORY_QUERY = "INSERT INTO event_auditorium (event_id, auditorium_id, event_date) VALUES (?,?,?);";
    private static final String SELECT_BY_EVENT_QUERY = "SELECT * FROM event_auditorium WHERE event_id = ?;";
    private static final String SELECT_BY_AUDITORY_QUERY = "SELECT * FROM event_auditorium WHERE auditorium_id = ?;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM event_auditorium WHERE id = ?;";
    private static final String SELECT_ID_QUERY = "SELECT id FROM event_auditorium WHERE event_id = ? and auditorium_id = ? and event_date = ?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM event_auditorium;";

    public void assignEventAuditorium(EventAuditorium ea){
        Object[] params = {ea.getEvent().getId(), ea.getAuditorium().getId(), ea.getDateAndTime()};
        jdbcTemplateEmbedded.update(ASSIGN_AUDITORY_QUERY, params);
    }

    public Set<EventAuditorium> getEventAuditoriumByEvent(Event e){
        Object[] params = {e.getId()};
        return new HashSet<>(jdbcTemplateEmbedded.query(SELECT_BY_EVENT_QUERY, params, entityRowMapper()));
    }

    public Set<EventAuditorium> getEventAuditoriumByAuditorium(Auditorium a){
        Object[] params = {a.getId()};
        return new HashSet<>(jdbcTemplateEmbedded.query(SELECT_BY_AUDITORY_QUERY, params, entityRowMapper()));
    }

    public List<EventAuditorium> getAll(){
        return jdbcTemplateEmbedded.query(SELECT_ALL_QUERY, entityRowMapper());
    }

    public EventAuditorium getById(Integer id){
        Object[] params = {id};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_ID_QUERY, params, entityRowMapper());
    }

    public Integer getId(EventAuditorium ea){
        Object[] params = {ea.getEvent().getId(), ea.getAuditorium().getId(), ea.getDateAndTime()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_ID_QUERY, params, Integer.class);
    }

    @Override
    public RowMapper<EventAuditorium> entityRowMapper() {
        return (rs, rowNum) -> new EventAuditorium(
                eventService.getById(rs.getInt("event_id")),
                auditoriumService.getById(rs.getInt("auditorium_id")),
                rs.getTimestamp("event_date")
        );
    }
}
