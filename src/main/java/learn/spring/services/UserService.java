package learn.spring.services;

import learn.spring.dao.UserDAO;
import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserById(int id){
        return userDAO.getUserById(id);
    }

    public User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

    public User getUserByName(String name){
        return userDAO.getUserByName(name);
    }
}
