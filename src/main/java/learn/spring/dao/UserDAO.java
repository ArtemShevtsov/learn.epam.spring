package learn.spring.dao;

import learn.spring.entity.Ticket;
import learn.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO {

    static public Map<Integer, User> UsersByIdMap;
    static public Map<String, User> UsersByEmailMap;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * Injecting from XML Spring configuration file
     * @param usersByIdMap
     */
    @Resource
    public void setUsersByIdMap(Map<Integer, User> usersByIdMap) {
        UsersByIdMap = usersByIdMap;
    }

    /**
     * Used for injecting  UsersByEmailMap Map
     * Read all User Beans from XML configuration and puting in the Map
     */
    @PostConstruct
    public void init(){
        UsersByEmailMap = new HashMap<String, User>();
        Map<String, User> userBeans = applicationContext.getBeansOfType(User.class);
        for (Map.Entry user: userBeans.entrySet()) {
            UsersByEmailMap.put(((User)user.getValue()).getEmail(), (User)user.getValue());
        }
    }

    public User getUserById(Integer id){
        return UsersByIdMap.get(id);
    }

    public User getUserByEmail(String email){
        return UsersByEmailMap.get(email);
    }

    public List<User> getUsersByName(String name){
        List<User> users = new ArrayList<User>();
        for (Map.Entry userEntry : UsersByIdMap.entrySet()) {
            if(((User)userEntry.getValue()).getFirstName().toLowerCase().equals(name.toLowerCase())){
                users.add((User)userEntry.getValue());
            }
        }
        return users;
    }

    public void register(User u){
        UsersByIdMap.put(u.getId(), u);
        UsersByEmailMap.put(u.getEmail(), u);
    }

    public void remove(User user){
        UsersByEmailMap.remove(user.getEmail());
        UsersByIdMap.remove(user.getId());
    }
}
