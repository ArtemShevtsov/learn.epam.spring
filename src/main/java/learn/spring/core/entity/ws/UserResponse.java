package learn.spring.core.entity.ws;

import learn.spring.core.entity.User;

import javax.xml.bind.annotation.*;

/**
 * Created by artem_shevtsov on 2/2/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "userResponse")
//@XmlType(name="", namespace = "http://localhost:8090/ws")
@XmlType(name="")
public class UserResponse {
//    @XmlTransient
    @XmlElement(name = "userValue")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
