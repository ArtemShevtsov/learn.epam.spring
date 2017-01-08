package learn.spring.core.services;

import learn.spring.core.dao.TicketDAO;
import learn.spring.core.dao.UserDAO;

import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public User getUserById(Integer id){
        return userDAO.getUserById(id);
    }

    public User getUserByEmail(String email){
        return userDAO.getUserByEmail(email);
    }

    public List<User> getUsersByName(String name){
        return userDAO.getUsersByName(name);
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public void register(User u){
        userDAO.register(u);
    }

    public void remove(User user) {
        userDAO.remove(user);
    }

}
