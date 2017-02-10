package learn.spring.core.entity.ws;

import javax.xml.bind.annotation.*;

/**
 * Created by artem_shevtsov on 2/2/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "getUserById")
@XmlType(name = "")
public class GetUserById {
    @XmlElement(name = "userId")
    Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
