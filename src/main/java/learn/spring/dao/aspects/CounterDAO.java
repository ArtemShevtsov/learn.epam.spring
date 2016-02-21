package learn.spring.dao.aspects;

import org.springframework.jdbc.core.JdbcTemplate;

public interface CounterDAO {
    default int getCount(JdbcTemplate jdbcTemplate, Object[] params, String query){
        int count;
        try {
            count = jdbcTemplate.queryForObject(query, params, Integer.class);
        } catch (Exception ex){
            return 0;
        }
        return count;
    }
}
