package learn.spring.core.entity.ws;

import learn.spring.core.entity.User;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "userCollectionResponse")
@XmlType(name="")
public class UserCollectionResponse {

    @XmlElement(name = "userCollection")
    List<User> userCollection;

    public List<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(List<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(User u: userCollection){
            sb.append("\n" + u.toString());
        }
        return "UserCollectionResponse{" + sb.toString() + "\n}";
    }
}
