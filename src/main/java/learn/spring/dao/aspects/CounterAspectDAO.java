package learn.spring.dao.aspects;

import learn.spring.entity.Event;
import learn.spring.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CounterAspectDAO implements CounterDAO {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    EventService eventService;

    private static final String MERGE_COUNTER_GET_BY_NAME = "MERGE INTO dict_event_counter (event_id, [COUNTER_FIELD])" +
            " KEY (event_id) VALUES(?, ?);";
    private static final String SELECT_COUNTER_GET_BY_NAME = "SELECT [COUNTER_FIELD] FROM dict_event_counter WHERE event_id = ?;";
    private static final String SELECT_EVENTS_QUERY_BY_NAME = "SELECT event_id FROM dict_event_counter where count_get_by_name is not null;";
    private static final String SELECT_EVENTS_QUERY_PRICE_REQUESTED = "SELECT event_id FROM dict_event_counter where count_price_requested is not null;";
    private static final String SELECT_EVENTS_QUERY_BOOKED = "SELECT event_id FROM dict_event_counter where count_booked is not null;";

    private String prepareMergeQuery(String actualVal){
        String qTemplate = MERGE_COUNTER_GET_BY_NAME;
        return qTemplate.replace("[COUNTER_FIELD]", actualVal);
    }

    private String prepareSelectQuery(String actualVal){
        String qTemplate = SELECT_COUNTER_GET_BY_NAME;
        return qTemplate.replace("[COUNTER_FIELD]", actualVal);
    }

    public void setCounterGetByNameVal(Event e, int counterVal){
        Object[] params = {e.getId(), counterVal};
        jdbcTemplateEmbedded.update(prepareMergeQuery("count_get_by_name"), params);
    }

    public Integer getCounterGetByNameVal(Event e) {
        return getCount(jdbcTemplateEmbedded, new Object[]{e.getId()}, prepareSelectQuery("count_get_by_name"));
    }

    public void setCounterPriceRequested(Event e, int counterVal){
        Object[] params = {e.getId(), counterVal};
        jdbcTemplateEmbedded.update(prepareMergeQuery("count_price_requested"), params);
    }

    public Integer getCounterPriceRequested(Event e) {
        return getCount(jdbcTemplateEmbedded, new Object[]{e.getId()}, prepareSelectQuery("count_price_requested"));
    }

    public void setCounterBooked(Event e, int counterVal){
        Object[] params = {e.getId(), counterVal};
        jdbcTemplateEmbedded.update(prepareMergeQuery("count_booked"), params);
    }

    public Integer getCounterBooked(Event e) {
        return getCount(jdbcTemplateEmbedded, new Object[]{e.getId()}, prepareSelectQuery("count_booked"));
    }

    public List<Event> getCountedEventsByName(){
        return jdbcTemplateEmbedded.query(SELECT_EVENTS_QUERY_BY_NAME, (rs, rowNum) -> eventService.getById(rs.getInt("event_id")));
    }
    public List<Event> getCountedEventsPriceRequested(){
        return jdbcTemplateEmbedded.query(SELECT_EVENTS_QUERY_PRICE_REQUESTED, (rs, rowNum) -> eventService.getById(rs.getInt("event_id")));
    }
    public List<Event> getCountedEventsBooked(){
        return jdbcTemplateEmbedded.query(SELECT_EVENTS_QUERY_BOOKED, (rs, rowNum) -> eventService.getById(rs.getInt("event_id")));
    }
}
