package learn.spring.core.dao.aspects;

import learn.spring.core.entity.User;
import learn.spring.core.services.UserService;
import learn.spring.core.strategy.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DiscountAspectDAO implements CounterDAO {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    UserService userService;

    private static final String MERGE_COUNTER = "MERGE INTO dict_discount_counter (class_name, user_id, discount_count)" +
            " KEY(class_name, user_id) VALUES(?,?,?)";
    private static final String SELECT_COUNTER_BY_CLASS_AND_USER_QUERY = "SELECT discount_count FROM dict_discount_counter" +
            " WHERE class_name = ? and user_id = ?;";
    private static final String SELECT_COUNTER_BY_CLASS_QUERY = "SELECT discount_count FROM dict_discount_counter" +
            " WHERE class_name = ?;";
    private static final String SELECT_STRATEGIES_QUERY = "SELECT DISTINCT class_name from dict_discount_counter WHERE discount_count is not null;";
    private static final String SELECT_USERS_BY_STRATEGY_QUERY = "SELECT DISTINCT user_id from dict_discount_counter" +
            " WHERE discount_count is not null and class_name = ?;";


    public int getCounterByClassAndUser(Class clazz, User user){
        Object[] params = {clazz.getName(), user.getId()};
        return getCount(jdbcTemplateEmbedded, params, SELECT_COUNTER_BY_CLASS_AND_USER_QUERY);
    }

    public int getCounterByClass(Class clazz){
        Object[] params = {clazz.getName()};
        return getCount(jdbcTemplateEmbedded, params, SELECT_COUNTER_BY_CLASS_QUERY);
    }

    public void setCounter(Class clazz, User user, int discountCount){
        Object[] params = {clazz.getName(), user.getId(), discountCount};
        jdbcTemplateEmbedded.update(MERGE_COUNTER, params);
    }

    public List<Class<? extends DiscountStrategy>> getCountedDiscountStrategies() {
        return jdbcTemplateEmbedded.query(SELECT_STRATEGIES_QUERY, (rs, rowNum) -> {
            Class clazz = null;
            try {
                clazz = Class.forName(rs.getString("class_name"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return clazz;
        });
    }

    public List<User> getUsersByDiscountStrategy(Class<? extends DiscountStrategy> clazz){
        Object[] params = {clazz.getName()};
        return jdbcTemplateEmbedded.query(SELECT_USERS_BY_STRATEGY_QUERY, params, (rs, rowNum) -> {
            return userService.getUserById(rs.getInt("user_id"));
        });
    }
}
