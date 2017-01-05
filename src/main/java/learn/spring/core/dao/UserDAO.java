package learn.spring.core.dao;

import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserDAO implements EntityDAO<User> {
    @Autowired
    JdbcTemplate jdbcTemplateEmbedded;
    @Autowired
    private List<User> usersList;

    private static final String INSERT_QUERY = "INSERT INTO dict_users(id, email, firstName, lastName, birthDay) VALUES (?,?,?,?,?);";
    private static final String DELETE_QUERY = "DELETE FROM dict_users WHERE id = ?;";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM dict_users WHERE lower(firstName) = ?;";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM dict_users WHERE lower(email) = ?;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dict_users WHERE id = ?;";

    /**
     * Users from Java Configuration stores in database
     */
    @PostConstruct
    private void registerUsersFromSpringConfig(){
        for(User u: usersList){
            register(u);
        }
    }

    public User getUserById(Integer id){
        Object[] params = {id};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_ID_QUERY, params, entityRowMapper());
    }

    public User getUserByEmail(String email){
        Object[] params = {email.toLowerCase()};
        return jdbcTemplateEmbedded.queryForObject(SELECT_BY_EMAIL_QUERY, params, entityRowMapper());
    }

    public List<User> getUsersByName(String name){
        Object[] params = {name.toLowerCase()};
        return jdbcTemplateEmbedded.query(SELECT_BY_NAME_QUERY, params, entityRowMapper());
    }

    public void register(User u){
        jdbcTemplateEmbedded.update(INSERT_QUERY,
                u.getId(),
                u.getEmail(),
                u.getFirstName(),
                u.getLastName(),
                u.getBirthDay());
    }

    public void remove(User user){
        jdbcTemplateEmbedded.update(DELETE_QUERY, user.getId());
    }


    @Override
    public RowMapper<User> entityRowMapper(){
        return (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getDate("birthDay")
        );
    }
}
