package learn.spring.core.dao;

import learn.spring.core.entity.Auditorium;
import learn.spring.core.entity.Event;
import learn.spring.core.entity.EventAuditorium;
import learn.spring.core.entity.EventRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class EventDAO implements EntityDAO<Event> {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    public List<Event> eventsList;
    @Autowired
    EventAuditoriumDAO eventAuditoriumDAO;

    private static final String INSERT_QUERY = "INSERT INTO dict_events(id, name, basePrice, ratingId, minutesLength) VALUES (?,?,?,?,?);";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM dict_events;";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM dict_events WHERE lower(name) = ?;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dict_events WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM dict_events WHERE id = ?;";
    private static final String SELECT_DATE_RANGE_QUERY = "SELECT e.* FROM dict_events e " +
            "JOIN event_auditorium ea ON (ea.event_id = e.id)" +
            "where ea.event_date between ? and ?;";

    /**
     * Events from Java Configuration stores in database
     */
    @PostConstruct
    private void registerEventsFromSpringConfig(){
        for(Event e: eventsList){
            create(e);
        }
    }

    public Event getByName(String name){
        Object[] params = {name.toLowerCase()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_NAME_QUERY, params, entityRowMapper());
    }

    public Event getById(Integer id){
        Object[] params = {id};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_ID_QUERY, params, entityRowMapper());
    }

    public List<Event> getAll(){
        return jdbcTemplateEmbedded.query(SELECT_ALL_QUERY, entityRowMapper());
    }

    public void create(Event e){
        jdbcTemplateEmbedded.update(INSERT_QUERY,
                e.getId(),
                e.getName(),
                e.getBasePrice(),
                e.getRating().getVal(),
                e.getMinutesLength());
    }

    public void remove(Event e){
        Object[] params = {e.getId()};
        jdbcTemplateEmbedded.update(DELETE_QUERY, params);
    }

    public void assignAuditorium(Event event, Auditorium auditorium, Date date){
        EventAuditorium ea = new EventAuditorium(event, auditorium, date);
        eventAuditoriumDAO.assignEventAuditorium(ea);
    }

    public Set<Event> getForDateRange(Date from, Date to){
        Object[] params = {from, to};
        return new HashSet<>(jdbcTemplateEmbedded.query(SELECT_DATE_RANGE_QUERY, params, entityRowMapper()));
    }

    public Set<Event> getNextEvents(Date to){
        return getForDateRange(new Date(), to);
    }

    @Override
    public RowMapper<Event> entityRowMapper() {

        return (rs, rowNum) -> new Event(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDouble("basePrice"),
                EventRating.valueOf(rs.getInt("ratingId")),
                rs.getInt("minutesLength")
        );
    }

}
