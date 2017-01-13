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

    private static final String INSERT_WITHOUT_ROLES_QUERY = "INSERT INTO dict_users(id, email, firstName, lastName, birthDay, password) VALUES (?,?,?,?,?,?);";
    private static final String INSERT_WITH_ROLES_QUERY = "INSERT INTO dict_users(id, email, firstName, lastName, birthDay, password, roles) VALUES (?,?,?,?,?,?,?);";
    private static final String DELETE_QUERY = "DELETE FROM dict_users WHERE id = ?;";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM dict_users WHERE lower(firstName) = ?;";
    private static final String SELECT_BY_EMAIL_QUERY = "SELECT * FROM dict_users WHERE lower(email) = ?;";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM dict_users WHERE id = ?;";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM dict_users;";

    /**
     * Users from Java Configuration stores in database
     */
    @PostConstruct
    private void registerUsersFromSpringConfig(){
        for(User u: usersList){
            register(u);
        }
    }


    public List<User> getAllUsers(){
        return jdbcTemplateEmbedded.query(SELECT_ALL_QUERY, entityRowMapper());
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
        String roles = u.getRoles();
        if(roles != null && roles.trim().length() > 0){
            jdbcTemplateEmbedded.update(INSERT_WITH_ROLES_QUERY,
                    u.getId(),
                    u.getEmail(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getBirthDay(),
                    u.getPassword(),
                    u.getRoles());
        } else {
            jdbcTemplateEmbedded.update(INSERT_WITHOUT_ROLES_QUERY,
                    u.getId(),
                    u.getEmail(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getBirthDay(),
                    u.getPassword());
        }
    }

    public void remove(User user){
        jdbcTemplateEmbedded.update(DELETE_QUERY, user.getId());
    }


    @Override
    public RowMapper<User> entityRowMapper(){
        RowMapper<User> rowMapper = (rs, rowNum) -> {
            User user = new User(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getDate("birthDay")
            );
            user.setPassword(rs.getString("password"));
            user.setRoles(rs.getString("roles"));
            return user;
        };
        return rowMapper;
    }
}
