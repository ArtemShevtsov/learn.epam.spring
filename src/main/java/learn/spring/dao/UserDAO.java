package learn.spring.dao;

import learn.spring.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDAO {
    static public Map<Integer, User> usersMap;

    public User getUserById(int id){
        return null;
    }

    public User getUserByEmail(String email){
        return null;
    }

    public User getUserByName(String name){
        return null;
    }
}
