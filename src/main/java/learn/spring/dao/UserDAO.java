package learn.spring.dao;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO {

    @Autowired
    public List<User> usersList;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserById(Integer id){
        for(User u: usersList){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }

    public User getUserByEmail(String email){
        for(User u: usersList){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    public List<User> getUsersByName(String name){
        List<User> users = new ArrayList<User>();
        for(User u: usersList){
            if(u.getFirstName().toLowerCase().equals(name.toLowerCase())){
                users.add(u);
            }
        }

        return users;
    }

    public void register(User u){
        usersList.add(u);
    }

    public void remove(User user){
        usersList.remove(user);
    }
}
