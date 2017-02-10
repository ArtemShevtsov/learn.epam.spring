package learn.spring.core.entity.ws;

import javax.xml.bind.annotation.*;

/**
 * Created by artem_shevtsov on 2/7/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getUserByEmail")
@XmlType(name = "")
public class GetUserByEmail {

    @XmlElement(name = "userEmail")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
