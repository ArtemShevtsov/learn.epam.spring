package learn.spring.core.dao;

import learn.spring.core.entity.User;
import learn.spring.core.entity.UserAccount;
import learn.spring.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * Created by artem_shevtsov on 1/19/17.
 */
@Component
public class UserAccountDAO implements EntityDAO<UserAccount> {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    UserService userService;

    private final String SELECT_BY_USER_ID_QUERY = "Select id as userId, availableMoney from dict_users where id=?";
    private final String UPDATE_BALANCE_BY_USER_ID_QUERY = "UPDATE dict_users set availableMoney=? where id=?";

    public UserAccount getAccountByUser(User user){
        Object[] params = {user.getId()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_USER_ID_QUERY, params, entityRowMapper());
    }

    public UserAccount updateBalance(UserAccount ua, Double additionalMoney) throws Exception {
        Double availableMoney = ua.getAvailableMoney();
        Double newBalance = Double.sum(availableMoney, additionalMoney);
        int update = jdbcTemplateEmbedded.update(UPDATE_BALANCE_BY_USER_ID_QUERY, newBalance, ua.getUser().getId());
        if(update == 1){
            ua.setAvailableMoney(newBalance);
        } else {
            throw new Exception("Too much records updated.");
        }
        return ua;
    }

    @Override
    public RowMapper<? extends UserAccount> entityRowMapper() {
        RowMapper<UserAccount> rm = (resultSet, i) -> {
            int userId = resultSet.getInt("userId");
            double availableMoney = resultSet.getDouble("availableMoney");
            User user = userService.getUserById(userId);
            return new UserAccount(user, availableMoney);
        };
        return rm;
    }
}
