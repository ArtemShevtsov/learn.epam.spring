package learn.spring.services;

import learn.spring.dao.UserDAO;
import static learn.spring.dao.UserDAO.*;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    public void showUsersInMaps(){
        System.out.println("UserById: ");
        for (Map.Entry user: UsersByIdMap.entrySet()) {
            System.out.printf("Key: %s; Value: %s\n", user.getKey(), user.getValue());
        }

        System.out.println("UserByEmail: ");
        for (Map.Entry user: UsersByEmailMap.entrySet()) {
            System.out.printf("Key: %s; Value: %s\n", user.getKey(), user.getValue());
        }
    }

    public void register(User u){
        userDAO.register(u);
    }

    public void remove(User user) {
        userDAO.remove(user);
    }

    public Set<Ticket> getBookedTickets(User user){
        return user.getTicketSet();
    }
}
