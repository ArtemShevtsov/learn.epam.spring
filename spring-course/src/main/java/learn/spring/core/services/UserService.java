package learn.spring.core.services;

import learn.spring.core.dao.TicketDAO;
import learn.spring.core.dao.UserAccountDAO;
import learn.spring.core.dao.UserDAO;

import learn.spring.core.entity.Ticket;
import learn.spring.core.entity.User;
import learn.spring.core.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserAccountDAO userAccountDAO;

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

    public UserAccount getUserAccountByUser(User user){
        return userAccountDAO.getAccountByUser(user);
    }

    public UserAccount doRefillAccount(UserAccount userAccount, Double refillAmount) throws Exception {
        double sum = Double.sum(userAccount.getAvailableMoney(), refillAmount);
        if(sum < 0){
            throw new Exception("User Balance must be more than zero!");
        }
        return userAccountDAO.updateBalance(userAccount, refillAmount);
    }
}
