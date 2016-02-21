package learn.spring.dao;

import learn.spring.entity.Auditorium;
import learn.spring.utils.StringToArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class AuditoriumDAO implements EntityDAO<Auditorium> {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    public List<Auditorium> auditoriums;

    private static final String SELECT_ALL_QUERY = "SELECT * FROM dict_auditoriums;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dict_auditoriums WHERE id = ?;";
    private static final String SELECT_ALL_SEATS_QUERY = "SELECT numberOfSeats FROM dict_auditoriums where id = ?;";
    private static final String SELECT_VIP_SEATS_QUERY = "SELECT vipSeats FROM dict_auditoriums where id = ?;";
    private static final String INSERT_QUERY = "INSERT INTO dict_auditoriums(id, name, numberOfSeats, vipSeats) values (?,?,?,?);";

    /**
     * Auditoriums from Java Configuration stores in database
     */
    @PostConstruct
    private void registerAuditoriumsFromSpringConfig(){
        for(Auditorium a: auditoriums){
            String vipSeats = Arrays.deepToString(a.getVipSeats());
            jdbcTemplateEmbedded.update(INSERT_QUERY,
                    a.getId(),
                    a.getName(),
                    a.getNumberOfSeats(),
                    vipSeats.substring(1, vipSeats.length() - 1));
        }
    }

    public List<Auditorium> getAuditoriums(){
        return jdbcTemplateEmbedded.query(SELECT_ALL_QUERY, entityRowMapper());
    }

    public Integer getSeatsNumber(Auditorium auditorium){
        Object[] params = {auditorium.getId()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_ALL_SEATS_QUERY, params, Integer.class);
    }

    public String getVipSeats(Auditorium auditorium){
        Object[] params = {auditorium.getId()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_VIP_SEATS_QUERY, params, String.class);
    }

    public Auditorium getById (Integer id){
        Object[] params = {id};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_ID_QUERY, params, entityRowMapper());
    }

    @Override
    public RowMapper<Auditorium> entityRowMapper() {
        return (rs, rowNum) -> new Auditorium(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("numberOfSeats"),
                StringToArrayUtils.converVipSeats(rs.getString("vipSeats"))
        );
    }
}
